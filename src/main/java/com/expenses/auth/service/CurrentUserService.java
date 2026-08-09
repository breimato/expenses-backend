package com.expenses.auth.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.expenses.common.exception.AuthException;
import com.expenses.common.exception.constants.ExceptionMessageConstants;

/** The Class Current User Service. */
@Service
public class CurrentUserService {

    /**
     * Get required authenticated user id.
     *
     * @return the user id
     */
    public Integer getRequiredUserId() {

        final var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof Jwt jwt)) {
            throw new AuthException(ExceptionMessageConstants.AUTH_UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
        }
        return Integer.valueOf(jwt.getSubject());
    }
}
