package com.expenses.analytics.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.analytics.mapper.AnalyticsResponseMapper;
import com.expenses.analytics.service.AnalyticsService;
import com.expenses.api.GetAnalyticsAveragesV1Api;
import com.expenses.api.dto.GetAnalyticsAveragesV1RequestDto;
import com.expenses.api.dto.GetAnalyticsAveragesV1ResponseDto;

import lombok.RequiredArgsConstructor;

/** The Class Get Analytics Averages Controller. */
@RestController
@RequiredArgsConstructor
public class GetAnalyticsAveragesController implements GetAnalyticsAveragesV1Api {

    /** The analytics service. */
    private final AnalyticsService analyticsService;

    /** The analytics response mapper. */
    private final AnalyticsResponseMapper analyticsResponseMapper;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<GetAnalyticsAveragesV1ResponseDto> getAnalyticsAveragesV1(
            final GetAnalyticsAveragesV1RequestDto getAnalyticsAveragesV1RequestDto) {

        final var referenceDate = getAnalyticsAveragesV1RequestDto.getReferenceDate() != null
                ? getAnalyticsAveragesV1RequestDto.getReferenceDate()
                : LocalDate.now();
        final var analyticsAveragesResult = this.analyticsService.computeAverages(referenceDate);

        final var getAnalyticsAveragesV1ResponseDto =
                this.analyticsResponseMapper.toGetAnalyticsAveragesV1Response(analyticsAveragesResult);

        return ResponseEntity.ok(getAnalyticsAveragesV1ResponseDto);
    }
}
