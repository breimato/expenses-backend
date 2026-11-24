package com.expenses.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.expenses.account.entity.AccountTransferEntity;

/** The Interface Account Transfer Jpa Mapper. */
public interface AccountTransferJpaMapper extends JpaRepository<AccountTransferEntity, Integer> {

    /**
     * Delete all transfers where the account is origin or destination.
     *
     * @param accountId the account id
     * @return the deleted count
     */
    @Modifying
    @Query("DELETE FROM AccountTransferEntity transfer WHERE transfer.fromAccountId = :accountId"
            + " OR transfer.toAccountId = :accountId")
    int deleteAllByAccountId(@Param("accountId") Integer accountId);
}
