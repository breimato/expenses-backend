package com.expenses.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expenses.account.entity.AccountInvitationEntity;

/** The Interface Account Invitation Jpa Mapper. */
public interface AccountInvitationJpaMapper extends JpaRepository<AccountInvitationEntity, Integer> {

    /**
     * Find by token.
     *
     * @param token the token
     * @return the optional invitation
     */
    Optional<AccountInvitationEntity> findByToken(String token);

    /**
     * Find by id and account id.
     *
     * @param id the id
     * @param accountId the account id
     * @return the optional invitation
     */
    Optional<AccountInvitationEntity> findByIdAndAccountId(Integer id, Integer accountId);
}
