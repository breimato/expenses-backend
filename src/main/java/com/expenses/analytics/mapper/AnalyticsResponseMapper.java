package com.expenses.analytics.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.expenses.analytics.model.AnalyticsAveragesResult;
import com.expenses.analytics.model.AnalyticsCategoryBreakdownResult;
import com.expenses.analytics.model.AnalyticsCategorySpendItemResult;
import com.expenses.analytics.model.AnalyticsProjectionsResult;
import com.expenses.api.dto.AnalyticsAveragesV1Dto;
import com.expenses.api.dto.AnalyticsCategorySpendItemV1Dto;
import com.expenses.api.dto.AnalyticsProjectionsV1Dto;
import com.expenses.api.dto.GetAnalyticsAveragesV1ResponseDto;
import com.expenses.api.dto.GetAnalyticsCategoryBreakdownV1ResponseAnalyticsCategoryBreakdown;
import com.expenses.api.dto.GetAnalyticsCategoryBreakdownV1ResponseDto;
import com.expenses.api.dto.GetAnalyticsProjectionsV1ResponseDto;
import com.expenses.common.DecimalMapper;

/** The Interface Analytics Response Mapper. */
@Mapper(componentModel = "spring", uses = DecimalMapper.class)
public interface AnalyticsResponseMapper {

    /**
     * To analytics averages v1 dto.
     *
     * @param analyticsAveragesResult the analytics averages result
     * @return the analytics averages v1 dto
     */
    @Mapping(target = "dailyAverage", source = "dailyAverage")
    AnalyticsAveragesV1Dto toAnalyticsAveragesV1Dto(AnalyticsAveragesResult analyticsAveragesResult);

    /**
     * To get analytics averages v1 response.
     *
     * @param analyticsAveragesResult the analytics averages result
     * @return the get analytics averages v1 response dto
     */
    default GetAnalyticsAveragesV1ResponseDto toGetAnalyticsAveragesV1Response(
            final AnalyticsAveragesResult analyticsAveragesResult) {

        return GetAnalyticsAveragesV1ResponseDto.builder()
                .analyticsAverages(this.toAnalyticsAveragesV1Dto(analyticsAveragesResult))
                .build();
    }

    /**
     * To analytics projections v1 dto.
     *
     * @param analyticsProjectionsResult the analytics projections result
     * @return the analytics projections v1 dto
     */
    @Mapping(target = "projectedMonthlyExpense", source = "projectedMonthlyExpense")
    @Mapping(target = "projectedEndOfMonthBalance", source = "projectedEndOfMonthBalance")
    @Mapping(target = "daysRemainingInMonth", source = "daysRemainingInMonth")
    AnalyticsProjectionsV1Dto toAnalyticsProjectionsV1Dto(AnalyticsProjectionsResult analyticsProjectionsResult);

    /**
     * To get analytics projections v1 response.
     *
     * @param analyticsProjectionsResult the analytics projections result
     * @return the get analytics projections v1 response dto
     */
    default GetAnalyticsProjectionsV1ResponseDto toGetAnalyticsProjectionsV1Response(
            final AnalyticsProjectionsResult analyticsProjectionsResult) {

        return GetAnalyticsProjectionsV1ResponseDto.builder()
                .analyticsProjections(this.toAnalyticsProjectionsV1Dto(analyticsProjectionsResult))
                .build();
    }

    /**
     * To analytics category spend item v1 dto.
     *
     * @param analyticsCategorySpendItemResult the item result
     * @return the dto
     */
    AnalyticsCategorySpendItemV1Dto toAnalyticsCategorySpendItemV1Dto(
            AnalyticsCategorySpendItemResult analyticsCategorySpendItemResult);

    /**
     * To get analytics category breakdown v1 response.
     *
     * @param analyticsCategoryBreakdownResult the breakdown result
     * @return the response dto
     */
    default GetAnalyticsCategoryBreakdownV1ResponseDto toGetAnalyticsCategoryBreakdownV1Response(
            final AnalyticsCategoryBreakdownResult analyticsCategoryBreakdownResult) {

        final var analyticsCategorySpendItemV1Dtos = analyticsCategoryBreakdownResult.items().stream()
                .map(this::toAnalyticsCategorySpendItemV1Dto)
                .toList();
        final var getAnalyticsCategoryBreakdownV1ResponseAnalyticsCategoryBreakdown =
                GetAnalyticsCategoryBreakdownV1ResponseAnalyticsCategoryBreakdown.builder()
                        .totalSpent(analyticsCategoryBreakdownResult.totalSpent().toPlainString())
                        .items(analyticsCategorySpendItemV1Dtos)
                        .build();
        return GetAnalyticsCategoryBreakdownV1ResponseDto.builder()
                .analyticsCategoryBreakdown(getAnalyticsCategoryBreakdownV1ResponseAnalyticsCategoryBreakdown)
                .build();
    }
}
