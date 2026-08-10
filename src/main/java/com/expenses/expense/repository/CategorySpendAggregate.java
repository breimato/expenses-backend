package com.expenses.expense.repository;

import java.math.BigDecimal;

/** Aggregate spending by category. */
public interface CategorySpendAggregate {

    /**
     * Gets category id.
     *
     * @return the category id
     */
    Integer getCategoryId();

    /**
     * Gets total.
     *
     * @return the total
     */
    BigDecimal getTotal();
}
