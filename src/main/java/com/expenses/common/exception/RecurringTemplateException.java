package com.expenses.common.exception;

import org.springframework.http.HttpStatus;

/** The Class Recurring Template Exception. */
public class RecurringTemplateException extends ExpensesException {

    /**
     * Instantiates a new recurring template exception.
     *
     * @param message the message
     */
    public RecurringTemplateException(final String message) {

        super(message, HttpStatus.NOT_FOUND);
    }
}
