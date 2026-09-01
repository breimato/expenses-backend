package com.expenses.account.controller;

import java.math.RoundingMode;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.account.mapper.AccountResponseMapper;
import com.expenses.account.repository.AccountRepository;
import com.expenses.api.PatchAccountV1Api;
import com.expenses.api.dto.AccountV1ResponseDto;
import com.expenses.api.dto.PatchAccountV1RequestDto;

import lombok.RequiredArgsConstructor;

/** The Class Patch Account Controller. */
@RestController
@RequiredArgsConstructor
public class PatchAccountController implements PatchAccountV1Api {

    /** The account repository. */
    private final AccountRepository accountRepository;

    /** The account response mapper. */
    private final AccountResponseMapper accountResponseMapper;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<AccountV1ResponseDto> patchAccountV1(
            final Integer id,
            final PatchAccountV1RequestDto patchAccountV1RequestDto) {

        final var accountEntity = this.accountRepository.update(id, patchAccountV1RequestDto);
        final var balance = this.accountRepository.getBalance(id)
                .setScale(2, RoundingMode.HALF_UP)
                .toPlainString();
        final var accountV1ResponseDto = this.accountResponseMapper.toAccountV1Response(accountEntity, balance);
        return ResponseEntity.ok(accountV1ResponseDto);
    }
}
