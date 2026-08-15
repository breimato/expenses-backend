package com.expenses.analytics.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.analytics.mapper.AnalyticsResponseMapper;
import com.expenses.analytics.service.AnalyticsService;
import com.expenses.api.GetAnalyticsPeriodAverageV1Api;
import com.expenses.api.dto.GetAnalyticsPeriodAverageV1RequestDto;
import com.expenses.api.dto.GetAnalyticsPeriodAverageV1ResponseDto;

import lombok.RequiredArgsConstructor;

/** The Class Get Analytics Period Average Controller. */
@RestController
@RequiredArgsConstructor
public class GetAnalyticsPeriodAverageController implements GetAnalyticsPeriodAverageV1Api {

    /** The analytics service. */
    private final AnalyticsService analyticsService;

    /** The analytics response mapper. */
    private final AnalyticsResponseMapper analyticsResponseMapper;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<GetAnalyticsPeriodAverageV1ResponseDto> getAnalyticsPeriodAverageV1(
            final GetAnalyticsPeriodAverageV1RequestDto getAnalyticsPeriodAverageV1RequestDto) {

        final var analyticsPeriodAverageResult = this.analyticsService.computePeriodAverage(
                getAnalyticsPeriodAverageV1RequestDto.getDateFrom(),
                getAnalyticsPeriodAverageV1RequestDto.getDateTo());
        final var getAnalyticsPeriodAverageV1ResponseDto =
                this.analyticsResponseMapper.toGetAnalyticsPeriodAverageV1Response(analyticsPeriodAverageResult);
        return ResponseEntity.ok(getAnalyticsPeriodAverageV1ResponseDto);
    }
}
