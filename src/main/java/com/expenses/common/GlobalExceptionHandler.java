package com.expenses.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.expenses.api.dto.ApiErrorV1;

import com.expenses.common.exception.ExpensesException;
import com.expenses.common.exception.constants.ExceptionMessageConstants;

import org.springframework.dao.DataIntegrityViolationException;

import jakarta.validation.ConstraintViolationException;

/** The Class Global Exception Handler. */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle exception.
     *
     * @param expensesException the expenses exception
     * @return the response entity
     */
    @ExceptionHandler(ExpensesException.class)
    public ResponseEntity<ApiErrorV1> handleException(final ExpensesException expensesException) {

        final var apiErrorV1 = ApiErrorV1.builder()
                .code(expensesException.getCode())
                .message(expensesException.getMessage())
                .build();
        return ResponseEntity.status(expensesException.getHttpStatus()).body(apiErrorV1);
    }

    /**
     * Handle data integrity violation.
     *
     * @param dataIntegrityViolationException the data integrity violation exception
     * @return the response entity
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorV1> handleDataIntegrityViolation(
            final DataIntegrityViolationException dataIntegrityViolationException) {

        final var apiErrorV1 = ApiErrorV1.builder()
                .code(ExceptionMessageConstants.DATA_INTEGRITY_ERROR.split(" \\| ")[0])
                .message(ExceptionMessageConstants.DATA_INTEGRITY_ERROR)
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiErrorV1);
    }

    /**
     * Handle validation errors.
     *
     * @param methodArgumentNotValidException the method argument not valid exception
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorV1> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException methodArgumentNotValidException) {

        final var apiErrorV1 = ApiErrorV1.builder()
                .code(ExceptionMessageConstants.VALIDATION_ERROR.split(" \\| ")[0])
                .message(ExceptionMessageConstants.VALIDATION_ERROR)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorV1);
    }

    /**
     * Handle constraint violation.
     *
     * @param constraintViolationException the constraint violation exception
     * @return the response entity
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorV1> handleConstraintViolation(
            final ConstraintViolationException constraintViolationException) {

        final var apiErrorV1 = ApiErrorV1.builder()
                .code(ExceptionMessageConstants.VALIDATION_ERROR.split(" \\| ")[0])
                .message(ExceptionMessageConstants.VALIDATION_ERROR)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorV1);
    }

    /**
     * Handle unexpected errors.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorV1> handleUnexpected(final Exception exception) {

        final var apiErrorV1 = ApiErrorV1.builder()
                .code(ExceptionMessageConstants.INTERNAL_ERROR.split(" \\| ")[0])
                .message(ExceptionMessageConstants.INTERNAL_ERROR)
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiErrorV1);
    }
}
