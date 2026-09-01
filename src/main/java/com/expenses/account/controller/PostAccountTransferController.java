package com.expenses.account.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.account.service.AccountTransferService;
import com.expenses.api.PostAccountTransferV1Api;
import com.expenses.api.dto.AccountTransferV1ResponseDto;
import com.expenses.api.dto.PostAccountTransferV1RequestDto;

import lombok.RequiredArgsConstructor;

/** The Class Post Account Transfer Controller. */
@RestController
@RequiredArgsConstructor
public class PostAccountTransferController implements PostAccountTransferV1Api {

    /** The account transfer service. */
    private final AccountTransferService accountTransferService;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<AccountTransferV1ResponseDto> postAccountTransferV1(
            final PostAccountTransferV1RequestDto postAccountTransferV1RequestDto) {

        final var accountTransferV1ResponseDto =
                this.accountTransferService.createTransfer(postAccountTransferV1RequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountTransferV1ResponseDto);
    }
}
