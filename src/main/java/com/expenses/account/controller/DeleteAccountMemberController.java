package com.expenses.account.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.account.service.AccountMemberService;
import com.expenses.api.DeleteAccountMemberV1Api;

import lombok.RequiredArgsConstructor;

/** The Class Delete Account Member Controller. */
@RestController
@RequiredArgsConstructor
public class DeleteAccountMemberController implements DeleteAccountMemberV1Api {

    /** The account member service. */
    private final AccountMemberService accountMemberService;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<Void> deleteAccountMemberV1(final Integer id, final Integer userId) {

        this.accountMemberService.removeMember(id, userId);
        return ResponseEntity.noContent().build();
    }
}
