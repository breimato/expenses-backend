package com.expenses.expense.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.expenses.api.dto.PatchExpenseV1RequestDto;

import com.expenses.common.DecimalMapper;
import com.expenses.expense.entity.ExpenseEntity;

/** The Interface Patch Expense Request Mapper. */
@Mapper(componentModel = "spring", uses = DecimalMapper.class)
public interface PatchExpenseRequestMapper {

    /**
     * Update expense entity.
     *
     * @param patchExpenseV1RequestDto the patch expense v1 request dto
     * @param expenseEntity the expense entity
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateExpenseEntity(PatchExpenseV1RequestDto patchExpenseV1RequestDto, @MappingTarget ExpenseEntity expenseEntity);
}
