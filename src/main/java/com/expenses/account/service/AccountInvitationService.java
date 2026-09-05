package com.expenses.account.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expenses.account.AccountMemberRole;
import com.expenses.account.entity.AccountInvitationEntity;
import com.expenses.account.entity.AccountMemberEntity;
import com.expenses.account.repository.AccountInvitationJpaMapper;
import com.expenses.account.repository.AccountJpaMapper;
import com.expenses.account.repository.AccountMemberJpaMapper;
import com.expenses.account.repository.AccountRepository;
import com.expenses.api.dto.AccountInvitationPreviewV1Dto;
import com.expenses.api.dto.AccountInvitationPreviewV1ResponseDto;
import com.expenses.api.dto.AccountInvitationV1Dto;
import com.expenses.api.dto.AccountInvitationV1ResponseDto;
import com.expenses.api.dto.AccountMemberRoleV1Dto;
import com.expenses.api.dto.AccountV1ResponseDto;
import com.expenses.auth.service.CurrentUserService;
import com.expenses.common.exception.AccountException;
import com.expenses.common.exception.constants.ExceptionMessageConstants;
import com.expenses.account.mapper.AccountResponseMapper;

import java.math.RoundingMode;

import lombok.RequiredArgsConstructor;

/** The Class Account Invitation Service. */
@Service
@RequiredArgsConstructor
public class AccountInvitationService {

    private static final int INVITATION_TTL_DAYS = 7;

    /** The account access service. */
    private final AccountAccessService accountAccessService;

    /** The account invitation jpa mapper. */
    private final AccountInvitationJpaMapper accountInvitationJpaMapper;

    /** The account member jpa mapper. */
    private final AccountMemberJpaMapper accountMemberJpaMapper;

    /** The account jpa mapper. */
    private final AccountJpaMapper accountJpaMapper;

    /** The account repository. */
    private final AccountRepository accountRepository;

    /** The account response mapper. */
    private final AccountResponseMapper accountResponseMapper;

    /** The current user service. */
    private final CurrentUserService currentUserService;

    /**
     * Create invitation for account.
     *
     * @param accountId the account id
     * @return the invitation response
     */
    @Transactional
    public AccountInvitationV1ResponseDto createInvitation(final Integer accountId) {

        this.accountAccessService.requireOwner(accountId);
        final var accountInvitationEntity = new AccountInvitationEntity();
        accountInvitationEntity.setAccountId(accountId);
        accountInvitationEntity.setToken(UUID.randomUUID().toString());
        accountInvitationEntity.setCreatedByUserId(this.currentUserService.getRequiredUserId());
        accountInvitationEntity.setExpiresAt(LocalDateTime.now().plusDays(INVITATION_TTL_DAYS));
        final var savedAccountInvitationEntity = this.accountInvitationJpaMapper.save(accountInvitationEntity);
        return AccountInvitationV1ResponseDto.builder()
                .invitation(this.toAccountInvitationV1Dto(savedAccountInvitationEntity))
                .build();
    }

    /**
     * Preview invitation by token.
     *
     * @param token the token
     * @return the preview response
     */
    @Transactional(readOnly = true)
    public AccountInvitationPreviewV1ResponseDto previewInvitation(final String token) {

        final var accountInvitationEntity = this.findByTokenOrThrow(token);
        final var accountEntity = this.accountJpaMapper.findById(accountInvitationEntity.getAccountId())
                .orElseThrow(() -> new AccountException(ExceptionMessageConstants.ACCOUNT_NOT_FOUND));
        final var userId = this.currentUserService.getRequiredUserId();
        final var alreadyMember = this.accountMemberJpaMapper.existsByAccountIdAndUserId(
                accountInvitationEntity.getAccountId(),
                userId);
        final var accountInvitationPreviewV1Dto = AccountInvitationPreviewV1Dto.builder()
                .accountId(accountEntity.getId())
                .accountName(accountEntity.getName())
                .status(this.toPreviewStatus(accountInvitationEntity))
                .expiresAt(accountInvitationEntity.getExpiresAt().atOffset(ZoneOffset.UTC))
                .alreadyMember(alreadyMember)
                .build();
        return AccountInvitationPreviewV1ResponseDto.builder()
                .invitation(accountInvitationPreviewV1Dto)
                .build();
    }

    /**
     * Accept invitation by token.
     *
     * @param token the token
     * @return the joined account response
     */
    @Transactional
    public AccountV1ResponseDto acceptInvitation(final String token) {

        final var accountInvitationEntity = this.findByTokenOrThrow(token);
        this.ensureInvitationAcceptable(accountInvitationEntity);
        final var userId = this.currentUserService.getRequiredUserId();
        if (Objects.equals(accountInvitationEntity.getCreatedByUserId(), userId)) {
            throw new AccountException(ExceptionMessageConstants.ACCOUNT_INVITATION_INVALID, HttpStatus.BAD_REQUEST);
        }
        if (this.accountMemberJpaMapper.existsByAccountIdAndUserId(accountInvitationEntity.getAccountId(), userId)) {
            throw new AccountException(ExceptionMessageConstants.ACCOUNT_INVITATION_ALREADY_MEMBER, HttpStatus.CONFLICT);
        }
        final var accountMemberEntity = new AccountMemberEntity();
        accountMemberEntity.setAccountId(accountInvitationEntity.getAccountId());
        accountMemberEntity.setUserId(userId);
        accountMemberEntity.setRole(AccountMemberRole.MEMBER);
        this.accountMemberJpaMapper.save(accountMemberEntity);
        accountInvitationEntity.setAcceptedAt(LocalDateTime.now());
        accountInvitationEntity.setAcceptedByUserId(userId);
        this.accountInvitationJpaMapper.save(accountInvitationEntity);
        final var accountEntity = this.accountRepository.findById(accountInvitationEntity.getAccountId());
        final var balance = this.accountRepository.getBalance(accountEntity.getId())
                .setScale(2, RoundingMode.HALF_UP)
                .toPlainString();
        return this.accountResponseMapper.toAccountV1Response(
                accountEntity,
                balance,
                AccountMemberRoleV1Dto.MEMBER);
    }

    /**
     * Revoke invitation.
     *
     * @param accountId the account id
     * @param invitationId the invitation id
     */
    @Transactional
    public void revokeInvitation(final Integer accountId, final Integer invitationId) {

        this.accountAccessService.requireOwner(accountId);
        final var accountInvitationEntity = this.accountInvitationJpaMapper
                .findByIdAndAccountId(invitationId, accountId)
                .orElseThrow(() -> new AccountException(ExceptionMessageConstants.ACCOUNT_INVITATION_NOT_FOUND));
        if (Objects.nonNull(accountInvitationEntity.getAcceptedAt())
                || Objects.nonNull(accountInvitationEntity.getRevokedAt())) {
            throw new AccountException(ExceptionMessageConstants.ACCOUNT_INVITATION_INVALID, HttpStatus.BAD_REQUEST);
        }
        accountInvitationEntity.setRevokedAt(LocalDateTime.now());
        this.accountInvitationJpaMapper.save(accountInvitationEntity);
    }

    private AccountInvitationEntity findByTokenOrThrow(final String token) {

        return this.accountInvitationJpaMapper.findByToken(token)
                .orElseThrow(() -> new AccountException(ExceptionMessageConstants.ACCOUNT_INVITATION_NOT_FOUND));
    }

    private void ensureInvitationAcceptable(final AccountInvitationEntity accountInvitationEntity) {

        if (Objects.nonNull(accountInvitationEntity.getRevokedAt())
                || Objects.nonNull(accountInvitationEntity.getAcceptedAt())
                || accountInvitationEntity.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new AccountException(ExceptionMessageConstants.ACCOUNT_INVITATION_INVALID, HttpStatus.BAD_REQUEST);
        }
    }

    private AccountInvitationV1Dto toAccountInvitationV1Dto(final AccountInvitationEntity accountInvitationEntity) {

        return AccountInvitationV1Dto.builder()
                .id(accountInvitationEntity.getId())
                .accountId(accountInvitationEntity.getAccountId())
                .token(accountInvitationEntity.getToken())
                .expiresAt(accountInvitationEntity.getExpiresAt().atOffset(ZoneOffset.UTC))
                .status(this.toInvitationStatus(accountInvitationEntity))
                .build();
    }

    private AccountInvitationV1Dto.StatusEnum toInvitationStatus(final AccountInvitationEntity accountInvitationEntity) {

        if (Objects.nonNull(accountInvitationEntity.getRevokedAt())) {
            return AccountInvitationV1Dto.StatusEnum.REVOKED;
        }
        if (Objects.nonNull(accountInvitationEntity.getAcceptedAt())) {
            return AccountInvitationV1Dto.StatusEnum.ACCEPTED;
        }
        if (accountInvitationEntity.getExpiresAt().isBefore(LocalDateTime.now())) {
            return AccountInvitationV1Dto.StatusEnum.EXPIRED;
        }
        return AccountInvitationV1Dto.StatusEnum.PENDING;
    }

    private AccountInvitationPreviewV1Dto.StatusEnum toPreviewStatus(
            final AccountInvitationEntity accountInvitationEntity) {

        if (Objects.nonNull(accountInvitationEntity.getRevokedAt())) {
            return AccountInvitationPreviewV1Dto.StatusEnum.REVOKED;
        }
        if (Objects.nonNull(accountInvitationEntity.getAcceptedAt())) {
            return AccountInvitationPreviewV1Dto.StatusEnum.ACCEPTED;
        }
        if (accountInvitationEntity.getExpiresAt().isBefore(LocalDateTime.now())) {
            return AccountInvitationPreviewV1Dto.StatusEnum.EXPIRED;
        }
        return AccountInvitationPreviewV1Dto.StatusEnum.PENDING;
    }
}
