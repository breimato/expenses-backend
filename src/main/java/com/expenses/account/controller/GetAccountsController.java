package com.expenses.account.controller;

import java.math.RoundingMode;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.account.mapper.AccountResponseMapper;
import com.expenses.account.repository.AccountRepository;
import com.expenses.api.GetAccountsV1Api;
import com.expenses.api.dto.AccountMemberRoleV1Dto;
import com.expenses.api.dto.GetAccountsV1ResponseDto;

import lombok.RequiredArgsConstructor;

/** The Class Get Accounts Controller. */
@RestController
@RequiredArgsConstructor
public class GetAccountsController implements GetAccountsV1Api {

    /** The account repository. */
    private final AccountRepository accountRepository;

    /** The account response mapper. */
    private final AccountResponseMapper accountResponseMapper;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<GetAccountsV1ResponseDto> getAccountsV1() {

        final var accountEntityList = this.accountRepository.findAllForCurrentUser();
        final var balanceByAccountId = accountEntityList.stream()
                .collect(Collectors.toMap(
                        accountEntity -> accountEntity.getId(),
                        accountEntity -> this.accountRepository.getBalance(accountEntity.getId())
                                .setScale(2, RoundingMode.HALF_UP)
                                .toPlainString()));
        final var roleByAccountId = accountEntityList.stream()
                .collect(Collectors.toMap(
                        accountEntity -> accountEntity.getId(),
                        accountEntity -> AccountMemberRoleV1Dto.fromValue(
                                this.accountRepository.getCurrentUserRole(accountEntity.getId()).name())));
        final var getAccountsV1ResponseDto = this.accountResponseMapper.toGetAccountsV1Response(
                accountEntityList,
                balanceByAccountId,
                roleByAccountId);
        return ResponseEntity.ok(getAccountsV1ResponseDto);
    }
}
