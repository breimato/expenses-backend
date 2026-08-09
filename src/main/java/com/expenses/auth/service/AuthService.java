package com.expenses.auth.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expenses.api.dto.AuthUserV1Dto;
import com.expenses.api.dto.AuthV1ResponseDto;
import com.expenses.api.dto.PostAuthLoginV1RequestDto;
import com.expenses.api.dto.PostAuthRegisterV1RequestDto;
import com.expenses.auth.entity.UserEntity;
import com.expenses.auth.repository.UserJpaMapper;
import com.expenses.category.entity.CategoryEntity;
import com.expenses.category.repository.CategoryJpaMapper;
import com.expenses.common.MovementType;
import com.expenses.common.exception.AuthException;
import com.expenses.common.exception.constants.ExceptionMessageConstants;
import com.expenses.profile.entity.ProfileEntity;
import com.expenses.profile.repository.ProfileJpaMapper;

import lombok.RequiredArgsConstructor;

/** The Class Auth Service. */
@Service
@RequiredArgsConstructor
public class AuthService {

    private static final List<DefaultCategory> DEFAULT_CATEGORIES = List.of(
            new DefaultCategory("Comida", "#EF4444", "🍔", MovementType.EXPENSE),
            new DefaultCategory("Transporte", "#3B82F6", "🚌", MovementType.EXPENSE),
            new DefaultCategory("Ocio", "#8B5CF6", "🎮", MovementType.EXPENSE),
            new DefaultCategory("Hogar", "#22C55E", "🏠", MovementType.EXPENSE),
            new DefaultCategory("Otros", "#6B7280", "📦", MovementType.EXPENSE),
            new DefaultCategory("Salario", "#16A34A", "wallet", MovementType.INCOME),
            new DefaultCategory("Reembolso", "#0D9488", "refresh", MovementType.INCOME),
            new DefaultCategory("Otros ingresos", "#65A30D", "plus", MovementType.INCOME));

    /** The user jpa mapper. */
    private final UserJpaMapper userJpaMapper;

    /** The profile jpa mapper. */
    private final ProfileJpaMapper profileJpaMapper;

    /** The category jpa mapper. */
    private final CategoryJpaMapper categoryJpaMapper;

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
        this.seedProfile(savedUserEntity);
        this.seedCategories(savedUserEntity.getId());
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

    private void seedProfile(final UserEntity userEntity) {

        final var profileEntity = new ProfileEntity();
        profileEntity.setUserId(userEntity.getId());
        profileEntity.setDisplayName(userEntity.getDisplayName());
        profileEntity.setBalance(BigDecimal.ZERO);
        this.profileJpaMapper.save(profileEntity);
    }

    private void seedCategories(final Integer userId) {

        for (final var defaultCategory : DEFAULT_CATEGORIES) {
            final var categoryEntity = new CategoryEntity();
            categoryEntity.setUserId(userId);
            categoryEntity.setName(defaultCategory.name());
            categoryEntity.setColor(defaultCategory.color());
            categoryEntity.setIcon(defaultCategory.icon());
            categoryEntity.setMovementType(defaultCategory.movementType());
            this.categoryJpaMapper.save(categoryEntity);
        }
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

    private record DefaultCategory(String name, String color, String icon, MovementType movementType) {
    }
}
