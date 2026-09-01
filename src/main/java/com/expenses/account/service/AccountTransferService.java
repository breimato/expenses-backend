package com.expenses.account.service;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expenses.account.entity.AccountTransferEntity;
import com.expenses.account.mapper.AccountTransferResponseMapper;
import com.expenses.account.repository.AccountTransferJpaMapper;
import com.expenses.api.dto.AccountTransferV1ResponseDto;
import com.expenses.api.dto.PostAccountTransferV1RequestDto;
import com.expenses.auth.service.CurrentUserService;
import com.expenses.category.repository.CategoryJpaMapper;
import com.expenses.common.DecimalMapper;
import com.expenses.common.MovementType;
import com.expenses.common.exception.AccountException;
import com.expenses.common.exception.constants.ExceptionMessageConstants;
import com.expenses.expense.entity.ExpenseEntity;
import com.expenses.expense.repository.ExpenseJpaMapper;

import lombok.RequiredArgsConstructor;

/** The Class Account Transfer Service. */
@Service
@RequiredArgsConstructor
public class AccountTransferService {

    /** The account access service. */
    private final AccountAccessService accountAccessService;

    /** The account transfer jpa mapper. */
    private final AccountTransferJpaMapper accountTransferJpaMapper;

    /** The expense jpa mapper. */
    private final ExpenseJpaMapper expenseJpaMapper;

    /** The category jpa mapper. */
    private final CategoryJpaMapper categoryJpaMapper;

    /** The current user service. */
    private final CurrentUserService currentUserService;

    /** The decimal mapper. */
    private final DecimalMapper decimalMapper;

    /** The account transfer response mapper. */
    private final AccountTransferResponseMapper accountTransferResponseMapper;

    /**
     * Create transfer between accounts.
     *
     * @param postAccountTransferV1RequestDto the request
     * @return the response
     */
    @Transactional
    public AccountTransferV1ResponseDto createTransfer(final PostAccountTransferV1RequestDto postAccountTransferV1RequestDto) {

        final var userId = this.currentUserService.getRequiredUserId();
        final var fromAccountId = postAccountTransferV1RequestDto.getFromAccountId();
        final var toAccountId = postAccountTransferV1RequestDto.getToAccountId();
        if (Objects.equals(fromAccountId, toAccountId)) {
            throw new AccountException(
                    ExceptionMessageConstants.ACCOUNT_TRANSFER_SAME_ACCOUNT,
                    HttpStatus.BAD_REQUEST);
        }
        this.accountAccessService.requireAccountAccess(fromAccountId);
        this.accountAccessService.requireAccountAccess(toAccountId);
        final var amount = this.decimalMapper.toBigDecimal(postAccountTransferV1RequestDto.getAmount());
        if (Objects.isNull(amount) || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new AccountException(
                    ExceptionMessageConstants.ACCOUNT_TRANSFER_INVALID_AMOUNT,
                    HttpStatus.BAD_REQUEST);
        }
        final var transferOutCategoryEntity = this.categoryJpaMapper
                .findFirstByAccountIdAndMovementType(fromAccountId, MovementType.TRANSFER_OUT)
                .orElseThrow(() -> new AccountException(ExceptionMessageConstants.ACCOUNT_TRANSFER_CATEGORY_MISSING));
        final var transferInCategoryEntity = this.categoryJpaMapper
                .findFirstByAccountIdAndMovementType(toAccountId, MovementType.TRANSFER_IN)
                .orElseThrow(() -> new AccountException(ExceptionMessageConstants.ACCOUNT_TRANSFER_CATEGORY_MISSING));
        final var accountTransferEntity = new AccountTransferEntity();
        accountTransferEntity.setUserId(userId);
        accountTransferEntity.setFromAccountId(fromAccountId);
        accountTransferEntity.setToAccountId(toAccountId);
        accountTransferEntity.setAmount(amount);
        accountTransferEntity.setTransferDate(postAccountTransferV1RequestDto.getTransferDate());
        accountTransferEntity.setDescription(postAccountTransferV1RequestDto.getDescription().trim());
        final var savedTransferEntity = this.accountTransferJpaMapper.save(accountTransferEntity);
        final var transferOutExpenseEntity = this.buildTransferMovement(
                userId,
                fromAccountId,
                transferOutCategoryEntity.getId(),
                amount,
                postAccountTransferV1RequestDto.getTransferDate(),
                postAccountTransferV1RequestDto.getDescription().trim(),
                MovementType.TRANSFER_OUT,
                savedTransferEntity.getId());
        final var transferInExpenseEntity = this.buildTransferMovement(
                userId,
                toAccountId,
                transferInCategoryEntity.getId(),
                amount,
                postAccountTransferV1RequestDto.getTransferDate(),
                postAccountTransferV1RequestDto.getDescription().trim(),
                MovementType.TRANSFER_IN,
                savedTransferEntity.getId());
        this.expenseJpaMapper.save(transferOutExpenseEntity);
        this.expenseJpaMapper.save(transferInExpenseEntity);
        return this.accountTransferResponseMapper.toAccountTransferV1Response(savedTransferEntity);
    }

    private ExpenseEntity buildTransferMovement(
            final Integer userId,
            final Integer accountId,
            final Integer categoryId,
            final BigDecimal amount,
            final java.time.LocalDate transferDate,
            final String description,
            final MovementType movementType,
            final Integer transferId) {

        final var expenseEntity = new ExpenseEntity();
        expenseEntity.setUserId(userId);
        expenseEntity.setAccountId(accountId);
        expenseEntity.setCategoryId(categoryId);
        expenseEntity.setAmount(amount);
        expenseEntity.setDescription(description);
        expenseEntity.setExpenseDate(transferDate);
        expenseEntity.setMovementType(movementType);
        expenseEntity.setOffsetsSpendingAverage(false);
        expenseEntity.setTransferId(transferId);
        return expenseEntity;
    }
}
