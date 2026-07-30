package com.expenses.category.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.expenses.category.entity.CategoryEntity;
import com.expenses.common.MovementType;

import jakarta.persistence.criteria.Predicate;

/** The Class Category Specification. */
public final class CategorySpecification {

    private CategorySpecification() {
    }

    /**
     * Build specification for optional category filters.
     *
     * @param id the id
     * @param name the name
     * @param movementType the movement type
     * @return the specification
     */
    public static Specification<CategoryEntity> withFilters(
            final Integer id,
            final String name,
            final MovementType movementType) {

        return (root, query, criteriaBuilder) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (id != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), id));
            }
            if (name != null && !name.isBlank()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        "%" + name.toLowerCase() + "%"));
            }
            if (movementType != null) {
                predicates.add(criteriaBuilder.equal(root.get("movementType"), movementType));
            }

            query.orderBy(
                    criteriaBuilder.asc(root.get("name")),
                    criteriaBuilder.asc(root.get("id")));

            if (predicates.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
