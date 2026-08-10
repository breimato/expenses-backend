package com.expenses.analytics.controller;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.analytics.mapper.AnalyticsResponseMapper;
import com.expenses.analytics.service.AnalyticsService;
import com.expenses.api.GetAnalyticsCategoryBreakdownV1Api;
import com.expenses.api.dto.GetAnalyticsAveragesV1RequestDto;
import com.expenses.api.dto.GetAnalyticsCategoryBreakdownV1ResponseDto;

import lombok.RequiredArgsConstructor;

/** The Class Get Analytics Category Breakdown Controller. */
@RestController
@RequiredArgsConstructor
public class GetAnalyticsCategoryBreakdownController implements GetAnalyticsCategoryBreakdownV1Api {

    /** The analytics service. */
    private final AnalyticsService analyticsService;

    /** The analytics response mapper. */
    private final AnalyticsResponseMapper analyticsResponseMapper;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<GetAnalyticsCategoryBreakdownV1ResponseDto> getAnalyticsCategoryBreakdownV1(
            final GetAnalyticsAveragesV1RequestDto getAnalyticsAveragesV1RequestDto) {

        final var referenceDate = Objects.nonNull(getAnalyticsAveragesV1RequestDto.getReferenceDate())
                ? getAnalyticsAveragesV1RequestDto.getReferenceDate()
                : LocalDate.now();
        final var analyticsCategoryBreakdownResult = this.analyticsService.computeCategoryBreakdown(referenceDate);
        final var getAnalyticsCategoryBreakdownV1ResponseDto =
                this.analyticsResponseMapper.toGetAnalyticsCategoryBreakdownV1Response(analyticsCategoryBreakdownResult);
        return ResponseEntity.ok(getAnalyticsCategoryBreakdownV1ResponseDto);
    }
}
