package com.expenses.profile.mapper;

import com.expenses.api.dto.ProfileV1Dto;
import com.expenses.api.dto.ProfileV1ResponseDto;
import com.expenses.common.DecimalMapper;
import com.expenses.profile.entity.ProfileEntity;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-29T10:58:36+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class ProfileResponseMapperImpl implements ProfileResponseMapper {

    @Autowired
    private DecimalMapper decimalMapper;

    @Override
    public ProfileV1Dto toProfileV1Dto(ProfileEntity profileEntity) {
        if ( profileEntity == null ) {
            return null;
        }

        ProfileV1Dto.Builder profileV1Dto = ProfileV1Dto.builder();

        profileV1Dto.displayName( profileEntity.getDisplayName() );
        profileV1Dto.balance( decimalMapper.toString( profileEntity.getBalance() ) );

        return profileV1Dto.build();
    }

    @Override
    public ProfileV1ResponseDto toProfileV1Response(ProfileEntity profileEntity) {
        if ( profileEntity == null ) {
            return null;
        }

        ProfileV1ResponseDto.Builder profileV1ResponseDto = ProfileV1ResponseDto.builder();

        profileV1ResponseDto.profile( toProfileV1Dto( profileEntity ) );

        return profileV1ResponseDto.build();
    }
}
