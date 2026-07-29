package com.expenses.recurring.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expenses.api.dto.PostExpenseV1RequestDto;
import com.expenses.common.RecurringFrequency;
import com.expenses.expense.entity.ExpenseEntity;
import com.expenses.expense.repository.ExpenseRepository;
import com.expenses.recurring.entity.RecurringApplicationEntity;
import com.expenses.recurring.entity.RecurringTemplateEntity;
import com.expenses.recurring.repository.RecurringApplicationJpaMapper;
import com.expenses.recurring.repository.RecurringTemplateJpaMapper;
import com.expenses.recurring.repository.RecurringTemplateRepository;

import lombok.RequiredArgsConstructor;

/** The Class Recurring Application Service. */
@Service
@RequiredArgsConstructor
public class RecurringApplicationService {

    private static final DateTimeFormatter PERIOD_KEY_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM");

    /** The recurring template jpa mapper. */
    private final RecurringTemplateJpaMapper recurringTemplateJpaMapper;

    /** The recurring application jpa mapper. */
    private final RecurringApplicationJpaMapper recurringApplicationJpaMapper;

    /** The expense repository. */
    private final ExpenseRepository expenseRepository;

    /** The recurring template repository. */
    private final RecurringTemplateRepository recurringTemplateRepository;

    /**
     * Apply all due recurring templates for the given reference date.
     *
     * @param referenceDate the reference date
     * @return the number of templates applied
     */
    @Transactional
    public int applyPending(final LocalDate referenceDate) {

        final var dueTemplates = this.recurringTemplateJpaMapper.findDueAutoApplyTemplates(
                RecurringFrequency.MONTHLY,
                referenceDate.getDayOfMonth());
        final var periodKey = referenceDate.format(PERIOD_KEY_FORMAT);
        var appliedCount = 0;

        for (final var template : dueTemplates) {
            if (this.recurringApplicationJpaMapper.findByTemplateIdAndPeriodKey(template.getId(), periodKey).isPresent()) {
                continue;
            }
            final var expenseDate = this.resolveExpenseDate(template, referenceDate);
            final var movement = this.createMovementFromTemplate(template, expenseDate);
            this.recordApplication(template.getId(), periodKey, movement.getId());
            this.recurringTemplateRepository.markAsUsed(template);
            appliedCount++;
        }

        return appliedCount;
    }

    /**
     * Create movement from template for quick-add or auto-apply.
     *
     * @param recurringTemplateEntity the recurring template entity
     * @param expenseDate the expense date
     * @return the expense entity
     */
    @Transactional
    public ExpenseEntity createMovementFromTemplate(
            final RecurringTemplateEntity recurringTemplateEntity,
            final LocalDate expenseDate) {

        final var postExpenseV1RequestDto = PostExpenseV1RequestDto.builder()
                .categoryId(recurringTemplateEntity.getCategoryId())
                .amount(recurringTemplateEntity.getAmount().toPlainString())
                .description(recurringTemplateEntity.getLabel())
                .expenseDate(expenseDate)
                .movementType(com.expenses.api.dto.MovementTypeV1.valueOf(recurringTemplateEntity.getMovementType().name()))
                .offsetsSpendingAverage(recurringTemplateEntity.isOffsetsSpendingAverage())
                .build();
        return this.expenseRepository.create(postExpenseV1RequestDto);
    }

    private LocalDate resolveExpenseDate(final RecurringTemplateEntity template, final LocalDate referenceDate) {

        final var dayOfMonth = Math.min(
                Objects.requireNonNullElse(template.getDayOfMonth(), referenceDate.getDayOfMonth()),
                referenceDate.lengthOfMonth());
        return referenceDate.withDayOfMonth(dayOfMonth);
    }

    private void recordApplication(final Integer templateId, final String periodKey, final Integer movementId) {

        final var recurringApplicationEntity = new RecurringApplicationEntity();
        recurringApplicationEntity.setTemplateId(templateId);
        recurringApplicationEntity.setPeriodKey(periodKey);
        recurringApplicationEntity.setMovementId(movementId);
        recurringApplicationEntity.setAppliedAt(LocalDateTime.now());
        this.recurringApplicationJpaMapper.save(recurringApplicationEntity);
    }

    /**
     * Record application for monthly templates when manually quick-adding.
     *
     * @param recurringTemplateEntity the recurring template entity
     * @param expenseEntity the expense entity
     * @param expenseDate the expense date
     */
    @Transactional
    public void recordManualApplicationIfMonthly(
            final RecurringTemplateEntity recurringTemplateEntity,
            final ExpenseEntity expenseEntity,
            final LocalDate expenseDate) {

        if (recurringTemplateEntity.getFrequency() != RecurringFrequency.MONTHLY) {
            return;
        }
        final var periodKey = expenseDate.format(PERIOD_KEY_FORMAT);
        if (this.recurringApplicationJpaMapper
                .findByTemplateIdAndPeriodKey(recurringTemplateEntity.getId(), periodKey)
                .isPresent()) {
            return;
        }
        this.recordApplication(recurringTemplateEntity.getId(), periodKey, expenseEntity.getId());
    }
}
