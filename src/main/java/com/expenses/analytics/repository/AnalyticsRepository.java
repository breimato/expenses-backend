package com.expenses.analytics.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.expenses.account.repository.AccountRepository;
import com.expenses.account.service.AccountAccessService;
import com.expenses.auth.repository.UserJpaMapper;
import com.expenses.auth.service.CurrentUserService;
import com.expenses.category.entity.CategoryEntity;
import com.expenses.category.repository.CategoryJpaMapper;
import com.expenses.common.exception.AuthException;
import com.expenses.common.exception.constants.ExceptionMessageConstants;
import com.expenses.expense.repository.CategorySpendAggregate;
import com.expenses.expense.repository.ExpenseJpaMapper;

import lombok.RequiredArgsConstructor;

/** The Class Analytics Repository. */
@Component
@RequiredArgsConstructor
public class AnalyticsRepository {

    /** The expense jpa mapper. */
    private final ExpenseJpaMapper expenseJpaMapper;

    /** The category jpa mapper. */
    private final CategoryJpaMapper categoryJpaMapper;

    /** The account repository. */
    private final AccountRepository accountRepository;

    /** The user jpa mapper. */
    private final UserJpaMapper userJpaMapper;

    /** The current user service. */
    private final CurrentUserService currentUserService;

    /** The account access service. */
    private final AccountAccessService accountAccessService;

    /**
     * Sum net spending in the given date range.
     *
     * @param accountId the account id
     * @param dateFrom the date from
     * @param dateTo the date to
     * @return the sum
     */
    @Transactional(readOnly = true)
    public BigDecimal sumNetSpendingByDateRange(
            final Integer accountId,
            final LocalDate dateFrom,
            final LocalDate dateTo) {

        this.accountAccessService.requireAccountAccess(accountId);
        return this.expenseJpaMapper.sumNetSpendingByDateRange(accountId, dateFrom, dateTo);
    }

    /**
     * Sum net spending in the given date range excluding categories.
     *
     * @param accountId the account id
     * @param dateFrom the date from
     * @param dateTo the date to
     * @param excludedCategoryIds the excluded category ids
     * @return the sum
     */
    @Transactional(readOnly = true)
    public BigDecimal sumNetSpendingByDateRangeExcludingCategories(
            final Integer accountId,
            final LocalDate dateFrom,
            final LocalDate dateTo,
            final List<Integer> excludedCategoryIds) {

        this.accountAccessService.requireAccountAccess(accountId);
        return this.expenseJpaMapper.sumNetSpendingByDateRangeExcludingCategories(
                accountId,
                dateFrom,
                dateTo,
                excludedCategoryIds);
    }

    /**
     * Find earliest movement date for the current user and account.
     *
     * @param accountId the account id
     * @return the optional first movement date
     */
    @Transactional(readOnly = true)
    public Optional<LocalDate> findFirstMovementDate(final Integer accountId) {

        this.accountAccessService.requireAccountAccess(accountId);
        return this.expenseJpaMapper.findMinExpenseDateByAccountId(accountId);
    }

    /**
     * Find earliest movement date for the current user and account within a date range.
     *
     * @param accountId the account id
     * @param dateFrom the date from
     * @param dateTo the date to
     * @return the optional first movement date in range
     */
    @Transactional(readOnly = true)
    public Optional<LocalDate> findFirstMovementDateInRange(
            final Integer accountId,
            final LocalDate dateFrom,
            final LocalDate dateTo) {

        this.accountAccessService.requireAccountAccess(accountId);
        return this.expenseJpaMapper.findMinExpenseDateByAccountIdAndExpenseDateBetween(
                accountId,
                dateFrom,
                dateTo);
    }

    /**
     * Get account creation date for the current user.
     *
     * @return the created at local date
     */
    @Transactional(readOnly = true)
    public LocalDate getCurrentUserCreatedOn() {

        final var userId = this.currentUserService.getRequiredUserId();
        final var userEntity = this.userJpaMapper.findById(userId)
                .orElseThrow(() -> new AuthException(
                        ExceptionMessageConstants.AUTH_UNAUTHORIZED,
                        HttpStatus.UNAUTHORIZED));
        return userEntity.getCreatedAt().toLocalDate();
    }

    /**
     * Sum net balance as of a date for the current user and account.
     *
     * @param accountId the account id
     * @param dateTo the inclusive end date
     * @return the balance
     */
    @Transactional(readOnly = true)
    public BigDecimal sumNetBalanceAsOf(final Integer accountId, final LocalDate dateTo) {

        this.accountAccessService.requireAccountAccess(accountId);
        return this.expenseJpaMapper.sumNetBalanceAsOf(accountId, dateTo);
    }

    /**
     * Sum expenses by category for current user and account in date range.
     *
     * @param accountId the account id
     * @param dateFrom the date from
     * @param dateTo the date to
     * @return the aggregates
     */
    @Transactional(readOnly = true)
    public List<CategorySpendAggregate> sumExpenseByCategory(
            final Integer accountId,
            final LocalDate dateFrom,
            final LocalDate dateTo) {

        this.accountAccessService.requireAccountAccess(accountId);
        return this.expenseJpaMapper.sumExpenseByCategory(accountId, dateFrom, dateTo);
    }

    /**
     * Find categories for current user and account.
     *
     * @param accountId the account id
     * @return the category entities
     */
    @Transactional(readOnly = true)
    public List<CategoryEntity> findCategoriesForAccount(final Integer accountId) {

        this.accountAccessService.requireAccountAccess(accountId);
        return this.categoryJpaMapper.findByAccountId(accountId);
    }

    /**
     * Get account balance.
     *
     * @param accountId the account id
     * @return the account balance
     */
    @Transactional(readOnly = true)
    public BigDecimal getAccountBalance(final Integer accountId) {

        return this.accountRepository.getBalance(accountId);
    }
}
