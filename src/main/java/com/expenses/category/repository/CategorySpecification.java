package com.expenses.category.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
     * @param accountId the account id
     * @param id the id
     * @param name the name
     * @param movementType the movement type
     * @return the specification
     */
    public static Specification<CategoryEntity> withFilters(
            final Integer accountId,
            final Integer id,
            final String name,
            final MovementType movementType) {

        return (root, query, criteriaBuilder) -> {
            final List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("accountId"), accountId));
            if (Objects.nonNull(id)) {
                predicates.add(criteriaBuilder.equal(root.get("id"), id));
            }
            if (Objects.nonNull(name) && !name.isBlank()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        "%" + name.toLowerCase() + "%"));
            }
            if (Objects.nonNull(movementType)) {
                predicates.add(criteriaBuilder.equal(root.get("movementType"), movementType));
            }
            query.orderBy(
                    criteriaBuilder.asc(root.get("name")),
                    criteriaBuilder.asc(root.get("id")));
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
