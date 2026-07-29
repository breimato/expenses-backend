package com.expenses.recurring.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.expenses.api.dto.PatchRecurringTemplateV1RequestDto;

import com.expenses.common.DecimalMapper;
import com.expenses.recurring.entity.RecurringTemplateEntity;

/** The Interface Patch Recurring Template Request Mapper. */
@Mapper(componentModel = "spring", uses = DecimalMapper.class)
public interface PatchRecurringTemplateRequestMapper {

    /**
     * Update recurring template entity.
     *
     * @param patchRecurringTemplateV1RequestDto the patch recurring template v1 request dto
     * @param recurringTemplateEntity the recurring template entity
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRecurringTemplateEntity(
            PatchRecurringTemplateV1RequestDto patchRecurringTemplateV1RequestDto,
            @MappingTarget RecurringTemplateEntity recurringTemplateEntity);
}
