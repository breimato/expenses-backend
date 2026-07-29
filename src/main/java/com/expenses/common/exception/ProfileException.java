package com.expenses.common.exception;

import org.springframework.http.HttpStatus;

/** The Class Profile Exception. */
public class ProfileException extends ExpensesException {

    /**
     * Instantiates a new profile exception.
     *
     * @param message the message
     */
    public ProfileException(final String message) {

        super(message, HttpStatus.NOT_FOUND);
    }
}
