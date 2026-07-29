package com.expenses.expense.mapper;

import com.expenses.api.dto.PostExpenseV1RequestDto;
import com.expenses.common.DecimalMapper;
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
public class PostExpenseRequestMapperImpl implements PostExpenseRequestMapper {

    @Autowired
    private DecimalMapper decimalMapper;

    @Override
    public ExpenseEntity toExpenseEntity(PostExpenseV1RequestDto postExpenseV1RequestDto) {
        if ( postExpenseV1RequestDto == null ) {
            return null;
        }

        ExpenseEntity expenseEntity = new ExpenseEntity();

        expenseEntity.setAmount( decimalMapper.toBigDecimal( postExpenseV1RequestDto.getAmount() ) );
        expenseEntity.setCategoryId( postExpenseV1RequestDto.getCategoryId() );
        expenseEntity.setDescription( postExpenseV1RequestDto.getDescription() );
        expenseEntity.setExpenseDate( postExpenseV1RequestDto.getExpenseDate() );

        return expenseEntity;
    }
}
