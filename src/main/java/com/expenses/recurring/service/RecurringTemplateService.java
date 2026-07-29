package com.expenses.recurring.service;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expenses.api.dto.PostExpenseV1RequestDto;
import com.expenses.api.dto.PostRecurringTemplateQuickAddV1RequestDto;
import com.expenses.expense.entity.ExpenseEntity;
import com.expenses.recurring.entity.RecurringTemplateEntity;
import com.expenses.recurring.repository.RecurringTemplateRepository;

import lombok.RequiredArgsConstructor;

/** The Class Recurring Template Service. */
@Service
@RequiredArgsConstructor
public class RecurringTemplateService {

    /** The recurring template repository. */
    private final RecurringTemplateRepository recurringTemplateRepository;

    /** The expense repository. */
    private final com.expenses.expense.repository.ExpenseRepository expenseRepository;

    /** The recurring application service. */
    private final com.expenses.recurring.service.RecurringApplicationService recurringApplicationService;

    /**
     * Create an expense from a recurring template and mark the template as used.
     *
     * @param id the recurring template id
     * @param postRecurringTemplateQuickAddV1RequestDto the quick add request dto
     * @return the created expense entity
     */
    @Transactional
    public ExpenseEntity quickAdd(
            final Integer id,
            final PostRecurringTemplateQuickAddV1RequestDto postRecurringTemplateQuickAddV1RequestDto) {

        final var recurringTemplateEntity = this.recurringTemplateRepository.findById(id);
        final var postExpenseV1RequestDto = this.buildPostExpenseRequest(
                recurringTemplateEntity,
                postRecurringTemplateQuickAddV1RequestDto);
        final var expenseEntity = this.expenseRepository.create(postExpenseV1RequestDto);
        this.recurringApplicationService.recordManualApplicationIfMonthly(
                recurringTemplateEntity,
                expenseEntity,
                postExpenseV1RequestDto.getExpenseDate());
        this.recurringTemplateRepository.markAsUsed(recurringTemplateEntity);
        return expenseEntity;
    }

    private PostExpenseV1RequestDto buildPostExpenseRequest(
            final RecurringTemplateEntity recurringTemplateEntity,
            final PostRecurringTemplateQuickAddV1RequestDto postRecurringTemplateQuickAddV1RequestDto) {

        return PostExpenseV1RequestDto.builder()
                .categoryId(recurringTemplateEntity.getCategoryId())
                .amount(this.resolveAmount(recurringTemplateEntity, postRecurringTemplateQuickAddV1RequestDto))
                .description(recurringTemplateEntity.getLabel())
                .expenseDate(this.resolveExpenseDate(postRecurringTemplateQuickAddV1RequestDto))
                .movementType(com.expenses.api.dto.MovementTypeV1.valueOf(recurringTemplateEntity.getMovementType().name()))
                .offsetsSpendingAverage(recurringTemplateEntity.isOffsetsSpendingAverage())
                .build();
    }

    private String resolveAmount(
            final RecurringTemplateEntity recurringTemplateEntity,
            final PostRecurringTemplateQuickAddV1RequestDto postRecurringTemplateQuickAddV1RequestDto) {

        if (Objects.nonNull(postRecurringTemplateQuickAddV1RequestDto)
                && Objects.nonNull(postRecurringTemplateQuickAddV1RequestDto.getAmount())) {
            return postRecurringTemplateQuickAddV1RequestDto.getAmount();
        }
        return recurringTemplateEntity.getAmount().toPlainString();
    }

    private LocalDate resolveExpenseDate(
            final PostRecurringTemplateQuickAddV1RequestDto postRecurringTemplateQuickAddV1RequestDto) {

        if (Objects.nonNull(postRecurringTemplateQuickAddV1RequestDto)
                && Objects.nonNull(postRecurringTemplateQuickAddV1RequestDto.getExpenseDate())) {
            return postRecurringTemplateQuickAddV1RequestDto.getExpenseDate();
        }
        return LocalDate.now();
    }
}
