package com.expenses.analytics.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expenses.analytics.model.AnalyticsAveragesResult;
import com.expenses.analytics.model.AnalyticsCategoryBreakdownResult;
import com.expenses.analytics.model.AnalyticsCategorySpendItemResult;
import com.expenses.analytics.model.AnalyticsPeriodAverageResult;
import com.expenses.analytics.model.AnalyticsProjectionsResult;
import com.expenses.analytics.repository.AnalyticsRepository;
import com.expenses.category.entity.CategoryEntity;
import com.expenses.common.exception.ExpenseException;
import com.expenses.common.exception.constants.ExceptionMessageConstants;
import com.expenses.expense.repository.CategorySpendAggregate;

import lombok.RequiredArgsConstructor;

/** The Class Analytics Service. */
@Service
@RequiredArgsConstructor
public class AnalyticsService {

    /** The Constant MONEY_SCALE. */
    private static final int MONEY_SCALE = 2;

    /** The Constant CALCULATION_SCALE. */
    private static final int CALCULATION_SCALE = 10;

    /** The Constant UNKNOWN_CATEGORY_NAME. */
    private static final String UNKNOWN_CATEGORY_NAME = "Sin categoría";

    /** The Constant UNKNOWN_CATEGORY_COLOR. */
    private static final String UNKNOWN_CATEGORY_COLOR = "#6B7280";

    /** The analytics repository. */
    private final AnalyticsRepository analyticsRepository;

    /**
     * Compute daily average net spending for the current month ending on the reference date.
     * Onboarding month starts at the first movement that month; later months start on day 1.
     *
     * @param referenceDate the reference date
     * @return the analytics averages result
     */
    @Transactional(readOnly = true)
    public AnalyticsAveragesResult computeAverages(final LocalDate referenceDate) {

        final var monthSpendingPace = this.resolveMonthSpendingPace(referenceDate);
        final var balanceAsOf = Objects.requireNonNullElse(
                this.analyticsRepository.sumNetBalanceAsOf(referenceDate),
                BigDecimal.ZERO);
        return AnalyticsAveragesResult.builder()
                .dailyAverage(this.divide(monthSpendingPace.netSpending(), monthSpendingPace.daysElapsed()))
                .balanceAsOf(balanceAsOf.setScale(MONEY_SCALE, RoundingMode.HALF_UP))
                .build();
    }

    /**
     * Compute daily average net spending for an inclusive date range.
     *
     * @param dateFrom the inclusive start date
     * @param dateTo the inclusive end date
     * @return the analytics period average result
     */
    @Transactional(readOnly = true)
    public AnalyticsPeriodAverageResult computePeriodAverage(final LocalDate dateFrom, final LocalDate dateTo) {

        if (Objects.isNull(dateFrom) || Objects.isNull(dateTo) || dateFrom.isAfter(dateTo)) {
            throw new ExpenseException(ExceptionMessageConstants.ANALYTICS_INVALID_DATE_RANGE, HttpStatus.BAD_REQUEST);
        }
        final var totalNetSpending = Objects.requireNonNullElse(
                this.analyticsRepository.sumNetSpendingByDateRange(dateFrom, dateTo),
                BigDecimal.ZERO);
        final var daysInPeriod = this.countInclusiveDays(dateFrom, dateTo);
        return AnalyticsPeriodAverageResult.builder()
                .dailyAverage(this.divide(totalNetSpending, daysInPeriod))
                .totalNetSpending(totalNetSpending.setScale(MONEY_SCALE, RoundingMode.HALF_UP))
                .daysInPeriod(daysInPeriod)
                .dateFrom(dateFrom)
                .dateTo(dateTo)
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

        final var monthSpendingPace = this.resolveMonthSpendingPace(referenceDate);

        final var dailyAverage = this.divideUnscaled(monthSpendingPace.netSpending(), monthSpendingPace.daysElapsed());

        final var projectedMonthlyExpense = dailyAverage
                .multiply(BigDecimal.valueOf(Math.max(monthSpendingPace.activeDaysInMonth(), 0)))
                .setScale(MONEY_SCALE, RoundingMode.HALF_UP);

        final var projectedEndOfMonthBalance = this.getProjectedEndOfMonthBalance(dailyAverage, monthSpendingPace);

        return AnalyticsProjectionsResult.builder()
                .projectedMonthlyExpense(projectedMonthlyExpense)
                .projectedEndOfMonthBalance(projectedEndOfMonthBalance)
                .daysRemainingInMonth(monthSpendingPace.daysRemainingInMonth())
                .build();
    }

    /**
     * Get projected end of month balance.
     *
     * @param dailyAverage the daily average
     * @param monthSpendingPace the month spending pace
     * @return the projected end of month balance
     */
    private BigDecimal getProjectedEndOfMonthBalance(
            final BigDecimal dailyAverage, final MonthSpendingPace monthSpendingPace) {

        final var projectedRemainingSpend = dailyAverage
                .multiply(BigDecimal.valueOf(monthSpendingPace.daysRemainingInMonth()))
                .setScale(MONEY_SCALE, RoundingMode.HALF_UP);

        final var profileBalance = this.analyticsRepository.getProfileBalance();

        return Objects.requireNonNullElse(profileBalance, BigDecimal.ZERO)
                .subtract(projectedRemainingSpend)
                .setScale(MONEY_SCALE, RoundingMode.HALF_UP);
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

        final var totalSpent = this.sumCategoryTotals(categorySpendAggregates);

        final var analyticsCategorySpendItemResults = categorySpendAggregates.stream()
                .map(categorySpendAggregate -> this.toAnalyticsCategorySpendItemResult(
                        categorySpendAggregate,
                        categoryEntityById,
                        totalSpent))
                .toList();

        return AnalyticsCategoryBreakdownResult.builder()
                .totalSpent(totalSpent)
                .items(analyticsCategorySpendItemResults)
                .build();
    }

    /**
     * Resolve month spending pace for the reference date.
     *
     * @param referenceDate the reference date
     * @return the month spending pace
     */
    private MonthSpendingPace resolveMonthSpendingPace(final LocalDate referenceDate) {

        final var monthStart = referenceDate.withDayOfMonth(1);
        final var periodStart = this.resolvePeriodStart(monthStart, referenceDate);
        final var netSpending = this.analyticsRepository.sumNetSpendingByDateRange(monthStart, referenceDate);
        final var daysElapsed = this.countInclusiveDays(periodStart, referenceDate);
        final var activeDaysInMonth = this.countInclusiveDays(
                periodStart,
                referenceDate.withDayOfMonth(referenceDate.lengthOfMonth()));
        final var daysRemainingInMonth = referenceDate.lengthOfMonth() - referenceDate.getDayOfMonth();
        return new MonthSpendingPace(netSpending, daysElapsed, activeDaysInMonth, daysRemainingInMonth);
    }

    /**
     * Resolve period start: first movement in onboarding month, otherwise day 1.
     *
     * @param monthStart the month start
     * @param referenceDate the reference date
     * @return the period start
     */
    private LocalDate resolvePeriodStart(final LocalDate monthStart, final LocalDate referenceDate) {

        final var onboardingMonth = YearMonth.from(this.analyticsRepository.getCurrentUserCreatedOn());
        if (!YearMonth.from(referenceDate).equals(onboardingMonth)) {
            return monthStart;
        }
        return this.analyticsRepository.findFirstMovementDateInRange(monthStart, referenceDate)
                .orElse(referenceDate);
    }

    /**
     * Sum category totals.
     *
     * @param categorySpendAggregates the category spend aggregates
     * @return the total spent
     */
    private BigDecimal sumCategoryTotals(final List<CategorySpendAggregate> categorySpendAggregates) {

        return categorySpendAggregates.stream()
                .map(this::normalizeTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(MONEY_SCALE, RoundingMode.HALF_UP);
    }

    /**
     * To analytics category spend item result.
     *
     * @param categorySpendAggregate the category spend aggregate
     * @param categoryEntityById the category entity by id
     * @param totalSpent the total spent
     * @return the analytics category spend item result
     */
    private AnalyticsCategorySpendItemResult toAnalyticsCategorySpendItemResult(
            final CategorySpendAggregate categorySpendAggregate,
            final Map<Integer, CategoryEntity> categoryEntityById,
            final BigDecimal totalSpent) {

        final var categoryEntity = categoryEntityById.get(categorySpendAggregate.getCategoryId());
        final var total = this.normalizeTotal(categorySpendAggregate).setScale(MONEY_SCALE, RoundingMode.HALF_UP);
        return AnalyticsCategorySpendItemResult.builder()
                .categoryId(categorySpendAggregate.getCategoryId())
                .categoryName(this.resolveCategoryName(categoryEntity))
                .categoryColor(this.resolveCategoryColor(categoryEntity))
                .total(total)
                .percent(this.calculatePercent(total, totalSpent))
                .build();
    }

    /**
     * Normalize total.
     *
     * @param categorySpendAggregate the category spend aggregate
     * @return the normalized total
     */
    private BigDecimal normalizeTotal(final CategorySpendAggregate categorySpendAggregate) {

        return Objects.requireNonNullElse(categorySpendAggregate.getTotal(), BigDecimal.ZERO)
                .max(BigDecimal.ZERO);
    }

    /**
     * Resolve category name.
     *
     * @param categoryEntity the category entity
     * @return the category name
     */
    private String resolveCategoryName(final CategoryEntity categoryEntity) {

        if (Objects.isNull(categoryEntity)) {
            return UNKNOWN_CATEGORY_NAME;
        }
        return categoryEntity.getName();
    }

    /**
     * Resolve category color.
     *
     * @param categoryEntity the category entity
     * @return the category color
     */
    private String resolveCategoryColor(final CategoryEntity categoryEntity) {

        if (Objects.isNull(categoryEntity)) {
            return UNKNOWN_CATEGORY_COLOR;
        }
        return categoryEntity.getColor();
    }

    /**
     * Calculate percent.
     *
     * @param total the total
     * @param totalSpent the total spent
     * @return the percent
     */
    private BigDecimal calculatePercent(final BigDecimal total, final BigDecimal totalSpent) {

        if (totalSpent.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO.setScale(MONEY_SCALE, RoundingMode.HALF_UP);
        }
        return total.multiply(BigDecimal.valueOf(100))
                .divide(totalSpent, MONEY_SCALE, RoundingMode.HALF_UP);
    }

    /**
     * Count inclusive days.
     *
     * @param periodStart the period start
     * @param referenceDate the reference date
     * @return the inclusive days
     */
    private Integer countInclusiveDays(final LocalDate periodStart, final LocalDate referenceDate) {

        if (Objects.isNull(periodStart) || periodStart.isAfter(referenceDate)) {
            return 0;
        }
        return (int) ChronoUnit.DAYS.between(periodStart, referenceDate) + 1;
    }

    /**
     * Divide total by divisor with money scale.
     *
     * @param total the total
     * @param divisor the divisor
     * @return the scaled quotient
     */
    private BigDecimal divide(final BigDecimal total, final Integer divisor) {

        return this.divideUnscaled(total, divisor).setScale(MONEY_SCALE, RoundingMode.HALF_UP);
    }

    /**
     * Divide total by divisor without money scale.
     *
     * @param total the total
     * @param divisor the divisor
     * @return the unscaled quotient
     */
    private BigDecimal divideUnscaled(final BigDecimal total, final Integer divisor) {

        if (Objects.isNull(divisor) || divisor <= 0) {
            return BigDecimal.ZERO;
        }
        return total.divide(BigDecimal.valueOf(divisor), CALCULATION_SCALE, RoundingMode.HALF_UP);
    }

    /** Month spending pace calculation context. */
    private record MonthSpendingPace(
            BigDecimal netSpending,
            Integer daysElapsed,
            Integer activeDaysInMonth,
            Integer daysRemainingInMonth) {
    }
}
