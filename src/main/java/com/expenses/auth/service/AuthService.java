package com.expenses.auth.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expenses.account.repository.AccountRepository;
import com.expenses.api.dto.AuthUserV1Dto;
import com.expenses.api.dto.AuthV1ResponseDto;
import com.expenses.api.dto.PostAuthLoginV1RequestDto;
import com.expenses.api.dto.PostAuthRegisterV1RequestDto;
import com.expenses.auth.entity.UserEntity;
import com.expenses.auth.repository.UserJpaMapper;
import com.expenses.common.exception.AuthException;
import com.expenses.common.exception.constants.ExceptionMessageConstants;

import lombok.RequiredArgsConstructor;

/** The Class Auth Service. */
@Service
@RequiredArgsConstructor
public class AuthService {

    /** The user jpa mapper. */
    private final UserJpaMapper userJpaMapper;

    /** The account repository. */
    private final AccountRepository accountRepository;

    /** The password encoder. */
    private final PasswordEncoder passwordEncoder;

    /** The jwt service. */
    private final JwtService jwtService;

    /**
     * Register a new user.
     *
     * @param postAuthRegisterV1RequestDto the register request
     * @return the auth response
     */
    @Transactional
    public AuthV1ResponseDto register(final PostAuthRegisterV1RequestDto postAuthRegisterV1RequestDto) {

        final var email = postAuthRegisterV1RequestDto.getEmail().trim().toLowerCase();
        if (this.userJpaMapper.existsByEmailIgnoreCase(email)) {
            throw new AuthException(ExceptionMessageConstants.AUTH_EMAIL_ALREADY_EXISTS, HttpStatus.CONFLICT);
        }
        final var userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setPasswordHash(this.passwordEncoder.encode(postAuthRegisterV1RequestDto.getPassword()));
        userEntity.setDisplayName(postAuthRegisterV1RequestDto.getDisplayName().trim());
        final var savedUserEntity = this.userJpaMapper.save(userEntity);
        this.accountRepository.createDefaultAccount(savedUserEntity.getId(), savedUserEntity.getDisplayName());
        return this.toAuthResponse(savedUserEntity);
    }

    /**
     * Login an existing user.
     *
     * @param postAuthLoginV1RequestDto the login request
     * @return the auth response
     */
    @Transactional(readOnly = true)
    public AuthV1ResponseDto login(final PostAuthLoginV1RequestDto postAuthLoginV1RequestDto) {

        final var email = postAuthLoginV1RequestDto.getEmail().trim().toLowerCase();
        final var userEntity = this.userJpaMapper.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new AuthException(
                        ExceptionMessageConstants.AUTH_INVALID_CREDENTIALS,
                        HttpStatus.UNAUTHORIZED));
        if (!this.passwordEncoder.matches(postAuthLoginV1RequestDto.getPassword(), userEntity.getPasswordHash())) {
            throw new AuthException(ExceptionMessageConstants.AUTH_INVALID_CREDENTIALS, HttpStatus.UNAUTHORIZED);
        }
        return this.toAuthResponse(userEntity);
    }

    private AuthV1ResponseDto toAuthResponse(final UserEntity userEntity) {

        final var authUserV1Dto = AuthUserV1Dto.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .displayName(userEntity.getDisplayName())
                .build();
        return AuthV1ResponseDto.builder()
                .accessToken(this.jwtService.createAccessToken(userEntity))
                .user(authUserV1Dto)
                .build();
    }
}
