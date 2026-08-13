package com.expenses.analytics.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.expenses.auth.repository.UserJpaMapper;
import com.expenses.auth.service.CurrentUserService;
import com.expenses.category.entity.CategoryEntity;
import com.expenses.category.repository.CategoryJpaMapper;
import com.expenses.common.exception.AuthException;
import com.expenses.common.exception.constants.ExceptionMessageConstants;
import com.expenses.expense.repository.CategorySpendAggregate;
import com.expenses.expense.repository.ExpenseJpaMapper;
import com.expenses.profile.repository.ProfileRepository;

import lombok.RequiredArgsConstructor;

/** The Class Analytics Repository. */
@Component
@RequiredArgsConstructor
public class AnalyticsRepository {

    /** The expense jpa mapper. */
    private final ExpenseJpaMapper expenseJpaMapper;

    /** The category jpa mapper. */
    private final CategoryJpaMapper categoryJpaMapper;

    /** The profile repository. */
    private final ProfileRepository profileRepository;

    /** The user jpa mapper. */
    private final UserJpaMapper userJpaMapper;

    /** The current user service. */
    private final CurrentUserService currentUserService;

    /**
     * Sum net spending in the given date range.
     *
     * @param dateFrom the date from
     * @param dateTo the date to
     * @return the sum
     */
    @Transactional(readOnly = true)
    public BigDecimal sumNetSpendingByDateRange(final LocalDate dateFrom, final LocalDate dateTo) {

        return this.expenseJpaMapper.sumNetSpendingByDateRange(
                this.currentUserService.getRequiredUserId(),
                dateFrom,
                dateTo);
    }

    /**
     * Find earliest movement date for the current user.
     *
     * @return the optional first movement date
     */
    @Transactional(readOnly = true)
    public Optional<LocalDate> findFirstMovementDate() {

        return this.expenseJpaMapper.findMinExpenseDateByUserId(this.currentUserService.getRequiredUserId());
    }

    /**
     * Find earliest movement date for the current user within a date range.
     *
     * @param dateFrom the date from
     * @param dateTo the date to
     * @return the optional first movement date in range
     */
    @Transactional(readOnly = true)
    public Optional<LocalDate> findFirstMovementDateInRange(final LocalDate dateFrom, final LocalDate dateTo) {

        return this.expenseJpaMapper.findMinExpenseDateByUserIdAndExpenseDateBetween(
                this.currentUserService.getRequiredUserId(),
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
     * Sum net balance as of a date for the current user.
     *
     * @param dateTo the inclusive end date
     * @return the balance
     */
    @Transactional(readOnly = true)
    public BigDecimal sumNetBalanceAsOf(final LocalDate dateTo) {

        return this.expenseJpaMapper.sumNetBalanceAsOf(this.currentUserService.getRequiredUserId(), dateTo);
    }

    /**
     * Sum expenses by category for current user in date range.
     *
     * @param dateFrom the date from
     * @param dateTo the date to
     * @return the aggregates
     */
    @Transactional(readOnly = true)
    public List<CategorySpendAggregate> sumExpenseByCategory(final LocalDate dateFrom, final LocalDate dateTo) {

        return this.expenseJpaMapper.sumExpenseByCategory(
                this.currentUserService.getRequiredUserId(),
                dateFrom,
                dateTo);
    }

    /**
     * Find categories for current user.
     *
     * @return the category entities
     */
    @Transactional(readOnly = true)
    public List<CategoryEntity> findCategoriesForCurrentUser() {

        return this.categoryJpaMapper.findByUserId(this.currentUserService.getRequiredUserId());
    }

    /**
     * Get current profile balance.
     *
     * @return the profile balance
     */
    @Transactional(readOnly = true)
    public BigDecimal getProfileBalance() {

        return this.profileRepository.getBalance();
    }
}
