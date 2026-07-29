package com.expenses.recurring.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.expenses.api.dto.GetRecurringTemplatesV1ResponseDto;
import com.expenses.api.dto.RecurringTemplateV1Dto;
import com.expenses.api.dto.RecurringTemplateV1ResponseDto;

import com.expenses.common.DateMapper;
import com.expenses.common.DecimalMapper;
import com.expenses.common.EnumMapper;
import com.expenses.common.JsonNullableMapper;
import com.expenses.recurring.entity.RecurringTemplateEntity;

/** The Interface Recurring Template Response Mapper. */
@Mapper(componentModel = "spring", uses = { DateMapper.class, DecimalMapper.class, JsonNullableMapper.class, EnumMapper.class })
public interface RecurringTemplateResponseMapper {

    /**
     * To recurring template v1 dto.
     *
     * @param recurringTemplateEntity the recurring template entity
     * @return the recurring template v1 dto
     */
    @Mapping(target = "lastUsedAt", source = "lastUsedAt", qualifiedByName = "mapOffsetDateTime")
    @Mapping(target = "dayOfMonth", source = "dayOfMonth", qualifiedByName = "mapInteger")
    RecurringTemplateV1Dto toRecurringTemplateV1Dto(RecurringTemplateEntity recurringTemplateEntity);

    /**
     * To recurring template v1 response.
     *
     * @param recurringTemplateEntity the recurring template entity
     * @return the recurring template v1 response dto
     */
    @Mapping(target = "recurringTemplate", source = "recurringTemplateEntity")
    RecurringTemplateV1ResponseDto toRecurringTemplateV1Response(RecurringTemplateEntity recurringTemplateEntity);

    /**
     * To get recurring templates v1 response.
     *
     * @param recurringTemplateEntityList the recurring template entity list
     * @return the get recurring templates v1 response dto
     */
    default GetRecurringTemplatesV1ResponseDto toGetRecurringTemplatesV1Response(
            final List<RecurringTemplateEntity> recurringTemplateEntityList) {
        return GetRecurringTemplatesV1ResponseDto.builder()
                .recurringTemplates(recurringTemplateEntityList.stream().map(this::toRecurringTemplateV1Dto).toList())
                .build();
    }
}
