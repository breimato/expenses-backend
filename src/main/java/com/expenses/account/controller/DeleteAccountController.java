package com.expenses.account.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.account.repository.AccountRepository;
import com.expenses.api.DeleteAccountV1Api;

import lombok.RequiredArgsConstructor;

/** The Class Delete Account Controller. */
@RestController
@RequiredArgsConstructor
public class DeleteAccountController implements DeleteAccountV1Api {

    /** The account repository. */
    private final AccountRepository accountRepository;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<Void> deleteAccountV1(final Integer id) {

        this.accountRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
