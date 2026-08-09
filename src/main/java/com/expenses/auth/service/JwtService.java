package com.expenses.auth.service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

import com.expenses.auth.entity.UserEntity;

/** The Class Jwt Service. */
@Service
public class JwtService {

    /** The jwt encoder. */
    private final JwtEncoder jwtEncoder;

    /** The jwt expiration minutes. */
    private final long jwtExpirationMinutes;

    /**
     * Instantiates a new jwt service.
     *
     * @param jwtSecret the jwt secret
     * @param jwtExpirationMinutes the jwt expiration minutes
     */
    public JwtService(
            @Value("${expenses.auth.jwt-secret}") final String jwtSecret,
            @Value("${expenses.auth.jwt-expiration-minutes}") final long jwtExpirationMinutes) {

        final SecretKey secretKey = new SecretKeySpec(jwtSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        this.jwtEncoder = new NimbusJwtEncoder(new ImmutableSecret<>(secretKey));
        this.jwtExpirationMinutes = jwtExpirationMinutes;
    }

    /**
     * Create access token for user.
     *
     * @param userEntity the user entity
     * @return the access token
     */
    public String createAccessToken(final UserEntity userEntity) {

        final var now = Instant.now();
        final var jwtClaimsSet = JwtClaimsSet.builder()
                .subject(String.valueOf(userEntity.getId()))
                .claim("email", userEntity.getEmail())
                .issuedAt(now)
                .expiresAt(now.plus(this.jwtExpirationMinutes, ChronoUnit.MINUTES))
                .build();
        final var jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, jwtClaimsSet)).getTokenValue();
    }
}
