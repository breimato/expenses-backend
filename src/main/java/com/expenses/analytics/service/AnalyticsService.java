package com.expenses.analytics.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expenses.analytics.model.AnalyticsAveragesResult;
import com.expenses.analytics.model.AnalyticsProjectionsResult;
import com.expenses.analytics.repository.AnalyticsRepository;

import lombok.RequiredArgsConstructor;

/** The Class Analytics Service. */
@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private static final int MONEY_SCALE = 2;
    private static final int CALCULATION_SCALE = 10;

    /** The analytics repository. */
    private final AnalyticsRepository analyticsRepository;

    /**
     * Compute daily average net spending for the current month ending on the reference date.
     *
     * @param referenceDate the reference date
     * @return the analytics averages result
     */
    @Transactional(readOnly = true)
    public AnalyticsAveragesResult computeAverages(final LocalDate referenceDate) {

        final var monthStart = referenceDate.withDayOfMonth(1);
        final var mtdTotal = this.analyticsRepository.sumNetSpendingByDateRange(monthStart, referenceDate);
        final var daysElapsed = referenceDate.getDayOfMonth();

        return AnalyticsAveragesResult.builder()
                .dailyAverage(this.divide(mtdTotal, daysElapsed))
                .build();
    }

    /**
     * Compute month-end projections based on current month spending pace.
     *
     * @param referenceDate the reference date
     * @return the analytics projections result
     */
    @Transactional(readOnly = true)
    public AnalyticsProjectionsResult computeProjections(final LocalDate referenceDate) {

        final var monthStart = referenceDate.withDayOfMonth(1);
        final var expensesThisMonth = this.analyticsRepository.sumNetSpendingByDateRange(monthStart, referenceDate);
        final var dailyRate = expensesThisMonth.divide(BigDecimal.valueOf(referenceDate.getDayOfMonth()), CALCULATION_SCALE, RoundingMode.HALF_UP);

        final var daysRemaining = referenceDate.lengthOfMonth() - referenceDate.getDayOfMonth();

        final var projectedMonthlyExpense = dailyRate
                .multiply(BigDecimal.valueOf(referenceDate.lengthOfMonth()))
                .setScale(MONEY_SCALE, RoundingMode.HALF_UP);

        final var projectedRemainingSpend = dailyRate
                .multiply(BigDecimal.valueOf(daysRemaining))
                .setScale(MONEY_SCALE, RoundingMode.HALF_UP);

        final var profileBalance = this.analyticsRepository.getProfileBalance();
        final var projectedEndOfMonthBalance = Objects.requireNonNullElse(profileBalance, BigDecimal.ZERO)
                .subtract(projectedRemainingSpend)
                .setScale(MONEY_SCALE, RoundingMode.HALF_UP);

        return AnalyticsProjectionsResult.builder()
                .projectedMonthlyExpense(projectedMonthlyExpense)
                .projectedEndOfMonthBalance(projectedEndOfMonthBalance)
                .daysRemainingInMonth(daysRemaining)
                .build();
    }

    private BigDecimal divide(final BigDecimal total, final int divisor) {

        if (divisor <= 0) {
            return BigDecimal.ZERO.setScale(MONEY_SCALE, RoundingMode.HALF_UP);
        }
        return total.divide(BigDecimal.valueOf(divisor), CALCULATION_SCALE, RoundingMode.HALF_UP)
                .setScale(MONEY_SCALE, RoundingMode.HALF_UP);
    }
}
