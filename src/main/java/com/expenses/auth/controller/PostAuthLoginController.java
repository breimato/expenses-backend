package com.expenses.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.api.PostAuthLoginV1Api;
import com.expenses.api.dto.AuthV1ResponseDto;
import com.expenses.api.dto.PostAuthLoginV1RequestDto;
import com.expenses.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

/** The Class Post Auth Login Controller. */
@RestController
@RequiredArgsConstructor
public class PostAuthLoginController implements PostAuthLoginV1Api {

    /** The auth service. */
    private final AuthService authService;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<AuthV1ResponseDto> postAuthLoginV1(
            final PostAuthLoginV1RequestDto postAuthLoginV1RequestDto) {

        final var authV1ResponseDto = this.authService.login(postAuthLoginV1RequestDto);
        return ResponseEntity.ok(authV1ResponseDto);
    }
}
