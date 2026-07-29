package com.expenses.profile.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.expenses.api.dto.ProfileV1Dto;
import com.expenses.api.dto.ProfileV1ResponseDto;

import com.expenses.common.DecimalMapper;
import com.expenses.profile.entity.ProfileEntity;

/** The Interface Profile Response Mapper. */
@Mapper(componentModel = "spring", uses = DecimalMapper.class)
public interface ProfileResponseMapper {

    /**
     * To profile v1 dto.
     *
     * @param profileEntity the profile entity
     * @return the profile v1 dto
     */
    ProfileV1Dto toProfileV1Dto(ProfileEntity profileEntity);

    /**
     * To profile v1 response.
     *
     * @param profileEntity the profile entity
     * @return the profile v1 response dto
     */
    @Mapping(target = "profile", source = "profileEntity")
    ProfileV1ResponseDto toProfileV1Response(ProfileEntity profileEntity);
}
