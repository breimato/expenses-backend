package com.expenses.category.mapper;

import com.expenses.api.dto.PostCategoryV1RequestDto;
import com.expenses.category.entity.CategoryEntity;
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
public class PostCategoryRequestMapperImpl implements PostCategoryRequestMapper {

    @Autowired
    private JsonNullableMapper jsonNullableMapper;

    @Override
    public CategoryEntity toCategoryEntity(PostCategoryV1RequestDto postCategoryV1RequestDto) {
        if ( postCategoryV1RequestDto == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setIcon( jsonNullableMapper.unmapString( postCategoryV1RequestDto.getIcon() ) );
        if ( postCategoryV1RequestDto.getSortOrder() != null ) {
            categoryEntity.setSortOrder( postCategoryV1RequestDto.getSortOrder() );
        }
        else {
            categoryEntity.setSortOrder( postCategoryV1RequestDto.getSortOrder() != null ? postCategoryV1RequestDto.getSortOrder() : 0 );
        }
        categoryEntity.setName( postCategoryV1RequestDto.getName() );
        categoryEntity.setColor( postCategoryV1RequestDto.getColor() );

        return categoryEntity;
    }
}
