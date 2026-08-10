package com.expenses.analytics.model;

import java.math.BigDecimal;

import lombok.Builder;

/** Analytics category spend item calculation result. */
@Builder
public record AnalyticsCategorySpendItemResult(
        Integer categoryId,
        String categoryName,
        String categoryColor,
        BigDecimal total,
        BigDecimal percent) {
}
