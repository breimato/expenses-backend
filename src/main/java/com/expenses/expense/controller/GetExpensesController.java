package com.expenses.expense.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.api.GetExpensesV1Api;
import com.expenses.api.dto.GetExpensesV1ResponseDto;
import com.expenses.api.dto.MovementTypeV1;
import com.expenses.expense.mapper.ExpenseResponseMapper;
import com.expenses.expense.repository.ExpenseRepository;

import lombok.RequiredArgsConstructor;

/** The Class Get Expenses Controller. */
@RestController
@RequiredArgsConstructor
public class GetExpensesController implements GetExpensesV1Api {

    /** The expense repository. */
    private final ExpenseRepository expenseRepository;

    /** The expense response mapper. */
    private final ExpenseResponseMapper expenseResponseMapper;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<GetExpensesV1ResponseDto> getExpensesV1(
            final Integer categoryId,
            final LocalDate expenseDate,
            final String description,
            final MovementTypeV1 movementType) {

        final var expenseEntityList = this.expenseRepository.findAll(
                categoryId,
                expenseDate,
                description,
                movementType);
        final var getExpensesV1ResponseDto = this.expenseResponseMapper.toGetExpensesV1Response(expenseEntityList);
        return ResponseEntity.ok(getExpensesV1ResponseDto);
    }
}
