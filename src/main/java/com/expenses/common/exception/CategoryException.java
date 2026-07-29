package com.expenses.common.exception;

import org.springframework.http.HttpStatus;

/** The Class Category Exception. */
public class CategoryException extends ExpensesException {

    /**
     * Instantiates a new category exception.
     *
     * @param message the message
     */
    public CategoryException(final String message) {

        super(message, HttpStatus.NOT_FOUND);
    }

    /**
     * Instantiates a new category exception with custom status.
     *
     * @param message the message
     * @param httpStatus the http status
     */
    public CategoryException(final String message, final HttpStatus httpStatus) {

        super(message, httpStatus);
    }
}
