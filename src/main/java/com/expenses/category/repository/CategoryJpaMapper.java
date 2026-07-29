package com.expenses.category.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.expenses.category.entity.CategoryEntity;
import com.expenses.common.MovementType;

/** The Interface Category Jpa Mapper. */
public interface CategoryJpaMapper extends JpaRepository<CategoryEntity, Integer>, JpaSpecificationExecutor<CategoryEntity> {

    /**
     * Shift sort orders up by 1 for all categories of the given movement type
     * whose current sort order is >= the target position.
     *
     * @param movementType the movement type
     * @param fromOrder    the position from which to shift (inclusive)
     */
    @Modifying
    @Query("UPDATE CategoryEntity c SET c.sortOrder = c.sortOrder + 1 "
         + "WHERE c.movementType = :movementType AND c.sortOrder >= :fromOrder")
    void shiftSortOrdersUp(@Param("movementType") MovementType movementType,
                           @Param("fromOrder") int fromOrder);

    /**
     * Check if a category with this sort order and movement type already exists.
     */
    boolean existsByMovementTypeAndSortOrder(MovementType movementType, Integer sortOrder);
}
