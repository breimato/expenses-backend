package com.expenses.category.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.expenses.category.entity.CategoryEntity;

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
     * Find all by user id.
     *
     * @param userId the user id
     * @return the list
     */
    List<CategoryEntity> findByUserId(Integer userId);
}
