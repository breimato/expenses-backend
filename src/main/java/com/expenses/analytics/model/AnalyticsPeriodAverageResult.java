package com.expenses.analytics.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;

/** Analytics period average calculation result. */
@Builder
public record AnalyticsPeriodAverageResult(
        BigDecimal dailyAverage,
        BigDecimal totalNetSpending,
        Integer daysInPeriod,
        LocalDate dateFrom,
        LocalDate dateTo) {
}
