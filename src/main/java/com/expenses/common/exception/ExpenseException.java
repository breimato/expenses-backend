package com.expenses.common.exception;

import org.springframework.http.HttpStatus;

/** The Class Expense Exception. */
public class ExpenseException extends ExpensesException {

    /**
     * Instantiates a new expense exception.
     *
     * @param message the message
     */
    public ExpenseException(final String message) {

        super(message, HttpStatus.NOT_FOUND);
    }
}
