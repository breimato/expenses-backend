package com.expenses.profile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expenses.profile.entity.ProfileEntity;

/** The Interface Profile Jpa Mapper. */
public interface ProfileJpaMapper extends JpaRepository<ProfileEntity, Integer> {

    /**
     * Find by user id.
     *
     * @param userId the user id
     * @return the optional profile
     */
    Optional<ProfileEntity> findByUserId(Integer userId);
}
