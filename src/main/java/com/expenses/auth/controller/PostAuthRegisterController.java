package com.expenses.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.api.PostAuthRegisterV1Api;
import com.expenses.api.dto.AuthV1ResponseDto;
import com.expenses.api.dto.PostAuthRegisterV1RequestDto;
import com.expenses.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

/** The Class Post Auth Register Controller. */
@RestController
@RequiredArgsConstructor
public class PostAuthRegisterController implements PostAuthRegisterV1Api {

    /** The auth service. */
    private final AuthService authService;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<AuthV1ResponseDto> postAuthRegisterV1(
            final PostAuthRegisterV1RequestDto postAuthRegisterV1RequestDto) {

        final var authV1ResponseDto = this.authService.register(postAuthRegisterV1RequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(authV1ResponseDto);
    }
}
