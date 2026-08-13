package com.expenses.analytics.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.expenses.analytics.repository.AnalyticsRepository;
import com.expenses.category.entity.CategoryEntity;
import com.expenses.expense.repository.CategorySpendAggregate;

/** The Class Analytics Service Test. */
@ExtendWith(MockitoExtension.class)
class AnalyticsServiceTest {

    @Mock
    AnalyticsRepository analyticsRepository;

    @InjectMocks
    AnalyticsService analyticsService;

    /** Test compute averages when onboarding month and first movement mid month. */
    @Test
    void testComputeAverages_whenOnboardingMonthAndFirstMovementMidMonth_thenAverageUsesDaysSinceFirstMovement() {

        // Given
        final var referenceDate = LocalDate.of(2026, 8, 14);
        final var monthStart = LocalDate.of(2026, 8, 1);
        final var firstMovementDate = LocalDate.of(2026, 8, 14);
        final var netSpending = new BigDecimal("10.00");
        when(this.analyticsRepository.getCurrentUserCreatedOn()).thenReturn(LocalDate.of(2026, 8, 10));
        when(this.analyticsRepository.findFirstMovementDateInRange(monthStart, referenceDate))
                .thenReturn(Optional.of(firstMovementDate));
        when(this.analyticsRepository.sumNetSpendingByDateRange(monthStart, referenceDate)).thenReturn(netSpending);
        when(this.analyticsRepository.sumNetBalanceAsOf(referenceDate)).thenReturn(new BigDecimal("100.00"));

        // When
        final var analyticsAveragesResult = this.analyticsService.computeAverages(referenceDate);

        // Then
        verify(this.analyticsRepository, times(1)).getCurrentUserCreatedOn();
        verify(this.analyticsRepository, times(1)).findFirstMovementDateInRange(monthStart, referenceDate);
        verify(this.analyticsRepository, times(1)).sumNetBalanceAsOf(referenceDate);
        assertEquals(new BigDecimal("10.00"), analyticsAveragesResult.dailyAverage());
        assertEquals(new BigDecimal("100.00"), analyticsAveragesResult.balanceAsOf());
    }

    /** Test compute averages when later month then always divide by day of month from day 1. */
    @Test
    void testComputeAverages_whenLaterMonth_thenAverageUsesCalendarDaysFromDayOne() {

        // Given
        final var referenceDate = LocalDate.of(2026, 9, 14);
        final var monthStart = LocalDate.of(2026, 9, 1);
        final var netSpending = new BigDecimal("140.00");
        when(this.analyticsRepository.getCurrentUserCreatedOn()).thenReturn(LocalDate.of(2026, 8, 10));
        when(this.analyticsRepository.sumNetSpendingByDateRange(monthStart, referenceDate)).thenReturn(netSpending);
        when(this.analyticsRepository.sumNetBalanceAsOf(referenceDate)).thenReturn(new BigDecimal("250.00"));

        // When
        final var analyticsAveragesResult = this.analyticsService.computeAverages(referenceDate);

        // Then
        assertEquals(new BigDecimal("10.00"), analyticsAveragesResult.dailyAverage());
        assertEquals(new BigDecimal("250.00"), analyticsAveragesResult.balanceAsOf());
    }

    /** Test compute averages when onboarding month ignores prior-month backfill for period start. */
    @Test
    void testComputeAverages_whenOnboardingMonthWithPriorBackfill_thenStillUsesFirstMovementInMonth() {

        // Given
        final var referenceDate = LocalDate.of(2026, 8, 14);
        final var monthStart = LocalDate.of(2026, 8, 1);
        final var firstMovementInMonth = LocalDate.of(2026, 8, 10);
        final var netSpending = new BigDecimal("50.00");
        when(this.analyticsRepository.getCurrentUserCreatedOn()).thenReturn(LocalDate.of(2026, 8, 1));
        when(this.analyticsRepository.findFirstMovementDateInRange(monthStart, referenceDate))
                .thenReturn(Optional.of(firstMovementInMonth));
        when(this.analyticsRepository.sumNetSpendingByDateRange(monthStart, referenceDate)).thenReturn(netSpending);
        when(this.analyticsRepository.sumNetBalanceAsOf(referenceDate)).thenReturn(new BigDecimal("80.00"));

        // When
        final var analyticsAveragesResult = this.analyticsService.computeAverages(referenceDate);

        // Then
        assertEquals(new BigDecimal("10.00"), analyticsAveragesResult.dailyAverage());
        assertEquals(new BigDecimal("80.00"), analyticsAveragesResult.balanceAsOf());
    }

    /** Test compute averages when there are no movements then average is zero. */
    @Test
    void testComputeAverages_whenNoMovements_thenAverageIsZero() {

        // Given
        final var referenceDate = LocalDate.of(2026, 8, 14);
        final var monthStart = LocalDate.of(2026, 8, 1);
        when(this.analyticsRepository.getCurrentUserCreatedOn()).thenReturn(LocalDate.of(2026, 7, 1));
        when(this.analyticsRepository.sumNetSpendingByDateRange(monthStart, referenceDate)).thenReturn(BigDecimal.ZERO);
        when(this.analyticsRepository.sumNetBalanceAsOf(referenceDate)).thenReturn(BigDecimal.ZERO);

        // When
        final var analyticsAveragesResult = this.analyticsService.computeAverages(referenceDate);

        // Then
        assertEquals(new BigDecimal("0.00"), analyticsAveragesResult.dailyAverage());
        assertEquals(new BigDecimal("0.00"), analyticsAveragesResult.balanceAsOf());
    }

    /** Test compute category breakdown when expenses exist then returns totals and percents. */
    @Test
    void testComputeCategoryBreakdown_whenExpensesExist_thenReturnsSortedPercents() {

        // Given
        final var referenceDate = LocalDate.of(2026, 8, 14);
        final var monthStart = LocalDate.of(2026, 8, 1);
        final var categorySpendAggregateOne = mock(CategorySpendAggregate.class);
        final var categorySpendAggregateTwo = mock(CategorySpendAggregate.class);
        when(categorySpendAggregateOne.getCategoryId()).thenReturn(1);
        when(categorySpendAggregateOne.getTotal()).thenReturn(new BigDecimal("75.00"));
        when(categorySpendAggregateTwo.getCategoryId()).thenReturn(2);
        when(categorySpendAggregateTwo.getTotal()).thenReturn(new BigDecimal("25.00"));
        final var categoryEntityOne = new CategoryEntity();
        categoryEntityOne.setId(1);
        categoryEntityOne.setName("Comida");
        categoryEntityOne.setColor("#EF4444");
        final var categoryEntityTwo = new CategoryEntity();
        categoryEntityTwo.setId(2);
        categoryEntityTwo.setName("Ocio");
        categoryEntityTwo.setColor("#8B5CF6");
        when(this.analyticsRepository.sumExpenseByCategory(monthStart, referenceDate))
                .thenReturn(List.of(categorySpendAggregateOne, categorySpendAggregateTwo));
        when(this.analyticsRepository.findCategoriesForCurrentUser())
                .thenReturn(List.of(categoryEntityOne, categoryEntityTwo));

        // When
        final var analyticsCategoryBreakdownResult = this.analyticsService.computeCategoryBreakdown(referenceDate);

        // Then
        assertEquals(new BigDecimal("100.00"), analyticsCategoryBreakdownResult.totalSpent());
        assertEquals(2, analyticsCategoryBreakdownResult.items().size());
        assertEquals(new BigDecimal("75.00"), analyticsCategoryBreakdownResult.items().get(0).percent());
        assertEquals(new BigDecimal("25.00"), analyticsCategoryBreakdownResult.items().get(1).percent());
        assertEquals("Comida", analyticsCategoryBreakdownResult.items().get(0).categoryName());
    }
}
