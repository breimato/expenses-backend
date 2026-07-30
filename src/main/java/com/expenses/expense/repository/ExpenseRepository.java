package com.expenses.expense.repository;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.expenses.api.dto.MovementTypeV1;
import java.time.LocalDate;

import com.expenses.api.dto.PatchExpenseV1RequestDto;
import com.expenses.api.dto.PostExpenseV1RequestDto;
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
                categoryId,
                expenseDate,
                description,
                this.enumMapper.toMovementTypeOrNull(movementType)));
    }

    /**
     * Create.
     *
     * @param postExpenseV1RequestDto the post expense v1 request dto
     * @return the expense entity
     */
    @Transactional
    public ExpenseEntity create(final PostExpenseV1RequestDto postExpenseV1RequestDto) {

        final var movementType = this.enumMapper.toMovementType(postExpenseV1RequestDto.getMovementType());
        this.ensureCategoryMatchesMovement(postExpenseV1RequestDto.getCategoryId(), movementType);
        final var expenseEntity = this.postExpenseRequestMapper.toExpenseEntity(postExpenseV1RequestDto);
        expenseEntity.setMovementType(movementType);
        if (Objects.isNull(postExpenseV1RequestDto.getOffsetsSpendingAverage())) {
            expenseEntity.setOffsetsSpendingAverage(false);
        } else {
            expenseEntity.setOffsetsSpendingAverage(postExpenseV1RequestDto.getOffsetsSpendingAverage());
        }
        final var savedExpenseEntity = this.expenseJpaMapper.save(expenseEntity);
        return savedExpenseEntity;
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
            this.ensureCategoryMatchesMovement(patchExpenseV1RequestDto.getCategoryId(), movementType);
        } else if (Objects.nonNull(patchExpenseV1RequestDto.getMovementType())) {
            this.ensureCategoryMatchesMovement(
                    expenseEntity.getCategoryId(),
                    this.enumMapper.toMovementType(patchExpenseV1RequestDto.getMovementType()));
        }
        this.patchExpenseRequestMapper.updateExpenseEntity(patchExpenseV1RequestDto, expenseEntity);
        if (Objects.nonNull(patchExpenseV1RequestDto.getMovementType())) {
            expenseEntity.setMovementType(this.enumMapper.toMovementType(patchExpenseV1RequestDto.getMovementType()));
        }
        final var savedExpenseEntity = this.expenseJpaMapper.save(expenseEntity);
        return savedExpenseEntity;
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
     * Find by id.
     *
     * @param id the id
     * @return the expense entity
     */
    @Transactional(readOnly = true)
    public ExpenseEntity findById(final Integer id) {

        return this.expenseJpaMapper.findById(id)
                .orElseThrow(() -> new ExpenseException(ExceptionMessageConstants.EXPENSE_NOT_FOUND));
    }

    private void ensureCategoryMatchesMovement(final Integer categoryId, final MovementType movementType) {

        if (!this.categoryRepository.existsById(categoryId)) {
            throw new CategoryException(ExceptionMessageConstants.CATEGORY_NOT_FOUND);
        }
        final var categoryEntity = this.categoryRepository.findById(categoryId);
        if (categoryEntity.getMovementType() != movementType) {
            throw new CategoryException(ExceptionMessageConstants.CATEGORY_MOVEMENT_TYPE_MISMATCH);
        }
    }
}
