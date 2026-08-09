package com.expenses.common.exception;

import org.springframework.http.HttpStatus;

/** The Class Auth Exception. */
public class AuthException extends ExpensesException {

    /**
     * Instantiates a new auth exception.
     *
     * @param message the message
     * @param httpStatus the http status
     */
    public AuthException(final String message, final HttpStatus httpStatus) {

        super(message, httpStatus);
    }
}
