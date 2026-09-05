package com.expenses.account.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.account.service.AccountMemberService;
import com.expenses.api.PostAccountLeaveV1Api;

import lombok.RequiredArgsConstructor;

/** The Class Post Account Leave Controller. */
@RestController
@RequiredArgsConstructor
public class PostAccountLeaveController implements PostAccountLeaveV1Api {

    /** The account member service. */
    private final AccountMemberService accountMemberService;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<Void> postAccountLeaveV1(final Integer id) {

        this.accountMemberService.leaveAccount(id);
        return ResponseEntity.noContent().build();
    }
}
