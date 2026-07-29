package com.expenses.analytics.model;

import java.math.BigDecimal;

import lombok.Builder;

/** Analytics projections calculation result. */
@Builder
public record AnalyticsProjectionsResult(
        BigDecimal projectedMonthlyExpense,
        BigDecimal projectedEndOfMonthBalance,
        int daysRemainingInMonth) {
}
