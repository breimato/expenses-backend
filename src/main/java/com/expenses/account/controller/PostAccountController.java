package com.expenses.account.controller;

import java.math.RoundingMode;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.account.mapper.AccountResponseMapper;
import com.expenses.account.repository.AccountRepository;
import com.expenses.api.PostAccountV1Api;
import com.expenses.api.dto.AccountV1ResponseDto;
import com.expenses.api.dto.PostAccountV1RequestDto;

import lombok.RequiredArgsConstructor;

/** The Class Post Account Controller. */
@RestController
@RequiredArgsConstructor
public class PostAccountController implements PostAccountV1Api {

    /** The account repository. */
    private final AccountRepository accountRepository;

    /** The account response mapper. */
    private final AccountResponseMapper accountResponseMapper;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<AccountV1ResponseDto> postAccountV1(final PostAccountV1RequestDto postAccountV1RequestDto) {

        final var accountEntity = this.accountRepository.create(postAccountV1RequestDto);
        final var balance = this.accountRepository.getBalance(accountEntity.getId())
                .setScale(2, RoundingMode.HALF_UP)
                .toPlainString();
        final var accountV1ResponseDto = this.accountResponseMapper.toAccountV1Response(accountEntity, balance);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountV1ResponseDto);
    }
}
