package com.expenses.account.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.account.service.AccountInvitationService;
import com.expenses.api.GetAccountInvitationV1Api;
import com.expenses.api.dto.AccountInvitationPreviewV1ResponseDto;

import lombok.RequiredArgsConstructor;

/** The Class Get Account Invitation Controller. */
@RestController
@RequiredArgsConstructor
public class GetAccountInvitationController implements GetAccountInvitationV1Api {

    /** The account invitation service. */
    private final AccountInvitationService accountInvitationService;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<AccountInvitationPreviewV1ResponseDto> getAccountInvitationV1(final String token) {

        return ResponseEntity.ok(this.accountInvitationService.previewInvitation(token));
    }
}
