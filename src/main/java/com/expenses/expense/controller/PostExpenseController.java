package com.expenses.expense.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.api.PostExpenseV1Api;
import com.expenses.api.dto.ExpenseV1ResponseDto;
import com.expenses.api.dto.PostExpenseV1RequestDto;

import com.expenses.expense.entity.ExpenseEntity;
import com.expenses.expense.mapper.ExpenseResponseMapper;
import com.expenses.expense.repository.ExpenseRepository;

import lombok.RequiredArgsConstructor;

/** The Class Post Expense Controller. */
@RestController
@RequiredArgsConstructor
public class PostExpenseController implements PostExpenseV1Api {

    /** The expense repository. */
    private final ExpenseRepository expenseRepository;

    /** The expense response mapper. */
    private final ExpenseResponseMapper expenseResponseMapper;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<ExpenseV1ResponseDto> postExpenseV1(final PostExpenseV1RequestDto postExpenseV1RequestDto) {

        final var expenseEntity = this.expenseRepository.create(postExpenseV1RequestDto);
        final var expenseV1ResponseDto = this.expenseResponseMapper.toExpenseV1Response(expenseEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseV1ResponseDto);
    }
}
