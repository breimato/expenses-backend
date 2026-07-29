package com.expenses.analytics.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.analytics.mapper.AnalyticsResponseMapper;
import com.expenses.analytics.service.AnalyticsService;
import com.expenses.api.GetAnalyticsProjectionsV1Api;
import com.expenses.api.dto.GetAnalyticsProjectionsV1RequestDto;
import com.expenses.api.dto.GetAnalyticsProjectionsV1ResponseDto;

import lombok.RequiredArgsConstructor;

/** The Class Get Analytics Projections Controller. */
@RestController
@RequiredArgsConstructor
public class GetAnalyticsProjectionsController implements GetAnalyticsProjectionsV1Api {

    /** The analytics service. */
    private final AnalyticsService analyticsService;

    /** The analytics response mapper. */
    private final AnalyticsResponseMapper analyticsResponseMapper;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<GetAnalyticsProjectionsV1ResponseDto> getAnalyticsProjectionsV1(
            final GetAnalyticsProjectionsV1RequestDto getAnalyticsProjectionsV1RequestDto) {

        final var referenceDate = getAnalyticsProjectionsV1RequestDto.getReferenceDate() != null
                ? getAnalyticsProjectionsV1RequestDto.getReferenceDate()
                : LocalDate.now();
        final var analyticsProjectionsResult = this.analyticsService.computeProjections(referenceDate);

        final var getAnalyticsProjectionsV1ResponseDto =
                this.analyticsResponseMapper.toGetAnalyticsProjectionsV1Response(analyticsProjectionsResult);

        return ResponseEntity.ok(getAnalyticsProjectionsV1ResponseDto);
    }
}
