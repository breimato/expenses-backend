package com.expenses.profile.mapper;

import com.expenses.api.dto.PatchProfileV1RequestDto;
import com.expenses.profile.entity.ProfileEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-29T10:58:36+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class PatchProfileRequestMapperImpl implements PatchProfileRequestMapper {

    @Override
    public void updateProfileEntity(PatchProfileV1RequestDto patchProfileV1RequestDto, ProfileEntity profileEntity) {
        if ( patchProfileV1RequestDto == null ) {
            return;
        }

        if ( patchProfileV1RequestDto.getDisplayName() != null ) {
            profileEntity.setDisplayName( patchProfileV1RequestDto.getDisplayName() );
        }
    }
}
