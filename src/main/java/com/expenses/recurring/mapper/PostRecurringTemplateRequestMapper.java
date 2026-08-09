package com.expenses.recurring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.expenses.api.dto.PostRecurringTemplateV1RequestDto;

import com.expenses.common.DecimalMapper;
import com.expenses.recurring.entity.RecurringTemplateEntity;

/** The Interface Post Recurring Template Request Mapper. */
@Mapper(componentModel = "spring", uses = DecimalMapper.class)
public interface PostRecurringTemplateRequestMapper {

    /**
     * To recurring template entity.
     *
     * @param postRecurringTemplateV1RequestDto the post recurring template v1 request dto
     * @return the recurring template entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "lastUsedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "movementType", ignore = true)
    @Mapping(target = "offsetsSpendingAverage", ignore = true)
    @Mapping(target = "frequency", ignore = true)
    @Mapping(target = "dayOfMonth", ignore = true)
    @Mapping(target = "autoApply", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    RecurringTemplateEntity toRecurringTemplateEntity(PostRecurringTemplateV1RequestDto postRecurringTemplateV1RequestDto);
}
