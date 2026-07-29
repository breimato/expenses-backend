package com.expenses.recurring.scheduler;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.expenses.recurring.service.RecurringApplicationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** The Class Recurring Scheduler. */
@Component
@RequiredArgsConstructor
@Slf4j
public class RecurringScheduler {

    /** The recurring application service. */
    private final RecurringApplicationService recurringApplicationService;

    /** Whether to apply pending templates on startup. */
    @Value("${expenses.recurring.apply-on-startup:false}")
    private boolean applyOnStartup;

    /** Apply pending recurring templates daily at 07:00. */
    @Scheduled(cron = "0 0 7 * * *")
    public void applyPendingDaily() {

        this.applyPending("scheduled");
    }

    /** Apply pending recurring templates on startup. */
    @EventListener(ApplicationReadyEvent.class)
    public void applyPendingOnStartup() {

        if (!this.applyOnStartup) {
            return;
        }
        this.applyPending("startup");
    }

    private void applyPending(final String trigger) {

        final var appliedCount = this.recurringApplicationService.applyPending(LocalDate.now());
        if (appliedCount > 0) {
            log.info("Applied {} recurring template(s) on {}", appliedCount, trigger);
        }
    }
}
