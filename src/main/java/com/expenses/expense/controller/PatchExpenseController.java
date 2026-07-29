package com.expenses.expense.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.api.PatchExpenseV1Api;
import com.expenses.api.dto.ExpenseV1ResponseDto;
import com.expenses.api.dto.PatchExpenseV1RequestDto;

import com.expenses.expense.entity.ExpenseEntity;
import com.expenses.expense.mapper.ExpenseResponseMapper;
import com.expenses.expense.repository.ExpenseRepository;

import lombok.RequiredArgsConstructor;

/** The Class Patch Expense Controller. */
@RestController
@RequiredArgsConstructor
public class PatchExpenseController implements PatchExpenseV1Api {

    /** The expense repository. */
    private final ExpenseRepository expenseRepository;

    /** The expense response mapper. */
    private final ExpenseResponseMapper expenseResponseMapper;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<ExpenseV1ResponseDto> patchExpenseV1(final Integer id, final PatchExpenseV1RequestDto patchExpenseV1RequestDto) {

        final var expenseEntity = this.expenseRepository.update(id, patchExpenseV1RequestDto);
        final var expenseV1ResponseDto = this.expenseResponseMapper.toExpenseV1Response(expenseEntity);
        return ResponseEntity.ok(expenseV1ResponseDto);
    }
}
