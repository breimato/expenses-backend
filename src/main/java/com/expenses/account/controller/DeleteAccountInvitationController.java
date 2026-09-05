package com.expenses.account.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.account.service.AccountInvitationService;
import com.expenses.api.DeleteAccountInvitationV1Api;

import lombok.RequiredArgsConstructor;

/** The Class Delete Account Invitation Controller. */
@RestController
@RequiredArgsConstructor
public class DeleteAccountInvitationController implements DeleteAccountInvitationV1Api {

    /** The account invitation service. */
    private final AccountInvitationService accountInvitationService;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<Void> deleteAccountInvitationV1(final Integer id, final Integer invitationId) {

        this.accountInvitationService.revokeInvitation(id, invitationId);
        return ResponseEntity.noContent().build();
    }
}
