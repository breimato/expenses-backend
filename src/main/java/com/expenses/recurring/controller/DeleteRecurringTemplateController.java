package com.expenses.recurring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.api.DeleteRecurringTemplateV1Api;

import com.expenses.recurring.repository.RecurringTemplateRepository;

import lombok.RequiredArgsConstructor;

/** The Class Delete Recurring Template Controller. */
@RestController
@RequiredArgsConstructor
public class DeleteRecurringTemplateController implements DeleteRecurringTemplateV1Api {

    /** The recurring template repository. */
    private final RecurringTemplateRepository recurringTemplateRepository;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<Void> deleteRecurringTemplateV1(final Integer id) {

        this.recurringTemplateRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
