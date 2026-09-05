package com.expenses.account.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.account.service.AccountInvitationService;
import com.expenses.api.PostAccountInvitationAcceptV1Api;
import com.expenses.api.dto.AccountV1ResponseDto;

import lombok.RequiredArgsConstructor;

/** The Class Post Account Invitation Accept Controller. */
@RestController
@RequiredArgsConstructor
public class PostAccountInvitationAcceptController implements PostAccountInvitationAcceptV1Api {

    /** The account invitation service. */
    private final AccountInvitationService accountInvitationService;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<AccountV1ResponseDto> postAccountInvitationAcceptV1(final String token) {

        return ResponseEntity.ok(this.accountInvitationService.acceptInvitation(token));
    }
}
