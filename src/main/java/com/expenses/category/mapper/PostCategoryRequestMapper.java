package com.expenses.category.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.expenses.api.dto.PostCategoryV1RequestDto;

import com.expenses.category.entity.CategoryEntity;
import com.expenses.common.JsonNullableMapper;

/** The Interface Post Category Request Mapper. */
@Mapper(componentModel = "spring", uses = JsonNullableMapper.class)
public interface PostCategoryRequestMapper {

    /**
     * To category entity.
     *
     * @param postCategoryV1RequestDto the post category v1 request dto
     * @return the category entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "movementType", ignore = true)
    @Mapping(target = "icon", source = "icon", qualifiedByName = "unmapString")
    CategoryEntity toCategoryEntity(PostCategoryV1RequestDto postCategoryV1RequestDto);
}
