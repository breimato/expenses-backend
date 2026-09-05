package com.expenses.expense.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
     * @param accountId the account id
     * @param categoryId the category id
     * @param expenseDate the expense date
     * @param description the description
     * @param movementType the movement type
     * @return the specification
     */
    public static Specification<ExpenseEntity> withFilters(
            final Integer accountId,
            final Integer categoryId,
            final LocalDate expenseDate,
            final String description,
            final MovementType movementType) {

        return (root, query, criteriaBuilder) -> {
            final List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("accountId"), accountId));
            if (Objects.nonNull(categoryId)) {
                predicates.add(criteriaBuilder.equal(root.get("categoryId"), categoryId));
            }
            if (Objects.nonNull(expenseDate)) {
                predicates.add(criteriaBuilder.equal(root.get("expenseDate"), expenseDate));
            }
            if (Objects.nonNull(description) && !description.isBlank()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("description")),
                        "%" + description.toLowerCase() + "%"));
            }
            if (Objects.nonNull(movementType)) {
                predicates.add(criteriaBuilder.equal(root.get("movementType"), movementType));
            }
            query.orderBy(
                    criteriaBuilder.desc(root.get("expenseDate")),
                    criteriaBuilder.desc(root.get("id")));
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
