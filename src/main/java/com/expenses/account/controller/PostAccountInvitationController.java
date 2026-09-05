package com.expenses.account.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.account.service.AccountInvitationService;
import com.expenses.api.PostAccountInvitationV1Api;
import com.expenses.api.dto.AccountInvitationV1ResponseDto;

import lombok.RequiredArgsConstructor;

/** The Class Post Account Invitation Controller. */
@RestController
@RequiredArgsConstructor
public class PostAccountInvitationController implements PostAccountInvitationV1Api {

    /** The account invitation service. */
    private final AccountInvitationService accountInvitationService;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<AccountInvitationV1ResponseDto> postAccountInvitationV1(final Integer id) {

        final var accountInvitationV1ResponseDto = this.accountInvitationService.createInvitation(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountInvitationV1ResponseDto);
    }
}
