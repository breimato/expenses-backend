package com.expenses.category.mapper;

import com.expenses.api.dto.MovementTypeV1;
import com.expenses.api.dto.PatchCategoryV1RequestDto;
import com.expenses.category.entity.CategoryEntity;
import com.expenses.common.JsonNullableMapper;
import com.expenses.common.MovementType;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-29T10:58:36+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class PatchCategoryRequestMapperImpl implements PatchCategoryRequestMapper {

    @Autowired
    private JsonNullableMapper jsonNullableMapper;

    @Override
    public void updateCategoryEntity(PatchCategoryV1RequestDto patchCategoryV1RequestDto, CategoryEntity categoryEntity) {
        if ( patchCategoryV1RequestDto == null ) {
            return;
        }

        if ( patchCategoryV1RequestDto.getIcon() != null ) {
            categoryEntity.setIcon( jsonNullableMapper.unmapString( patchCategoryV1RequestDto.getIcon() ) );
        }
        if ( patchCategoryV1RequestDto.getName() != null ) {
            categoryEntity.setName( patchCategoryV1RequestDto.getName() );
        }
        if ( patchCategoryV1RequestDto.getColor() != null ) {
            categoryEntity.setColor( patchCategoryV1RequestDto.getColor() );
        }
        if ( patchCategoryV1RequestDto.getSortOrder() != null ) {
            categoryEntity.setSortOrder( patchCategoryV1RequestDto.getSortOrder() );
        }
        if ( patchCategoryV1RequestDto.getMovementType() != null ) {
            categoryEntity.setMovementType( movementTypeV1ToMovementType( patchCategoryV1RequestDto.getMovementType() ) );
        }
    }

    protected MovementType movementTypeV1ToMovementType(MovementTypeV1 movementTypeV1) {
        if ( movementTypeV1 == null ) {
            return null;
        }

        MovementType movementType;

        switch ( movementTypeV1 ) {
            case EXPENSE: movementType = MovementType.EXPENSE;
            break;
            case INCOME: movementType = MovementType.INCOME;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + movementTypeV1 );
        }

        return movementType;
    }
}
