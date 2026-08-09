package com.expenses.category.repository;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.expenses.api.dto.MovementTypeV1;
import com.expenses.api.dto.PatchCategoryV1RequestDto;
import com.expenses.api.dto.PostCategoryV1RequestDto;
import com.expenses.auth.service.CurrentUserService;
import com.expenses.category.entity.CategoryEntity;
import com.expenses.category.mapper.PatchCategoryRequestMapper;
import com.expenses.category.mapper.PostCategoryRequestMapper;
import com.expenses.common.EnumMapper;
import com.expenses.common.exception.CategoryException;
import com.expenses.common.exception.constants.ExceptionMessageConstants;
import com.expenses.expense.repository.ExpenseJpaMapper;
import com.expenses.recurring.repository.RecurringTemplateJpaMapper;

import lombok.RequiredArgsConstructor;

/** The Class Category Repository. */
@Component
@RequiredArgsConstructor
public class CategoryRepository {

    /** The category jpa mapper. */
    private final CategoryJpaMapper categoryJpaMapper;

    /** The post category request mapper. */
    private final PostCategoryRequestMapper postCategoryRequestMapper;

    /** The patch category request mapper. */
    private final PatchCategoryRequestMapper patchCategoryRequestMapper;

    /** The enum mapper. */
    private final EnumMapper enumMapper;

    /** The expense jpa mapper. */
    private final ExpenseJpaMapper expenseJpaMapper;

    /** The recurring template jpa mapper. */
    private final RecurringTemplateJpaMapper recurringTemplateJpaMapper;

    /** The current user service. */
    private final CurrentUserService currentUserService;

    /**
     * Find all.
     *
     * @param id the id
     * @param name the name
     * @param movementType the movement type
     * @return the list
     */
    @Transactional(readOnly = true)
    public List<CategoryEntity> findAll(final Integer id, final String name, final MovementTypeV1 movementType) {

        return this.categoryJpaMapper.findAll(CategorySpecification.withFilters(
                this.currentUserService.getRequiredUserId(),
                id,
                name,
                this.enumMapper.toMovementTypeOrNull(movementType)));
    }

    /**
     * Create.
     *
     * @param postCategoryV1RequestDto the post category v1 request dto
     * @return the category entity
     */
    @Transactional
    public CategoryEntity create(final PostCategoryV1RequestDto postCategoryV1RequestDto) {

        final var categoryEntity = this.postCategoryRequestMapper.toCategoryEntity(postCategoryV1RequestDto);
        categoryEntity.setUserId(this.currentUserService.getRequiredUserId());
        if (Objects.nonNull(postCategoryV1RequestDto.getMovementType())) {
            categoryEntity.setMovementType(this.enumMapper.toMovementType(postCategoryV1RequestDto.getMovementType()));
        }
        return this.categoryJpaMapper.save(categoryEntity);
    }

    /**
     * Update.
     *
     * @param id the id
     * @param patchCategoryV1RequestDto the patch category v1 request dto
     * @return the category entity
     */
    @Transactional
    public CategoryEntity update(final Integer id, final PatchCategoryV1RequestDto patchCategoryV1RequestDto) {

        final var categoryEntity = this.findById(id);
        this.patchCategoryRequestMapper.updateCategoryEntity(patchCategoryV1RequestDto, categoryEntity);
        if (Objects.nonNull(patchCategoryV1RequestDto.getMovementType())) {
            categoryEntity.setMovementType(this.enumMapper.toMovementType(patchCategoryV1RequestDto.getMovementType()));
        }
        return this.categoryJpaMapper.save(categoryEntity);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @Transactional
    public void delete(final Integer id) {

        final var categoryEntity = this.findById(id);
        if (this.expenseJpaMapper.existsByCategoryId(id) || this.recurringTemplateJpaMapper.existsByCategoryId(id)) {
            throw new CategoryException(ExceptionMessageConstants.CATEGORY_IN_USE, HttpStatus.CONFLICT);
        }
        this.categoryJpaMapper.delete(categoryEntity);
    }

    /**
     * Find by id for current user.
     *
     * @param id the id
     * @return the category entity
     */
    @Transactional(readOnly = true)
    public CategoryEntity findById(final Integer id) {

        return this.findByIdForUser(id, this.currentUserService.getRequiredUserId());
    }

    /**
     * Find by id for user.
     *
     * @param id the id
     * @param userId the user id
     * @return the category entity
     */
    @Transactional(readOnly = true)
    public CategoryEntity findByIdForUser(final Integer id, final Integer userId) {

        return this.categoryJpaMapper.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new CategoryException(ExceptionMessageConstants.CATEGORY_NOT_FOUND));
    }

    /**
     * Exists by id for current user.
     *
     * @param id the id
     * @return true, if successful
     */
    @Transactional(readOnly = true)
    public boolean existsById(final Integer id) {

        return this.existsByIdForUser(id, this.currentUserService.getRequiredUserId());
    }

    /**
     * Exists by id for user.
     *
     * @param id the id
     * @param userId the user id
     * @return true, if successful
     */
    @Transactional(readOnly = true)
    public boolean existsByIdForUser(final Integer id, final Integer userId) {

        return this.categoryJpaMapper.findByIdAndUserId(id, userId).isPresent();
    }
}
