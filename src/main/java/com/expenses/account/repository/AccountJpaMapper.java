package com.expenses.account.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expenses.account.entity.AccountEntity;

/** The Interface Account Jpa Mapper. */
public interface AccountJpaMapper extends JpaRepository<AccountEntity, Integer> {

    /**
     * Find by id and user id.
     *
     * @param id the id
     * @param userId the user id
     * @return the optional account
     */
    Optional<AccountEntity> findByIdAndUserId(Integer id, Integer userId);

    /**
     * Find all by user id ordered by default first then name.
     *
     * @param userId the user id
     * @return the list
     */
    List<AccountEntity> findByUserIdOrderByIsDefaultDescNameAsc(Integer userId);

    /**
     * Find default account for user.
     *
     * @param userId the user id
     * @return the optional account
     */
    Optional<AccountEntity> findByUserIdAndIsDefaultTrue(Integer userId);
}
