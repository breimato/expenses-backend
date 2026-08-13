package com.expenses.expense.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.expenses.api.dto.PostExpenseV1RequestDto;

import com.expenses.common.DecimalMapper;
import com.expenses.expense.entity.ExpenseEntity;

/** The Interface Post Expense Request Mapper. */
@Mapper(componentModel = "spring", uses = DecimalMapper.class)
public interface PostExpenseRequestMapper {

    /**
     * To expense entity.
     *
     * @param postExpenseV1RequestDto the post expense v1 request dto
     * @return the expense entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "movementType", ignore = true)
    @Mapping(target = "offsetsSpendingAverage", ignore = true)
    @Mapping(target = "reimbursedExpenseId", ignore = true)
    @Mapping(target = "amount", source = "amount")
    ExpenseEntity toExpenseEntity(PostExpenseV1RequestDto postExpenseV1RequestDto);
}
