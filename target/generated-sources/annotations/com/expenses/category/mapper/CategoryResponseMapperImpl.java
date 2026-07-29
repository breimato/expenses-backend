package com.expenses.category.mapper;

import com.expenses.api.dto.CategoryV1Dto;
import com.expenses.api.dto.CategoryV1ResponseDto;
import com.expenses.category.entity.CategoryEntity;
import com.expenses.common.EnumMapper;
import com.expenses.common.JsonNullableMapper;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-29T10:58:36+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class CategoryResponseMapperImpl implements CategoryResponseMapper {

    @Autowired
    private JsonNullableMapper jsonNullableMapper;
    @Autowired
    private EnumMapper enumMapper;

    @Override
    public CategoryV1Dto toCategoryV1Dto(CategoryEntity categoryEntity) {
        if ( categoryEntity == null ) {
            return null;
        }

        CategoryV1Dto.Builder categoryV1Dto = CategoryV1Dto.builder();

        categoryV1Dto.icon( jsonNullableMapper.mapString( categoryEntity.getIcon() ) );
        categoryV1Dto.id( categoryEntity.getId() );
        categoryV1Dto.name( categoryEntity.getName() );
        categoryV1Dto.color( categoryEntity.getColor() );
        categoryV1Dto.sortOrder( categoryEntity.getSortOrder() );
        categoryV1Dto.movementType( enumMapper.toMovementTypeV1( categoryEntity.getMovementType() ) );

        return categoryV1Dto.build();
    }

    @Override
    public CategoryV1ResponseDto toCategoryV1Response(CategoryEntity categoryEntity) {
        if ( categoryEntity == null ) {
            return null;
        }

        CategoryV1ResponseDto.Builder categoryV1ResponseDto = CategoryV1ResponseDto.builder();

        categoryV1ResponseDto.category( toCategoryV1Dto( categoryEntity ) );

        return categoryV1ResponseDto.build();
    }
}
