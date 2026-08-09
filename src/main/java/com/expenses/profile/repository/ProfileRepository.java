package com.expenses.profile.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.expenses.api.dto.PatchProfileV1RequestDto;
import com.expenses.auth.service.CurrentUserService;
import com.expenses.common.exception.ProfileException;
import com.expenses.common.exception.constants.ExceptionMessageConstants;
import com.expenses.expense.repository.ExpenseJpaMapper;
import com.expenses.profile.entity.ProfileEntity;
import com.expenses.profile.mapper.PatchProfileRequestMapper;

import lombok.RequiredArgsConstructor;

/** The Class Profile Repository. */
@Component
@RequiredArgsConstructor
public class ProfileRepository {

    /** The profile jpa mapper. */
    private final ProfileJpaMapper profileJpaMapper;

    /** The expense jpa mapper. */
    private final ExpenseJpaMapper expenseJpaMapper;

    /** The patch profile request mapper. */
    private final PatchProfileRequestMapper patchProfileRequestMapper;

    /** The current user service. */
    private final CurrentUserService currentUserService;

    /**
     * Get profile for current user.
     *
     * @return the profile entity
     */
    @Transactional(readOnly = true)
    public ProfileEntity getProfile() {

        return this.profileJpaMapper.findByUserId(this.currentUserService.getRequiredUserId())
                .orElseThrow(() -> new ProfileException(ExceptionMessageConstants.PROFILE_NOT_FOUND));
    }

    /**
     * Update profile for current user.
     *
     * @param patchProfileV1RequestDto the patch profile v1 request dto
     * @return the profile entity
     */
    @Transactional
    public ProfileEntity updateProfile(final PatchProfileV1RequestDto patchProfileV1RequestDto) {

        final var profileEntity = this.getProfile();
        this.patchProfileRequestMapper.updateProfileEntity(patchProfileV1RequestDto, profileEntity);
        return this.profileJpaMapper.save(profileEntity);
    }

    /**
     * Get balance computed from all recorded movements for current user.
     *
     * @return the balance
     */
    @Transactional(readOnly = true)
    public java.math.BigDecimal getBalance() {

        return this.expenseJpaMapper.sumNetBalance(this.currentUserService.getRequiredUserId());
    }
}
