package com.expenses.expense.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.expenses.api.dto.ExpenseV1Dto;
import com.expenses.api.dto.ExpenseV1ResponseDto;
import com.expenses.api.dto.GetExpensesV1ResponseDto;

import com.expenses.common.DateMapper;
import com.expenses.common.DecimalMapper;
import com.expenses.common.EnumMapper;
import com.expenses.common.JsonNullableMapper;
import com.expenses.expense.entity.ExpenseEntity;

/** The Interface Expense Response Mapper. */
@Mapper(componentModel = "spring", uses = { DateMapper.class, DecimalMapper.class, EnumMapper.class, JsonNullableMapper.class })
public interface ExpenseResponseMapper {

    /**
     * To expense v1 dto.
     *
     * @param expenseEntity the expense entity
     * @return the expense v1 dto
     */
    @Mapping(target = "reimbursedExpenseId", source = "reimbursedExpenseId", qualifiedByName = "mapInteger")
    ExpenseV1Dto toExpenseV1Dto(ExpenseEntity expenseEntity);

    /**
     * To expense v1 response.
     *
     * @param expenseEntity the expense entity
     * @return the expense v1 response dto
     */
    @Mapping(target = "expense", source = "expenseEntity")
    ExpenseV1ResponseDto toExpenseV1Response(ExpenseEntity expenseEntity);

    /**
     * To get expenses v1 response.
     *
     * @param expenseEntityList the expense entity list
     * @return the get expenses v1 response dto
     */
    default GetExpensesV1ResponseDto toGetExpensesV1Response(final List<ExpenseEntity> expenseEntityList) {
        return GetExpensesV1ResponseDto.builder()
                .expenses(expenseEntityList.stream().map(this::toExpenseV1Dto).toList())
                .build();
    }
}
