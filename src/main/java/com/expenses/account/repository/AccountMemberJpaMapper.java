package com.expenses.account.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.expenses.account.entity.AccountEntity;
import com.expenses.account.entity.AccountMemberEntity;

/** The Interface Account Member Jpa Mapper. */
public interface AccountMemberJpaMapper extends JpaRepository<AccountMemberEntity, Integer> {

    /**
     * Find by account id and user id.
     *
     * @param accountId the account id
     * @param userId the user id
     * @return the optional membership
     */
    Optional<AccountMemberEntity> findByAccountIdAndUserId(Integer accountId, Integer userId);

    /**
     * Find all by account id.
     *
     * @param accountId the account id
     * @return the list
     */
    List<AccountMemberEntity> findByAccountIdOrderByIdAsc(Integer accountId);

    /**
     * Find all by user id.
     *
     * @param userId the user id
     * @return the list
     */
    List<AccountMemberEntity> findByUserId(Integer userId);

    /**
     * Exists by account id and user id.
     *
     * @param accountId the account id
     * @param userId the user id
     * @return true if present
     */
    boolean existsByAccountIdAndUserId(Integer accountId, Integer userId);

    /**
     * Find accessible accounts for user ordered by default then name.
     *
     * @param userId the user id
     * @return the list
     */
    @Query("""
            SELECT accountEntity FROM AccountEntity accountEntity
            WHERE EXISTS (
                SELECT 1 FROM AccountMemberEntity accountMemberEntity
                WHERE accountMemberEntity.accountId = accountEntity.id
                  AND accountMemberEntity.userId = :userId
            )
            ORDER BY accountEntity.isDefault DESC, accountEntity.name ASC
            """)
    List<AccountEntity> findAccessibleAccountsByUserId(@Param("userId") Integer userId);
}
