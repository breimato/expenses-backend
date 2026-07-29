package com.expenses.expense.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.api.DeleteExpenseV1Api;

import com.expenses.expense.repository.ExpenseRepository;

import lombok.RequiredArgsConstructor;

/** The Class Delete Expense Controller. */
@RestController
@RequiredArgsConstructor
public class DeleteExpenseController implements DeleteExpenseV1Api {

    /** The expense repository. */
    private final ExpenseRepository expenseRepository;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<Void> deleteExpenseV1(final Integer id) {

        this.expenseRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
