package com.expenses.account.service;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expenses.account.AccountMemberRole;
import com.expenses.account.repository.AccountMemberJpaMapper;
import com.expenses.api.dto.AccountMemberRoleV1Dto;
import com.expenses.api.dto.AccountMemberV1Dto;
import com.expenses.api.dto.GetAccountMembersV1ResponseDto;
import com.expenses.auth.repository.UserJpaMapper;
import com.expenses.auth.service.CurrentUserService;
import com.expenses.common.exception.AccountException;
import com.expenses.common.exception.constants.ExceptionMessageConstants;

import lombok.RequiredArgsConstructor;

/** The Class Account Member Service. */
@Service
@RequiredArgsConstructor
public class AccountMemberService {

    /** The account access service. */
    private final AccountAccessService accountAccessService;

    /** The account member jpa mapper. */
    private final AccountMemberJpaMapper accountMemberJpaMapper;

    /** The user jpa mapper. */
    private final UserJpaMapper userJpaMapper;

    /** The current user service. */
    private final CurrentUserService currentUserService;

    /**
     * List members for account.
     *
     * @param accountId the account id
     * @return the members response
     */
    @Transactional(readOnly = true)
    public GetAccountMembersV1ResponseDto listMembers(final Integer accountId) {

        this.accountAccessService.requireAccountAccess(accountId);
        final var accountMemberV1DtoList = this.accountMemberJpaMapper.findByAccountIdOrderByIdAsc(accountId).stream()
                .map(accountMemberEntity -> {
                    final var userEntity = this.userJpaMapper.findById(accountMemberEntity.getUserId())
                            .orElseThrow(() -> new AccountException(ExceptionMessageConstants.ACCOUNT_MEMBER_NOT_FOUND));
                    return AccountMemberV1Dto.builder()
                            .userId(userEntity.getId())
                            .displayName(userEntity.getDisplayName())
                            .email(userEntity.getEmail())
                            .role(AccountMemberRoleV1Dto.fromValue(accountMemberEntity.getRole().name()))
                            .build();
                })
                .toList();
        return GetAccountMembersV1ResponseDto.builder()
                .members(accountMemberV1DtoList)
                .build();
    }

    /**
     * Remove member from account.
     *
     * @param accountId the account id
     * @param userId the user id
     */
    @Transactional
    public void removeMember(final Integer accountId, final Integer userId) {

        this.accountAccessService.requireOwner(accountId);
        final var accountMemberEntity = this.accountMemberJpaMapper
                .findByAccountIdAndUserId(accountId, userId)
                .orElseThrow(() -> new AccountException(ExceptionMessageConstants.ACCOUNT_MEMBER_NOT_FOUND));
        if (accountMemberEntity.getRole() == AccountMemberRole.OWNER) {
            throw new AccountException(ExceptionMessageConstants.ACCOUNT_CANNOT_REMOVE_OWNER, HttpStatus.BAD_REQUEST);
        }
        this.accountMemberJpaMapper.delete(accountMemberEntity);
    }

    /**
     * Leave shared account.
     *
     * @param accountId the account id
     */
    @Transactional
    public void leaveAccount(final Integer accountId) {

        final var accountMemberEntity = this.accountAccessService.requireMembership(accountId);
        if (accountMemberEntity.getRole() == AccountMemberRole.OWNER) {
            throw new AccountException(ExceptionMessageConstants.ACCOUNT_OWNER_CANNOT_LEAVE, HttpStatus.FORBIDDEN);
        }
        if (!Objects.equals(accountMemberEntity.getUserId(), this.currentUserService.getRequiredUserId())) {
            throw new AccountException(ExceptionMessageConstants.ACCOUNT_FORBIDDEN, HttpStatus.FORBIDDEN);
        }
        this.accountMemberJpaMapper.delete(accountMemberEntity);
    }
}
