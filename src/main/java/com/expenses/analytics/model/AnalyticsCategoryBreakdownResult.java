package com.expenses.analytics.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;

/** Analytics category breakdown calculation result. */
@Builder
public record AnalyticsCategoryBreakdownResult(
        BigDecimal totalSpent,
        List<AnalyticsCategorySpendItemResult> items) {
}
