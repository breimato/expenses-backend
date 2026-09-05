package com.expenses.account.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.expenses.account.AccountMemberRole;
import com.expenses.account.entity.AccountEntity;
import com.expenses.account.entity.AccountMemberEntity;
import com.expenses.account.repository.AccountJpaMapper;
import com.expenses.account.repository.AccountMemberJpaMapper;
import com.expenses.auth.service.CurrentUserService;
import com.expenses.common.exception.AccountException;
import com.expenses.common.exception.constants.ExceptionMessageConstants;

import lombok.RequiredArgsConstructor;

/** The Class Account Access Service. */
@Service
@RequiredArgsConstructor
public class AccountAccessService {

    /** The account jpa mapper. */
    private final AccountJpaMapper accountJpaMapper;

    /** The account member jpa mapper. */
    private final AccountMemberJpaMapper accountMemberJpaMapper;

    /** The current user service. */
    private final CurrentUserService currentUserService;

    /**
     * Require account access for current user.
     *
     * @param accountId the account id
     * @return the account entity
     */
    public AccountEntity requireAccountAccess(final Integer accountId) {

        this.requireMembership(accountId);
        return this.accountJpaMapper.findById(accountId)
                .orElseThrow(() -> new AccountException(ExceptionMessageConstants.ACCOUNT_NOT_FOUND));
    }

    /**
     * Require membership for current user.
     *
     * @param accountId the account id
     * @return the account member entity
     */
    public AccountMemberEntity requireMembership(final Integer accountId) {

        return this.accountMemberJpaMapper
                .findByAccountIdAndUserId(accountId, this.currentUserService.getRequiredUserId())
                .orElseThrow(() -> new AccountException(ExceptionMessageConstants.ACCOUNT_NOT_FOUND));
    }

    /**
     * Require OWNER role for current user.
     *
     * @param accountId the account id
     * @return the account entity
     */
    public AccountEntity requireOwner(final Integer accountId) {

        final var accountMemberEntity = this.requireMembership(accountId);
        if (accountMemberEntity.getRole() != AccountMemberRole.OWNER) {
            throw new AccountException(ExceptionMessageConstants.ACCOUNT_FORBIDDEN, HttpStatus.FORBIDDEN);
        }
        return this.accountJpaMapper.findById(accountId)
                .orElseThrow(() -> new AccountException(ExceptionMessageConstants.ACCOUNT_NOT_FOUND));
    }

    /**
     * Get current user role for account.
     *
     * @param accountId the account id
     * @return the role
     */
    public AccountMemberRole getCurrentUserRole(final Integer accountId) {

        return this.requireMembership(accountId).getRole();
    }
}
