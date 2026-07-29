package com.expenses.analytics.model;

import java.math.BigDecimal;

import lombok.Builder;

/** Analytics averages calculation result. */
@Builder
public record AnalyticsAveragesResult(
        BigDecimal dailyAverage,
        BigDecimal weeklyAverage,
        BigDecimal monthlyAverage,
        BigDecimal yearlyAverage) {
}
