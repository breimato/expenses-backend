package com.expenses.category.repository;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.expenses.api.dto.MovementTypeV1;
import com.expenses.api.dto.PatchCategoryV1RequestDto;
import com.expenses.api.dto.PostCategoryV1RequestDto;

import com.expenses.category.entity.CategoryEntity;
import com.expenses.category.mapper.PatchCategoryRequestMapper;
import com.expenses.category.mapper.PostCategoryRequestMapper;
import com.expenses.common.EnumMapper;
import com.expenses.common.exception.CategoryException;
import com.expenses.common.exception.constants.ExceptionMessageConstants;
import com.expenses.expense.repository.ExpenseJpaMapper;
import com.expenses.recurring.repository.RecurringTemplateJpaMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

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
                id, name, this.enumMapper.toMovementTypeOrNull(movementType)));
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
        if (Objects.nonNull(postCategoryV1RequestDto.getMovementType())) {
            categoryEntity.setMovementType(this.enumMapper.toMovementType(postCategoryV1RequestDto.getMovementType()));
        }
        this.shiftIfNeeded(categoryEntity.getMovementType(), categoryEntity.getSortOrder(), null);
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
        final var oldSortOrder = categoryEntity.getSortOrder();
        this.patchCategoryRequestMapper.updateCategoryEntity(patchCategoryV1RequestDto, categoryEntity);
        if (Objects.nonNull(patchCategoryV1RequestDto.getMovementType())) {
            categoryEntity.setMovementType(this.enumMapper.toMovementType(patchCategoryV1RequestDto.getMovementType()));
        }
        this.shiftIfNeeded(categoryEntity.getMovementType(), categoryEntity.getSortOrder(), id);
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
     * Find by id.
     *
     * @param id the id
     * @return the category entity
     */
    @Transactional(readOnly = true)
    public CategoryEntity findById(final Integer id) {

        return this.categoryJpaMapper.findById(id)
                .orElseThrow(() -> new CategoryException(ExceptionMessageConstants.CATEGORY_NOT_FOUND));
    }

    /**
     * Exists by id.
     *
     * @param id the id
     * @return true, if successful
     */
    @Transactional(readOnly = true)
    public boolean existsById(final Integer id) {

        return this.categoryJpaMapper.existsById(id);
    }

    /**
     * If the target sortOrder is already taken by another category of the same
     * movement type, shift all categories at that position and above up by one.
     *
     * @param movementType the movement type scope
     * @param sortOrder    the desired position
     * @param excludeId    the id to exclude from the collision check (the category being edited), or null
     */
    private void shiftIfNeeded(final com.expenses.common.MovementType movementType,
                               final Integer sortOrder,
                               final Integer excludeId) {

        if (Objects.isNull(sortOrder) || Objects.isNull(movementType)) {
            return;
        }

        final boolean collision = this.categoryJpaMapper.findAll(
                CategorySpecification.withFilters(null, null, movementType))
                .stream()
                .anyMatch(c -> sortOrder.equals(c.getSortOrder())
                        && (excludeId == null || !excludeId.equals(c.getId())));

        if (collision) {
            this.categoryJpaMapper.shiftSortOrdersUp(movementType, sortOrder);
        }
    }
}
