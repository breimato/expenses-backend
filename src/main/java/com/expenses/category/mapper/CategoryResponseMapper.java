package com.expenses.category.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.expenses.api.dto.CategoryV1Dto;
import com.expenses.api.dto.CategoryV1ResponseDto;
import com.expenses.api.dto.GetCategoriesV1ResponseDto;

import com.expenses.category.entity.CategoryEntity;
import com.expenses.common.EnumMapper;
import com.expenses.common.JsonNullableMapper;

/** The Interface Category Response Mapper. */
@Mapper(componentModel = "spring", uses = { JsonNullableMapper.class, EnumMapper.class })
public interface CategoryResponseMapper {

    /**
     * To category v1 dto.
     *
     * @param categoryEntity the category entity
     * @return the category v1 dto
     */
    @Mapping(target = "icon", source = "icon", qualifiedByName = "mapString")
    CategoryV1Dto toCategoryV1Dto(CategoryEntity categoryEntity);

    /**
     * To category v1 response.
     *
     * @param categoryEntity the category entity
     * @return the category v1 response dto
     */
    @Mapping(target = "category", source = "categoryEntity")
    CategoryV1ResponseDto toCategoryV1Response(CategoryEntity categoryEntity);

    /**
     * To get categories v1 response.
     *
     * @param categoryEntityList the category entity list
     * @return the get categories v1 response dto
     */
    default GetCategoriesV1ResponseDto toGetCategoriesV1Response(final List<CategoryEntity> categoryEntityList) {
        return GetCategoriesV1ResponseDto.builder()
                .categories(categoryEntityList.stream().map(this::toCategoryV1Dto).toList())
                .build();
    }
}
