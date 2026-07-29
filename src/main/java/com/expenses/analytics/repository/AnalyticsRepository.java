package com.expenses.analytics.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.expenses.expense.repository.ExpenseJpaMapper;
import com.expenses.profile.repository.ProfileRepository;

import lombok.RequiredArgsConstructor;

/** The Class Analytics Repository. */
@Component
@RequiredArgsConstructor
public class AnalyticsRepository {

    /** The expense jpa mapper. */
    private final ExpenseJpaMapper expenseJpaMapper;

    /** The profile repository. */
    private final ProfileRepository profileRepository;

    /**
     * Sum net spending in the given date range.
     *
     * @param dateFrom the date from
     * @param dateTo the date to
     * @return the sum
     */
    @Transactional(readOnly = true)
    public BigDecimal sumNetSpendingByDateRange(final LocalDate dateFrom, final LocalDate dateTo) {

        return this.expenseJpaMapper.sumNetSpendingByDateRange(dateFrom, dateTo);
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
