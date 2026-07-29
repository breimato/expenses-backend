package com.expenses.expense.mapper;

import com.expenses.api.dto.MovementTypeV1;
import com.expenses.api.dto.PatchExpenseV1RequestDto;
import com.expenses.common.DecimalMapper;
import com.expenses.common.MovementType;
import com.expenses.expense.entity.ExpenseEntity;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-29T10:58:36+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class PatchExpenseRequestMapperImpl implements PatchExpenseRequestMapper {

    @Autowired
    private DecimalMapper decimalMapper;

    @Override
    public void updateExpenseEntity(PatchExpenseV1RequestDto patchExpenseV1RequestDto, ExpenseEntity expenseEntity) {
        if ( patchExpenseV1RequestDto == null ) {
            return;
        }

        if ( patchExpenseV1RequestDto.getCategoryId() != null ) {
            expenseEntity.setCategoryId( patchExpenseV1RequestDto.getCategoryId() );
        }
        if ( patchExpenseV1RequestDto.getAmount() != null ) {
            expenseEntity.setAmount( decimalMapper.toBigDecimal( patchExpenseV1RequestDto.getAmount() ) );
        }
        if ( patchExpenseV1RequestDto.getDescription() != null ) {
            expenseEntity.setDescription( patchExpenseV1RequestDto.getDescription() );
        }
        if ( patchExpenseV1RequestDto.getExpenseDate() != null ) {
            expenseEntity.setExpenseDate( patchExpenseV1RequestDto.getExpenseDate() );
        }
        if ( patchExpenseV1RequestDto.getMovementType() != null ) {
            expenseEntity.setMovementType( movementTypeV1ToMovementType( patchExpenseV1RequestDto.getMovementType() ) );
        }
        if ( patchExpenseV1RequestDto.getOffsetsSpendingAverage() != null ) {
            expenseEntity.setOffsetsSpendingAverage( patchExpenseV1RequestDto.getOffsetsSpendingAverage() );
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
}
