package com.expenses.account.service;

import org.springframework.stereotype.Service;

import com.expenses.account.entity.AccountEntity;
import com.expenses.account.repository.AccountJpaMapper;
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

    /** The current user service. */
    private final CurrentUserService currentUserService;

    /**
     * Require account access for current user.
     *
     * @param accountId the account id
     * @return the account entity
     */
    public AccountEntity requireAccountAccess(final Integer accountId) {

        return this.accountJpaMapper.findByIdAndUserId(accountId, this.currentUserService.getRequiredUserId())
                .orElseThrow(() -> new AccountException(ExceptionMessageConstants.ACCOUNT_NOT_FOUND));
    }
}
