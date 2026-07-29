package com.expenses.expense.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.expenses.common.MovementType;
import com.expenses.expense.entity.ExpenseEntity;

import jakarta.persistence.criteria.Predicate;

/** The Class Expense Specification. */
public final class ExpenseSpecification {

    private ExpenseSpecification() {
    }

    /**
     * Build specification for optional expense filters.
     *
     * @param categoryId the category id
     * @param expenseDate the expense date
     * @param description the description
     * @param movementType the movement type
     * @return the specification
     */
    public static Specification<ExpenseEntity> withFilters(
            final Integer categoryId,
            final LocalDate expenseDate,
            final String description,
            final MovementType movementType) {

        return (root, query, criteriaBuilder) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (categoryId != null) {
                predicates.add(criteriaBuilder.equal(root.get("categoryId"), categoryId));
            }
            if (expenseDate != null) {
                predicates.add(criteriaBuilder.equal(root.get("expenseDate"), expenseDate));
            }
            if (description != null && !description.isBlank()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("description")),
                        "%" + description.toLowerCase() + "%"));
            }
            if (movementType != null) {
                predicates.add(criteriaBuilder.equal(root.get("movementType"), movementType));
            }

            query.orderBy(
                    criteriaBuilder.desc(root.get("expenseDate")),
                    criteriaBuilder.desc(root.get("id")));

            if (predicates.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
