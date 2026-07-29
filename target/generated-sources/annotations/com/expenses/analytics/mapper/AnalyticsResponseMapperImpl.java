package com.expenses.analytics.mapper;

import com.expenses.analytics.model.AnalyticsAveragesResult;
import com.expenses.analytics.model.AnalyticsProjectionsResult;
import com.expenses.api.dto.AnalyticsAveragesV1Dto;
import com.expenses.api.dto.AnalyticsProjectionsV1Dto;
import com.expenses.common.DecimalMapper;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-29T10:58:36+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class AnalyticsResponseMapperImpl implements AnalyticsResponseMapper {

    @Autowired
    private DecimalMapper decimalMapper;

    @Override
    public AnalyticsAveragesV1Dto toAnalyticsAveragesV1Dto(AnalyticsAveragesResult analyticsAveragesResult) {
        if ( analyticsAveragesResult == null ) {
            return null;
        }

        AnalyticsAveragesV1Dto.Builder analyticsAveragesV1Dto = AnalyticsAveragesV1Dto.builder();

        analyticsAveragesV1Dto.dailyAverage( decimalMapper.toString( analyticsAveragesResult.dailyAverage() ) );
        analyticsAveragesV1Dto.weeklyAverage( decimalMapper.toString( analyticsAveragesResult.weeklyAverage() ) );
        analyticsAveragesV1Dto.monthlyAverage( decimalMapper.toString( analyticsAveragesResult.monthlyAverage() ) );
        analyticsAveragesV1Dto.yearlyAverage( decimalMapper.toString( analyticsAveragesResult.yearlyAverage() ) );

        return analyticsAveragesV1Dto.build();
    }

    @Override
    public AnalyticsProjectionsV1Dto toAnalyticsProjectionsV1Dto(AnalyticsProjectionsResult analyticsProjectionsResult) {
        if ( analyticsProjectionsResult == null ) {
            return null;
        }

        AnalyticsProjectionsV1Dto.Builder analyticsProjectionsV1Dto = AnalyticsProjectionsV1Dto.builder();

        analyticsProjectionsV1Dto.projectedMonthlyExpense( decimalMapper.toString( analyticsProjectionsResult.projectedMonthlyExpense() ) );
        analyticsProjectionsV1Dto.projectedEndOfMonthBalance( decimalMapper.toString( analyticsProjectionsResult.projectedEndOfMonthBalance() ) );
        analyticsProjectionsV1Dto.daysRemainingInMonth( analyticsProjectionsResult.daysRemainingInMonth() );

        return analyticsProjectionsV1Dto.build();
    }
}
