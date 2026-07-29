package com.expenses.expense.mapper;

import com.expenses.api.dto.ExpenseV1Dto;
import com.expenses.api.dto.ExpenseV1ResponseDto;
import com.expenses.common.DateMapper;
import com.expenses.common.DecimalMapper;
import com.expenses.common.EnumMapper;
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
public class ExpenseResponseMapperImpl implements ExpenseResponseMapper {

    @Autowired
    private DateMapper dateMapper;
    @Autowired
    private DecimalMapper decimalMapper;
    @Autowired
    private EnumMapper enumMapper;

    @Override
    public ExpenseV1Dto toExpenseV1Dto(ExpenseEntity expenseEntity) {
        if ( expenseEntity == null ) {
            return null;
        }

        ExpenseV1Dto.Builder expenseV1Dto = ExpenseV1Dto.builder();

        expenseV1Dto.id( expenseEntity.getId() );
        expenseV1Dto.categoryId( expenseEntity.getCategoryId() );
        expenseV1Dto.amount( decimalMapper.toString( expenseEntity.getAmount() ) );
        expenseV1Dto.description( expenseEntity.getDescription() );
        expenseV1Dto.expenseDate( expenseEntity.getExpenseDate() );
        expenseV1Dto.movementType( enumMapper.toMovementTypeV1( expenseEntity.getMovementType() ) );
        expenseV1Dto.offsetsSpendingAverage( expenseEntity.isOffsetsSpendingAverage() );
        expenseV1Dto.createdAt( dateMapper.toOffsetDateTime( expenseEntity.getCreatedAt() ) );
        expenseV1Dto.updatedAt( dateMapper.toOffsetDateTime( expenseEntity.getUpdatedAt() ) );

        return expenseV1Dto.build();
    }

    @Override
    public ExpenseV1ResponseDto toExpenseV1Response(ExpenseEntity expenseEntity) {
        if ( expenseEntity == null ) {
            return null;
        }

        ExpenseV1ResponseDto.Builder expenseV1ResponseDto = ExpenseV1ResponseDto.builder();

        expenseV1ResponseDto.expense( toExpenseV1Dto( expenseEntity ) );

        return expenseV1ResponseDto.build();
    }
}
