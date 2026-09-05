package com.expenses.account.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.account.service.AccountMemberService;
import com.expenses.api.GetAccountMembersV1Api;
import com.expenses.api.dto.GetAccountMembersV1ResponseDto;

import lombok.RequiredArgsConstructor;

/** The Class Get Account Members Controller. */
@RestController
@RequiredArgsConstructor
public class GetAccountMembersController implements GetAccountMembersV1Api {

    /** The account member service. */
    private final AccountMemberService accountMemberService;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<GetAccountMembersV1ResponseDto> getAccountMembersV1(final Integer id) {

        return ResponseEntity.ok(this.accountMemberService.listMembers(id));
    }
}
