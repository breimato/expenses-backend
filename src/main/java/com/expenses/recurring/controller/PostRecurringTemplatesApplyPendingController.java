package com.expenses.recurring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.api.RecurringApplicationsV1Api;
import com.expenses.api.dto.PostRecurringTemplatesApplyPendingV1Response;
import com.expenses.recurring.service.RecurringApplicationService;

import java.time.LocalDate;

import lombok.RequiredArgsConstructor;

/** The Class Post Recurring Templates Apply Pending Controller. */
@RestController
@RequiredArgsConstructor
public class PostRecurringTemplatesApplyPendingController implements RecurringApplicationsV1Api {

    /** The recurring application service. */
    private final RecurringApplicationService recurringApplicationService;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<PostRecurringTemplatesApplyPendingV1Response> postRecurringTemplatesApplyPendingV1() {

        final var appliedCount = this.recurringApplicationService.applyPendingForCurrentUser(LocalDate.now());
        final var response = PostRecurringTemplatesApplyPendingV1Response.builder()
                .appliedCount(appliedCount)
                .build();
        return ResponseEntity.ok(response);
    }
}
