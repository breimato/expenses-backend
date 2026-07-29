package com.expenses.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expenses.profile.entity.ProfileEntity;

/** The Interface Profile Jpa Mapper. */
public interface ProfileJpaMapper extends JpaRepository<ProfileEntity, Integer> {

}
