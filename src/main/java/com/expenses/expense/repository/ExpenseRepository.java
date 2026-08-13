package com.expenses.expense.repository;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.expenses.api.dto.MovementTypeV1;
import java.time.LocalDate;

import com.expenses.api.dto.PatchExpenseV1RequestDto;
import com.expenses.api.dto.PostExpenseV1RequestDto;
import com.expenses.auth.service.CurrentUserService;
import com.expenses.category.repository.CategoryRepository;
import com.expenses.common.EnumMapper;
import com.expenses.common.MovementType;
import com.expenses.common.exception.CategoryException;
import com.expenses.common.exception.ExpenseException;
import com.expenses.common.exception.constants.ExceptionMessageConstants;
import com.expenses.expense.entity.ExpenseEntity;
import com.expenses.expense.mapper.PatchExpenseRequestMapper;
import com.expenses.expense.mapper.PostExpenseRequestMapper;
import com.expenses.recurring.repository.RecurringApplicationJpaMapper;

import org.openapitools.jackson.nullable.JsonNullable;

import lombok.RequiredArgsConstructor;

/** The Class Expense Repository. */
@Component
@RequiredArgsConstructor
public class ExpenseRepository {

    /** The expense jpa mapper. */
    private final ExpenseJpaMapper expenseJpaMapper;

    /** The category repository. */
    private final CategoryRepository categoryRepository;

    /** The post expense request mapper. */
    private final PostExpenseRequestMapper postExpenseRequestMapper;

    /** The patch expense request mapper. */
    private final PatchExpenseRequestMapper patchExpenseRequestMapper;

    /** The enum mapper. */
    private final EnumMapper enumMapper;

    /** The recurring application jpa mapper. */
    private final RecurringApplicationJpaMapper recurringApplicationJpaMapper;

    /** The current user service. */
    private final CurrentUserService currentUserService;

    /**
     * Find all.
     *
     * @param categoryId the category id
     * @param expenseDate the expense date
     * @param description the description
     * @param movementType the movement type
     * @return the list
     */
    @Transactional(readOnly = true)
    public List<ExpenseEntity> findAll(
            final Integer categoryId,
            final LocalDate expenseDate,
            final String description,
            final MovementTypeV1 movementType) {

        return this.expenseJpaMapper.findAll(ExpenseSpecification.withFilters(
                this.currentUserService.getRequiredUserId(),
                categoryId,
                expenseDate,
                description,
                this.enumMapper.toMovementTypeOrNull(movementType)));
    }

    /**
     * Create for current user.
     *
     * @param postExpenseV1RequestDto the post expense v1 request dto
     * @return the expense entity
     */
    @Transactional
    public ExpenseEntity create(final PostExpenseV1RequestDto postExpenseV1RequestDto) {

        return this.create(postExpenseV1RequestDto, this.currentUserService.getRequiredUserId());
    }

    /**
     * Create for a specific user.
     *
     * @param postExpenseV1RequestDto the post expense v1 request dto
     * @param userId the user id
     * @return the expense entity
     */
    @Transactional
    public ExpenseEntity create(final PostExpenseV1RequestDto postExpenseV1RequestDto, final Integer userId) {

        final var movementType = this.enumMapper.toMovementType(postExpenseV1RequestDto.getMovementType());
        this.ensureCategoryMatchesMovement(postExpenseV1RequestDto.getCategoryId(), movementType, userId);
        final var expenseEntity = this.postExpenseRequestMapper.toExpenseEntity(postExpenseV1RequestDto);
        expenseEntity.setUserId(userId);
        expenseEntity.setMovementType(movementType);
        if (Objects.isNull(postExpenseV1RequestDto.getOffsetsSpendingAverage())) {
            expenseEntity.setOffsetsSpendingAverage(false);
        } else {
            expenseEntity.setOffsetsSpendingAverage(postExpenseV1RequestDto.getOffsetsSpendingAverage());
        }
        this.applyReimbursedExpenseLink(
                expenseEntity,
                movementType,
                userId,
                this.unwrapInteger(postExpenseV1RequestDto.getReimbursedExpenseId()));
        return this.expenseJpaMapper.save(expenseEntity);
    }

    /**
     * Update.
     *
     * @param id the id
     * @param patchExpenseV1RequestDto the patch expense v1 request dto
     * @return the expense entity
     */
    @Transactional
    public ExpenseEntity update(final Integer id, final PatchExpenseV1RequestDto patchExpenseV1RequestDto) {

        final var expenseEntity = this.findById(id);
        if (Objects.nonNull(patchExpenseV1RequestDto.getCategoryId())) {
            final var movementType = Objects.nonNull(patchExpenseV1RequestDto.getMovementType())
                    ? this.enumMapper.toMovementType(patchExpenseV1RequestDto.getMovementType())
                    : expenseEntity.getMovementType();
            this.ensureCategoryMatchesMovement(
                    patchExpenseV1RequestDto.getCategoryId(),
                    movementType,
                    expenseEntity.getUserId());
        } else if (Objects.nonNull(patchExpenseV1RequestDto.getMovementType())) {
            this.ensureCategoryMatchesMovement(
                    expenseEntity.getCategoryId(),
                    this.enumMapper.toMovementType(patchExpenseV1RequestDto.getMovementType()),
                    expenseEntity.getUserId());
        }
        this.patchExpenseRequestMapper.updateExpenseEntity(patchExpenseV1RequestDto, expenseEntity);
        if (Objects.nonNull(patchExpenseV1RequestDto.getMovementType())) {
            expenseEntity.setMovementType(this.enumMapper.toMovementType(patchExpenseV1RequestDto.getMovementType()));
        }
        if (Objects.nonNull(patchExpenseV1RequestDto.getReimbursedExpenseId())
                && patchExpenseV1RequestDto.getReimbursedExpenseId().isPresent()) {
            this.applyReimbursedExpenseLink(
                    expenseEntity,
                    expenseEntity.getMovementType(),
                    expenseEntity.getUserId(),
                    patchExpenseV1RequestDto.getReimbursedExpenseId().get());
        } else if (expenseEntity.getMovementType() != MovementType.INCOME) {
            expenseEntity.setReimbursedExpenseId(null);
        }
        return this.expenseJpaMapper.save(expenseEntity);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @Transactional
    public void delete(final Integer id) {

        final var expenseEntity = this.findById(id);
        this.recurringApplicationJpaMapper.deleteByMovementId(id);
        this.expenseJpaMapper.delete(expenseEntity);
    }

    /**
     * Find by id for current user.
     *
     * @param id the id
     * @return the expense entity
     */
    @Transactional(readOnly = true)
    public ExpenseEntity findById(final Integer id) {

        return this.expenseJpaMapper.findByIdAndUserId(id, this.currentUserService.getRequiredUserId())
                .orElseThrow(() -> new ExpenseException(ExceptionMessageConstants.EXPENSE_NOT_FOUND));
    }

    private void applyReimbursedExpenseLink(
            final ExpenseEntity expenseEntity,
            final MovementType movementType,
            final Integer userId,
            final Integer reimbursedExpenseId) {

        if (movementType != MovementType.INCOME || Objects.isNull(reimbursedExpenseId)) {
            expenseEntity.setReimbursedExpenseId(null);
            return;
        }
        final var reimbursedExpenseEntity = this.expenseJpaMapper.findByIdAndUserId(reimbursedExpenseId, userId)
                .orElseThrow(() -> new ExpenseException(ExceptionMessageConstants.EXPENSE_REIMBURSED_NOT_FOUND));
        if (reimbursedExpenseEntity.getMovementType() != MovementType.EXPENSE) {
            throw new ExpenseException(
                    ExceptionMessageConstants.EXPENSE_REIMBURSED_MUST_BE_EXPENSE,
                    HttpStatus.BAD_REQUEST);
        }
        expenseEntity.setReimbursedExpenseId(reimbursedExpenseId);
        expenseEntity.setOffsetsSpendingAverage(true);
    }

    private Integer unwrapInteger(final JsonNullable<Integer> jsonNullable) {

        if (Objects.isNull(jsonNullable) || !jsonNullable.isPresent()) {
            return null;
        }
        return jsonNullable.get();
    }

    private void ensureCategoryMatchesMovement(
            final Integer categoryId,
            final MovementType movementType,
            final Integer userId) {

        if (!this.categoryRepository.existsByIdForUser(categoryId, userId)) {
            throw new CategoryException(ExceptionMessageConstants.CATEGORY_NOT_FOUND);
        }
        final var categoryEntity = this.categoryRepository.findByIdForUser(categoryId, userId);
        if (categoryEntity.getMovementType() != movementType) {
            throw new CategoryException(ExceptionMessageConstants.CATEGORY_MOVEMENT_TYPE_MISMATCH);
        }
    }
}
