package com.expenses.recurring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.api.PostRecurringTemplateV1Api;
import com.expenses.api.dto.PostRecurringTemplateV1RequestDto;
import com.expenses.api.dto.RecurringTemplateV1ResponseDto;

import com.expenses.recurring.entity.RecurringTemplateEntity;
import com.expenses.recurring.mapper.RecurringTemplateResponseMapper;
import com.expenses.recurring.repository.RecurringTemplateRepository;

import lombok.RequiredArgsConstructor;

/** The Class Post Recurring Template Controller. */
@RestController
@RequiredArgsConstructor
public class PostRecurringTemplateController implements PostRecurringTemplateV1Api {

    /** The recurring template repository. */
    private final RecurringTemplateRepository recurringTemplateRepository;

    /** The recurring template response mapper. */
    private final RecurringTemplateResponseMapper recurringTemplateResponseMapper;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<RecurringTemplateV1ResponseDto> postRecurringTemplateV1(
            final PostRecurringTemplateV1RequestDto postRecurringTemplateV1RequestDto) {

        final var recurringTemplateEntity = this.recurringTemplateRepository.create(postRecurringTemplateV1RequestDto);
        final var recurringTemplateV1ResponseDto =
                this.recurringTemplateResponseMapper.toRecurringTemplateV1Response(recurringTemplateEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(recurringTemplateV1ResponseDto);
    }
}
