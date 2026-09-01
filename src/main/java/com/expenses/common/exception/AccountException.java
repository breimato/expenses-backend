package com.expenses.common.exception;

import org.springframework.http.HttpStatus;

/** The Class Account Exception. */
public class AccountException extends ExpensesException {

    /**
     * Instantiates a new account exception.
     *
     * @param message the message
     */
    public AccountException(final String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    /**
     * Instantiates a new account exception.
     *
     * @param message the message
     * @param httpStatus the http status
     */
    public AccountException(final String message, final HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
