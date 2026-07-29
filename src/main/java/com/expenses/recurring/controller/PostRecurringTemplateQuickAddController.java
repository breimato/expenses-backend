package com.expenses.recurring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.api.RecurringTemplateApplicationsV1Api;
import com.expenses.api.dto.ExpenseV1ResponseDto;
import com.expenses.api.dto.PostRecurringTemplateQuickAddV1RequestDto;
import com.expenses.expense.mapper.ExpenseResponseMapper;
import com.expenses.recurring.service.RecurringTemplateService;

import lombok.RequiredArgsConstructor;

/** The Class Post Recurring Template Quick Add Controller. */
@RestController
@RequiredArgsConstructor
public class PostRecurringTemplateQuickAddController implements RecurringTemplateApplicationsV1Api {

    /** The recurring template service. */
    private final RecurringTemplateService recurringTemplateService;

    /** The expense response mapper. */
    private final ExpenseResponseMapper expenseResponseMapper;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<ExpenseV1ResponseDto> postRecurringTemplateQuickAddV1(
            final Integer id,
            final PostRecurringTemplateQuickAddV1RequestDto postRecurringTemplateQuickAddV1RequestDto) {

        final var expenseEntity = this.recurringTemplateService.quickAdd(id, postRecurringTemplateQuickAddV1RequestDto);
        final var expenseV1ResponseDto = this.expenseResponseMapper.toExpenseV1Response(expenseEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseV1ResponseDto);
    }
}
