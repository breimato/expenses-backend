package com.expenses.recurring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.api.PatchRecurringTemplateV1Api;
import com.expenses.api.dto.PatchRecurringTemplateV1RequestDto;
import com.expenses.api.dto.RecurringTemplateV1ResponseDto;

import com.expenses.recurring.entity.RecurringTemplateEntity;
import com.expenses.recurring.mapper.RecurringTemplateResponseMapper;
import com.expenses.recurring.repository.RecurringTemplateRepository;

import lombok.RequiredArgsConstructor;

/** The Class Patch Recurring Template Controller. */
@RestController
@RequiredArgsConstructor
public class PatchRecurringTemplateController implements PatchRecurringTemplateV1Api {

    /** The recurring template repository. */
    private final RecurringTemplateRepository recurringTemplateRepository;

    /** The recurring template response mapper. */
    private final RecurringTemplateResponseMapper recurringTemplateResponseMapper;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<RecurringTemplateV1ResponseDto> patchRecurringTemplateV1(
            final Integer id,
            final PatchRecurringTemplateV1RequestDto patchRecurringTemplateV1RequestDto) {

        final var recurringTemplateEntity = this.recurringTemplateRepository.update(id, patchRecurringTemplateV1RequestDto);
        final var recurringTemplateV1ResponseDto =
                this.recurringTemplateResponseMapper.toRecurringTemplateV1Response(recurringTemplateEntity);
        return ResponseEntity.ok(recurringTemplateV1ResponseDto);
    }
}
