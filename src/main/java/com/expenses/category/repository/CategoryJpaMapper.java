package com.expenses.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.expenses.category.entity.CategoryEntity;

/** The Interface Category Jpa Mapper. */
public interface CategoryJpaMapper extends JpaRepository<CategoryEntity, Integer>, JpaSpecificationExecutor<CategoryEntity> {
}
