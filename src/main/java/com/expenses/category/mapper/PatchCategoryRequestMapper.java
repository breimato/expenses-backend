package com.expenses.category.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.expenses.api.dto.PatchCategoryV1RequestDto;

import com.expenses.category.entity.CategoryEntity;
import com.expenses.common.JsonNullableMapper;

/** The Interface Patch Category Request Mapper. */
@Mapper(componentModel = "spring", uses = JsonNullableMapper.class)
public interface PatchCategoryRequestMapper {

    /**
     * Update category entity.
     *
     * @param patchCategoryV1RequestDto the patch category v1 request dto
     * @param categoryEntity the category entity
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "icon", source = "icon", qualifiedByName = "unmapString")
    void updateCategoryEntity(PatchCategoryV1RequestDto patchCategoryV1RequestDto, @MappingTarget CategoryEntity categoryEntity);
}
