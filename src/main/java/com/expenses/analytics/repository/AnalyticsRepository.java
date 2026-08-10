package com.expenses.analytics.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.expenses.auth.service.CurrentUserService;
import com.expenses.category.entity.CategoryEntity;
import com.expenses.category.repository.CategoryJpaMapper;
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
