package com.expenses.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/** The Class Expenses Exception. */
@Getter
public abstract class ExpensesException extends RuntimeException {

    /** The code. */
    private final String code;

    /** The http status. */
    private final HttpStatus httpStatus;

  /**
   * Instantiates a new expenses exception.
   *
   * @param message the message
   * @param httpStatus the http status
   */
    protected ExpensesException(final String message, final HttpStatus httpStatus) {

        super(message);
        this.code = message.split(" \\| ")[0];
        this.httpStatus = httpStatus;
    }
}
