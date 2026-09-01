package com.expenses.account.controller;

import java.math.RoundingMode;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.account.mapper.AccountResponseMapper;
import com.expenses.account.repository.AccountRepository;
import com.expenses.api.GetAccountV1Api;
import com.expenses.api.dto.AccountV1ResponseDto;

import lombok.RequiredArgsConstructor;

/** The Class Get Account Controller. */
@RestController
@RequiredArgsConstructor
public class GetAccountController implements GetAccountV1Api {

    /** The account repository. */
    private final AccountRepository accountRepository;

    /** The account response mapper. */
    private final AccountResponseMapper accountResponseMapper;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<AccountV1ResponseDto> getAccountV1(final Integer id) {

        final var accountEntity = this.accountRepository.findById(id);
        final var balance = this.accountRepository.getBalance(id)
                .setScale(2, RoundingMode.HALF_UP)
                .toPlainString();
        final var accountV1ResponseDto = this.accountResponseMapper.toAccountV1Response(accountEntity, balance);
        return ResponseEntity.ok(accountV1ResponseDto);
    }
}
