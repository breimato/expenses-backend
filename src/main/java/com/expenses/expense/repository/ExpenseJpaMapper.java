package com.expenses.expense.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.expenses.expense.entity.ExpenseEntity;

/** The Interface Expense Jpa Mapper. */
public interface ExpenseJpaMapper extends JpaRepository<ExpenseEntity, Integer>, JpaSpecificationExecutor<ExpenseEntity> {

    /**
     * Find by id and user id.
     *
     * @param id the id
     * @param userId the user id
     * @return the optional expense
     */
    Optional<ExpenseEntity> findByIdAndUserId(Integer id, Integer userId);

    /**
     * Sum net spending by expense date range for user.
     *
     * @param userId the user id
     * @param dateFrom the date from
     * @param dateTo the date to
     * @return the sum
     */
    @Query("""
            SELECT COALESCE(SUM(
                CASE
                    WHEN expenseEntity.movementType = com.expenses.common.MovementType.EXPENSE
                         AND expenseEntity.offsetsSpendingAverage = false THEN expenseEntity.amount
                    WHEN expenseEntity.movementType = com.expenses.common.MovementType.INCOME
                         AND expenseEntity.offsetsSpendingAverage = true THEN -expenseEntity.amount
                    ELSE 0
                END
            ), 0)
            FROM ExpenseEntity expenseEntity
            WHERE expenseEntity.userId = :userId
              AND expenseEntity.expenseDate >= :dateFrom
              AND expenseEntity.expenseDate <= :dateTo
            """)
    BigDecimal sumNetSpendingByDateRange(
            @Param("userId") Integer userId,
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo);

    /**
     * Sum net balance from all movements for user.
     *
     * @param userId the user id
     * @return the balance
     */
    @Query("""
            SELECT COALESCE(SUM(
                CASE
                    WHEN expenseEntity.movementType = com.expenses.common.MovementType.INCOME THEN expenseEntity.amount
                    WHEN expenseEntity.movementType = com.expenses.common.MovementType.EXPENSE THEN -expenseEntity.amount
                    ELSE 0
                END
            ), 0)
            FROM ExpenseEntity expenseEntity
            WHERE expenseEntity.userId = :userId
            """)
    BigDecimal sumNetBalance(@Param("userId") Integer userId);

    /**
     * Check if any expense exists for category.
     *
     * @param categoryId the category id
     * @return true if exists
     */
    boolean existsByCategoryId(Integer categoryId);
}
