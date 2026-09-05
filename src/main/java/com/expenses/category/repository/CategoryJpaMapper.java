package com.expenses.category.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.expenses.category.entity.CategoryEntity;
import com.expenses.common.MovementType;

/** The Interface Category Jpa Mapper. */
public interface CategoryJpaMapper extends JpaRepository<CategoryEntity, Integer>, JpaSpecificationExecutor<CategoryEntity> {

    /**
     * Find by id and user id.
     *
     * @param id the id
     * @param userId the user id
     * @return the optional category
     */
    Optional<CategoryEntity> findByIdAndUserId(Integer id, Integer userId);

    /**
     * Find by id and account id.
     *
     * @param id the id
     * @param accountId the account id
     * @return the optional category
     */
    Optional<CategoryEntity> findByIdAndAccountId(Integer id, Integer accountId);

    /**
     * Find by id, user id and account id.
     *
     * @param id the id
     * @param userId the user id
     * @param accountId the account id
     * @return the optional category
     */
    Optional<CategoryEntity> findByIdAndUserIdAndAccountId(Integer id, Integer userId, Integer accountId);

    /**
     * Find first category by account id and movement type.
     *
     * @param accountId the account id
     * @param movementType the movement type
     * @return the optional category
     */
    Optional<CategoryEntity> findFirstByAccountIdAndMovementType(Integer accountId, MovementType movementType);

    /**
     * Find all by user id.
     *
     * @param userId the user id
     * @return the list
     */
    List<CategoryEntity> findByUserId(Integer userId);

    /**
     * Find all by account id.
     *
     * @param accountId the account id
     * @return the list
     */
    List<CategoryEntity> findByAccountId(Integer accountId);
}
