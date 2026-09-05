package com.expenses.expense.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.expenses.expense.entity.ExpenseEntity;

/** The Interface Expense Jpa Mapper. */
public interface ExpenseJpaMapper extends JpaRepository<ExpenseEntity, Integer>, JpaSpecificationExecutor<ExpenseEntity> {

    /**
     * Find by id and account id.
     *
     * @param id the id
     * @param accountId the account id
     * @return the optional expense
     */
    Optional<ExpenseEntity> findByIdAndAccountId(Integer id, Integer accountId);

    /**
     * Find all by transfer id.
     *
     * @param transferId the transfer id
     * @return the list
     */
    List<ExpenseEntity> findAllByTransferId(Integer transferId);

    /**
     * Sum net spending in date range for account.
     *
     * @param accountId the account id
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
            WHERE expenseEntity.accountId = :accountId
              AND expenseEntity.expenseDate >= :dateFrom
              AND expenseEntity.expenseDate <= :dateTo
            """)
    BigDecimal sumNetSpendingByDateRange(
            @Param("accountId") Integer accountId,
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo);

    /**
     * Sum net balance from all movements for account.
     *
     * @param accountId the account id
     * @return the balance
     */
    @Query("""
            SELECT COALESCE(SUM(
                CASE
                    WHEN expenseEntity.movementType = com.expenses.common.MovementType.INCOME THEN expenseEntity.amount
                    WHEN expenseEntity.movementType = com.expenses.common.MovementType.EXPENSE THEN -expenseEntity.amount
                    WHEN expenseEntity.movementType = com.expenses.common.MovementType.TRANSFER_IN THEN expenseEntity.amount
                    WHEN expenseEntity.movementType = com.expenses.common.MovementType.TRANSFER_OUT THEN -expenseEntity.amount
                    ELSE 0
                END
            ), 0)
            FROM ExpenseEntity expenseEntity
            WHERE expenseEntity.accountId = :accountId
            """)
    BigDecimal sumNetBalance(@Param("accountId") Integer accountId);

    /**
     * Sum net balance from movements on or before the given date.
     *
     * @param accountId the account id
     * @param dateTo the inclusive end date
     * @return the balance as of date
     */
    @Query("""
            SELECT COALESCE(SUM(
                CASE
                    WHEN expenseEntity.movementType = com.expenses.common.MovementType.INCOME THEN expenseEntity.amount
                    WHEN expenseEntity.movementType = com.expenses.common.MovementType.EXPENSE THEN -expenseEntity.amount
                    WHEN expenseEntity.movementType = com.expenses.common.MovementType.TRANSFER_IN THEN expenseEntity.amount
                    WHEN expenseEntity.movementType = com.expenses.common.MovementType.TRANSFER_OUT THEN -expenseEntity.amount
                    ELSE 0
                END
            ), 0)
            FROM ExpenseEntity expenseEntity
            WHERE expenseEntity.accountId = :accountId
              AND expenseEntity.expenseDate <= :dateTo
            """)
    BigDecimal sumNetBalanceAsOf(
            @Param("accountId") Integer accountId,
            @Param("dateTo") LocalDate dateTo);

    /**
     * Find the earliest movement date for account.
     *
     * @param accountId the account id
     * @return the optional earliest expense date
     */
    @Query("""
            SELECT MIN(expenseEntity.expenseDate)
            FROM ExpenseEntity expenseEntity
            WHERE expenseEntity.accountId = :accountId
            """)
    Optional<LocalDate> findMinExpenseDateByAccountId(@Param("accountId") Integer accountId);

    /**
     * Find the earliest movement date for account within a date range.
     *
     * @param accountId the account id
     * @param dateFrom the date from
     * @param dateTo the date to
     * @return the optional earliest expense date in range
     */
    @Query("""
            SELECT MIN(expenseEntity.expenseDate)
            FROM ExpenseEntity expenseEntity
            WHERE expenseEntity.accountId = :accountId
              AND expenseEntity.expenseDate >= :dateFrom
              AND expenseEntity.expenseDate <= :dateTo
            """)
    Optional<LocalDate> findMinExpenseDateByAccountIdAndExpenseDateBetween(
            @Param("accountId") Integer accountId,
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo);

    /**
     * Sum expense amounts by category for account in date range.
     *
     * @param accountId the account id
     * @param dateFrom the date from
     * @param dateTo the date to
     * @return the aggregates
     */
    @Query("""
            SELECT expenseEntity.categoryId AS categoryId,
                   COALESCE(SUM(expenseEntity.amount), 0)
                       - COALESCE(SUM((
                           SELECT COALESCE(SUM(reimbursement.amount), 0)
                           FROM ExpenseEntity reimbursement
                           WHERE reimbursement.reimbursedExpenseId = expenseEntity.id
                       )), 0) AS total
            FROM ExpenseEntity expenseEntity
            WHERE expenseEntity.accountId = :accountId
              AND expenseEntity.expenseDate >= :dateFrom
              AND expenseEntity.expenseDate <= :dateTo
              AND expenseEntity.movementType = com.expenses.common.MovementType.EXPENSE
            GROUP BY expenseEntity.categoryId
            ORDER BY total DESC
            """)
    List<CategorySpendAggregate> sumExpenseByCategory(
            @Param("accountId") Integer accountId,
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo);

    /**
     * Check if any expense exists for category.
     *
     * @param categoryId the category id
     * @return true if exists
     */
    boolean existsByCategoryId(Integer categoryId);
}
