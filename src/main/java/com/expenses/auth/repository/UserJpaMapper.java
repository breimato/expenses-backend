package com.expenses.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expenses.auth.entity.UserEntity;

/** The Interface User Jpa Mapper. */
public interface UserJpaMapper extends JpaRepository<UserEntity, Integer> {

    /**
     * Find by email ignoring case.
     *
     * @param email the email
     * @return the optional user
     */
    Optional<UserEntity> findByEmailIgnoreCase(String email);

    /**
     * Check if email exists ignoring case.
     *
     * @param email the email
     * @return true if exists
     */
    boolean existsByEmailIgnoreCase(String email);
}
