package com.expenses.recurring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.api.GetRecurringTemplatesV1Api;
import com.expenses.api.dto.GetRecurringTemplatesV1ResponseDto;
import com.expenses.recurring.mapper.RecurringTemplateResponseMapper;
import com.expenses.recurring.repository.RecurringTemplateRepository;

import lombok.RequiredArgsConstructor;

/** The Class Get Recurring Templates Controller. */
@RestController
@RequiredArgsConstructor
public class GetRecurringTemplatesController implements GetRecurringTemplatesV1Api {

    /** The recurring template repository. */
    private final RecurringTemplateRepository recurringTemplateRepository;

    /** The recurring template response mapper. */
    private final RecurringTemplateResponseMapper recurringTemplateResponseMapper;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<GetRecurringTemplatesV1ResponseDto> getRecurringTemplatesV1(final Integer categoryId) {

        final var recurringTemplateEntityList = this.recurringTemplateRepository.findAll(categoryId);
        final var getRecurringTemplatesV1ResponseDto =
                this.recurringTemplateResponseMapper.toGetRecurringTemplatesV1Response(recurringTemplateEntityList);
        return ResponseEntity.ok(getRecurringTemplatesV1ResponseDto);
    }
}
