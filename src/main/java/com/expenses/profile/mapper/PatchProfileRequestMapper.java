package com.expenses.profile.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.expenses.api.dto.PatchProfileV1RequestDto;

import com.expenses.common.DecimalMapper;
import com.expenses.profile.entity.ProfileEntity;

/** The Interface Patch Profile Request Mapper. */
@Mapper(componentModel = "spring", uses = DecimalMapper.class)
public interface PatchProfileRequestMapper {

    /**
     * Update profile entity.
     *
     * @param patchProfileV1RequestDto the patch profile v1 request dto
     * @param profileEntity the profile entity
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfileEntity(PatchProfileV1RequestDto patchProfileV1RequestDto, @MappingTarget ProfileEntity profileEntity);
}
