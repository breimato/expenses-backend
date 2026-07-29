package com.expenses.recurring.mapper;

import com.expenses.api.dto.RecurringTemplateV1Dto;
import com.expenses.api.dto.RecurringTemplateV1ResponseDto;
import com.expenses.common.DecimalMapper;
import com.expenses.common.EnumMapper;
import com.expenses.common.JsonNullableMapper;
import com.expenses.recurring.entity.RecurringTemplateEntity;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-29T10:58:36+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class RecurringTemplateResponseMapperImpl implements RecurringTemplateResponseMapper {

    @Autowired
    private DecimalMapper decimalMapper;
    @Autowired
    private JsonNullableMapper jsonNullableMapper;
    @Autowired
    private EnumMapper enumMapper;

    @Override
    public RecurringTemplateV1Dto toRecurringTemplateV1Dto(RecurringTemplateEntity recurringTemplateEntity) {
        if ( recurringTemplateEntity == null ) {
            return null;
        }

        RecurringTemplateV1Dto.Builder recurringTemplateV1Dto = RecurringTemplateV1Dto.builder();

        recurringTemplateV1Dto.lastUsedAt( jsonNullableMapper.mapOffsetDateTime( recurringTemplateEntity.getLastUsedAt() ) );
        recurringTemplateV1Dto.dayOfMonth( jsonNullableMapper.mapInteger( recurringTemplateEntity.getDayOfMonth() ) );
        recurringTemplateV1Dto.id( recurringTemplateEntity.getId() );
        recurringTemplateV1Dto.label( recurringTemplateEntity.getLabel() );
        recurringTemplateV1Dto.amount( decimalMapper.toString( recurringTemplateEntity.getAmount() ) );
        recurringTemplateV1Dto.categoryId( recurringTemplateEntity.getCategoryId() );
        recurringTemplateV1Dto.sortOrder( recurringTemplateEntity.getSortOrder() );
        recurringTemplateV1Dto.movementType( enumMapper.toMovementTypeV1( recurringTemplateEntity.getMovementType() ) );
        recurringTemplateV1Dto.offsetsSpendingAverage( recurringTemplateEntity.isOffsetsSpendingAverage() );
        recurringTemplateV1Dto.frequency( enumMapper.toRecurringFrequencyV1( recurringTemplateEntity.getFrequency() ) );
        recurringTemplateV1Dto.autoApply( recurringTemplateEntity.isAutoApply() );
        recurringTemplateV1Dto.enabled( recurringTemplateEntity.isEnabled() );

        return recurringTemplateV1Dto.build();
    }

    @Override
    public RecurringTemplateV1ResponseDto toRecurringTemplateV1Response(RecurringTemplateEntity recurringTemplateEntity) {
        if ( recurringTemplateEntity == null ) {
            return null;
        }

        RecurringTemplateV1ResponseDto.Builder recurringTemplateV1ResponseDto = RecurringTemplateV1ResponseDto.builder();

        recurringTemplateV1ResponseDto.recurringTemplate( toRecurringTemplateV1Dto( recurringTemplateEntity ) );

        return recurringTemplateV1ResponseDto.build();
    }
}
