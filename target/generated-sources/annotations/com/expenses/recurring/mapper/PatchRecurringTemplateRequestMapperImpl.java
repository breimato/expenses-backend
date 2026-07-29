package com.expenses.recurring.mapper;

import com.expenses.api.dto.MovementTypeV1;
import com.expenses.api.dto.PatchRecurringTemplateV1RequestDto;
import com.expenses.api.dto.RecurringFrequencyV1;
import com.expenses.common.DecimalMapper;
import com.expenses.common.MovementType;
import com.expenses.common.RecurringFrequency;
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
public class PatchRecurringTemplateRequestMapperImpl implements PatchRecurringTemplateRequestMapper {

    @Autowired
    private DecimalMapper decimalMapper;

    @Override
    public void updateRecurringTemplateEntity(PatchRecurringTemplateV1RequestDto patchRecurringTemplateV1RequestDto, RecurringTemplateEntity recurringTemplateEntity) {
        if ( patchRecurringTemplateV1RequestDto == null ) {
            return;
        }

        if ( patchRecurringTemplateV1RequestDto.getLabel() != null ) {
            recurringTemplateEntity.setLabel( patchRecurringTemplateV1RequestDto.getLabel() );
        }
        if ( patchRecurringTemplateV1RequestDto.getAmount() != null ) {
            recurringTemplateEntity.setAmount( decimalMapper.toBigDecimal( patchRecurringTemplateV1RequestDto.getAmount() ) );
        }
        if ( patchRecurringTemplateV1RequestDto.getCategoryId() != null ) {
            recurringTemplateEntity.setCategoryId( patchRecurringTemplateV1RequestDto.getCategoryId() );
        }
        if ( patchRecurringTemplateV1RequestDto.getSortOrder() != null ) {
            recurringTemplateEntity.setSortOrder( patchRecurringTemplateV1RequestDto.getSortOrder() );
        }
        if ( patchRecurringTemplateV1RequestDto.getMovementType() != null ) {
            recurringTemplateEntity.setMovementType( movementTypeV1ToMovementType( patchRecurringTemplateV1RequestDto.getMovementType() ) );
        }
        if ( patchRecurringTemplateV1RequestDto.getOffsetsSpendingAverage() != null ) {
            recurringTemplateEntity.setOffsetsSpendingAverage( patchRecurringTemplateV1RequestDto.getOffsetsSpendingAverage() );
        }
        if ( patchRecurringTemplateV1RequestDto.getFrequency() != null ) {
            recurringTemplateEntity.setFrequency( recurringFrequencyV1ToRecurringFrequency( patchRecurringTemplateV1RequestDto.getFrequency() ) );
        }
        if ( patchRecurringTemplateV1RequestDto.getDayOfMonth() != null ) {
            recurringTemplateEntity.setDayOfMonth( patchRecurringTemplateV1RequestDto.getDayOfMonth() );
        }
        if ( patchRecurringTemplateV1RequestDto.getAutoApply() != null ) {
            recurringTemplateEntity.setAutoApply( patchRecurringTemplateV1RequestDto.getAutoApply() );
        }
        if ( patchRecurringTemplateV1RequestDto.getEnabled() != null ) {
            recurringTemplateEntity.setEnabled( patchRecurringTemplateV1RequestDto.getEnabled() );
        }
    }

    protected MovementType movementTypeV1ToMovementType(MovementTypeV1 movementTypeV1) {
        if ( movementTypeV1 == null ) {
            return null;
        }

        MovementType movementType;

        switch ( movementTypeV1 ) {
            case EXPENSE: movementType = MovementType.EXPENSE;
            break;
            case INCOME: movementType = MovementType.INCOME;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + movementTypeV1 );
        }

        return movementType;
    }

    protected RecurringFrequency recurringFrequencyV1ToRecurringFrequency(RecurringFrequencyV1 recurringFrequencyV1) {
        if ( recurringFrequencyV1 == null ) {
            return null;
        }

        RecurringFrequency recurringFrequency;

        switch ( recurringFrequencyV1 ) {
            case MANUAL: recurringFrequency = RecurringFrequency.MANUAL;
            break;
            case MONTHLY: recurringFrequency = RecurringFrequency.MONTHLY;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + recurringFrequencyV1 );
        }

        return recurringFrequency;
    }
}
