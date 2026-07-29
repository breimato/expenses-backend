package com.expenses.profile.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.api.PatchProfileV1Api;
import com.expenses.api.dto.PatchProfileV1RequestDto;
import com.expenses.api.dto.ProfileV1ResponseDto;

import com.expenses.profile.entity.ProfileEntity;
import com.expenses.profile.mapper.ProfileResponseMapper;
import com.expenses.profile.repository.ProfileRepository;

import lombok.RequiredArgsConstructor;

/** The Class Patch Profile Controller. */
@RestController
@RequiredArgsConstructor
public class PatchProfileController implements PatchProfileV1Api {

    /** The profile repository. */
    private final ProfileRepository profileRepository;

    /** The profile response mapper. */
    private final ProfileResponseMapper profileResponseMapper;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<ProfileV1ResponseDto> patchProfileV1(final PatchProfileV1RequestDto patchProfileV1RequestDto) {

        final var profileEntity = this.profileRepository.updateProfile(patchProfileV1RequestDto);
        profileEntity.setBalance(this.profileRepository.getBalance());
        final var profileV1ResponseDto = this.profileResponseMapper.toProfileV1Response(profileEntity);
        return ResponseEntity.ok(profileV1ResponseDto);
    }
}
