package com.expenses.analytics.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expenses.analytics.model.AnalyticsAveragesResult;
import com.expenses.analytics.model.AnalyticsCategoryBreakdownResult;
import com.expenses.analytics.model.AnalyticsCategorySpendItemResult;
import com.expenses.analytics.model.AnalyticsProjectionsResult;
import com.expenses.analytics.repository.AnalyticsRepository;
import com.expenses.category.entity.CategoryEntity;

import lombok.RequiredArgsConstructor;

/** The Class Analytics Service. */
@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private static final int MONEY_SCALE = 2;
    private static final int CALCULATION_SCALE = 10;
    private static final String UNKNOWN_CATEGORY_NAME = "Sin categoría";
    private static final String UNKNOWN_CATEGORY_COLOR = "#6B7280";

    /** The analytics repository. */
    private final AnalyticsRepository analyticsRepository;

    /**
     * Compute daily average net spending for the current month ending on the reference date.
     * Days counted start at the first movement of the account within the month (not day 1).
     *
     * @param referenceDate the reference date
     * @return the analytics averages result
     */
    @Transactional(readOnly = true)
    public AnalyticsAveragesResult computeAverages(final LocalDate referenceDate) {

        final var monthStart = referenceDate.withDayOfMonth(1);
        final var periodStart = this.resolvePeriodStart(monthStart, referenceDate);
        final var mtdTotal = this.analyticsRepository.sumNetSpendingByDateRange(monthStart, referenceDate);
        final var daysElapsed = this.countInclusiveDays(periodStart, referenceDate);

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
        final var periodStart = this.resolvePeriodStart(monthStart, referenceDate);
        final var expensesThisMonth = this.analyticsRepository.sumNetSpendingByDateRange(monthStart, referenceDate);
        final var daysElapsed = this.countInclusiveDays(periodStart, referenceDate);
        final var dailyRate = daysElapsed <= 0
                ? BigDecimal.ZERO
                : expensesThisMonth.divide(BigDecimal.valueOf(daysElapsed), CALCULATION_SCALE, RoundingMode.HALF_UP);

        final var daysRemaining = referenceDate.lengthOfMonth() - referenceDate.getDayOfMonth();
        final var activeDaysInMonth = this.countInclusiveDays(periodStart, referenceDate.withDayOfMonth(referenceDate.lengthOfMonth()));

        final var projectedMonthlyExpense = dailyRate
                .multiply(BigDecimal.valueOf(Math.max(activeDaysInMonth, 0)))
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

    /**
     * Compute monthly expense breakdown by category.
     *
     * @param referenceDate the reference date
     * @return the category breakdown result
     */
    @Transactional(readOnly = true)
    public AnalyticsCategoryBreakdownResult computeCategoryBreakdown(final LocalDate referenceDate) {

        final var monthStart = referenceDate.withDayOfMonth(1);
        final var categorySpendAggregates = this.analyticsRepository.sumExpenseByCategory(monthStart, referenceDate);
        final var categoryEntityById = this.analyticsRepository.findCategoriesForCurrentUser().stream()
                .collect(Collectors.toMap(CategoryEntity::getId, Function.identity()));
        var totalSpent = BigDecimal.ZERO.setScale(MONEY_SCALE, RoundingMode.HALF_UP);
        for (final var categorySpendAggregate : categorySpendAggregates) {
            totalSpent = totalSpent.add(Objects.requireNonNullElse(categorySpendAggregate.getTotal(), BigDecimal.ZERO));
        }
        final var analyticsCategorySpendItemResults = new ArrayList<AnalyticsCategorySpendItemResult>();
        for (final var categorySpendAggregate : categorySpendAggregates) {
            final var categoryEntity = categoryEntityById.get(categorySpendAggregate.getCategoryId());
            final var total = Objects.requireNonNullElse(categorySpendAggregate.getTotal(), BigDecimal.ZERO)
                    .setScale(MONEY_SCALE, RoundingMode.HALF_UP);
            final var percent = totalSpent.compareTo(BigDecimal.ZERO) == 0
                    ? BigDecimal.ZERO.setScale(MONEY_SCALE, RoundingMode.HALF_UP)
                    : total.multiply(BigDecimal.valueOf(100))
                            .divide(totalSpent, MONEY_SCALE, RoundingMode.HALF_UP);
            analyticsCategorySpendItemResults.add(AnalyticsCategorySpendItemResult.builder()
                    .categoryId(categorySpendAggregate.getCategoryId())
                    .categoryName(Objects.nonNull(categoryEntity) ? categoryEntity.getName() : UNKNOWN_CATEGORY_NAME)
                    .categoryColor(Objects.nonNull(categoryEntity) ? categoryEntity.getColor() : UNKNOWN_CATEGORY_COLOR)
                    .total(total)
                    .percent(percent)
                    .build());
        }
        return AnalyticsCategoryBreakdownResult.builder()
                .totalSpent(totalSpent)
                .items(analyticsCategorySpendItemResults)
                .build();
    }

    private LocalDate resolvePeriodStart(final LocalDate monthStart, final LocalDate referenceDate) {

        final var firstMovementDate = this.analyticsRepository.findFirstMovementDate().orElse(null);
        if (Objects.isNull(firstMovementDate)) {
            return referenceDate;
        }
        if (firstMovementDate.isAfter(referenceDate)) {
            return referenceDate;
        }
        if (firstMovementDate.isBefore(monthStart)) {
            return monthStart;
        }
        return firstMovementDate;
    }

    private int countInclusiveDays(final LocalDate periodStart, final LocalDate referenceDate) {

        if (Objects.isNull(periodStart) || periodStart.isAfter(referenceDate)) {
            return 0;
        }
        return (int) ChronoUnit.DAYS.between(periodStart, referenceDate) + 1;
    }

    private BigDecimal divide(final BigDecimal total, final int divisor) {

        if (divisor <= 0) {
            return BigDecimal.ZERO.setScale(MONEY_SCALE, RoundingMode.HALF_UP);
        }
        return total.divide(BigDecimal.valueOf(divisor), CALCULATION_SCALE, RoundingMode.HALF_UP)
                .setScale(MONEY_SCALE, RoundingMode.HALF_UP);
    }
}
