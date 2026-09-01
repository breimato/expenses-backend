package com.expenses.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expenses.account.entity.AccountTransferEntity;

/** The Interface Account Transfer Jpa Mapper. */
public interface AccountTransferJpaMapper extends JpaRepository<AccountTransferEntity, Integer> {
}
