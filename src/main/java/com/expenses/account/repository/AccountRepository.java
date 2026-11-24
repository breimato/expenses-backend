package com.expenses.account.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.expenses.account.AccountMemberRole;
import com.expenses.account.entity.AccountEntity;
import com.expenses.account.entity.AccountMemberEntity;
import com.expenses.account.service.AccountAccessService;
import com.expenses.api.dto.PatchAccountV1RequestDto;
import com.expenses.api.dto.PostAccountV1RequestDto;
import com.expenses.auth.service.CurrentUserService;
import com.expenses.category.service.CategorySeedService;
import com.expenses.common.exception.AccountException;
import com.expenses.common.exception.constants.ExceptionMessageConstants;
import com.expenses.expense.repository.ExpenseJpaMapper;

import lombok.RequiredArgsConstructor;

/** The Class Account Repository. */
@Component
@RequiredArgsConstructor
public class AccountRepository {

    /** The account jpa mapper. */
    private final AccountJpaMapper accountJpaMapper;

    /** The account member jpa mapper. */
    private final AccountMemberJpaMapper accountMemberJpaMapper;

    /** The account transfer jpa mapper. */
    private final AccountTransferJpaMapper accountTransferJpaMapper;

    /** The expense jpa mapper. */
    private final ExpenseJpaMapper expenseJpaMapper;

    /** The current user service. */
    private final CurrentUserService currentUserService;

    /** The account access service. */
    private final AccountAccessService accountAccessService;

    /** The category seed service. */
    private final CategorySeedService categorySeedService;

    /**
     * Find all accounts for current user.
     *
     * @return the list
     */
    @Transactional(readOnly = true)
    public List<AccountEntity> findAllForCurrentUser() {

        return this.accountMemberJpaMapper.findAccessibleAccountsByUserId(
                this.currentUserService.getRequiredUserId());
    }

    /**
     * Find by id for current user.
     *
     * @param accountId the account id
     * @return the account entity
     */
    @Transactional(readOnly = true)
    public AccountEntity findById(final Integer accountId) {

        return this.accountAccessService.requireAccountAccess(accountId);
    }

    /**
     * Create default account for user.
     *
     * @param userId the user id
     * @param displayName the display name
     * @return the account entity
     */
    @Transactional
    public AccountEntity createDefaultAccount(final Integer userId, final String displayName) {

        final var accountEntity = new AccountEntity();
        accountEntity.setUserId(userId);
        accountEntity.setName(displayName);
        accountEntity.setDefault(true);
        final var savedAccountEntity = this.accountJpaMapper.save(accountEntity);
        this.createOwnerMembership(savedAccountEntity.getId(), userId);
        this.categorySeedService.seedDefaultCategories(userId, savedAccountEntity.getId());
        return savedAccountEntity;
    }

    /**
     * Create account for current user.
     *
     * @param postAccountV1RequestDto the post account v1 request dto
     * @return the account entity
     */
    @Transactional
    public AccountEntity create(final PostAccountV1RequestDto postAccountV1RequestDto) {

        final var userId = this.currentUserService.getRequiredUserId();
        final var accountEntity = new AccountEntity();
        accountEntity.setUserId(userId);
        accountEntity.setName(postAccountV1RequestDto.getName().trim());
        accountEntity.setDefault(false);
        final var savedAccountEntity = this.accountJpaMapper.save(accountEntity);
        this.createOwnerMembership(savedAccountEntity.getId(), userId);
        this.categorySeedService.seedDefaultCategories(userId, savedAccountEntity.getId());
        return savedAccountEntity;
    }

    /**
     * Delete account with all its data for current user (OWNER only).
     *
     * @param accountId the account id
     */
    @Transactional
    public void delete(final Integer accountId) {

        final var accountEntity = this.accountAccessService.requireOwner(accountId);
        if (accountEntity.isDefault()) {
            throw new AccountException(ExceptionMessageConstants.ACCOUNT_DEFAULT_CANNOT_DELETE, HttpStatus.CONFLICT);
        }
        this.accountTransferJpaMapper.deleteAllByAccountId(accountId);
        this.expenseJpaMapper.deleteAllByAccountId(accountId);
        this.accountJpaMapper.delete(accountEntity);
    }

    /**
     * Update account for current user (OWNER only).
     *
     * @param accountId the account id
     * @param patchAccountV1RequestDto the patch account v1 request dto
     * @return the account entity
     */
    @Transactional
    public AccountEntity update(final Integer accountId, final PatchAccountV1RequestDto patchAccountV1RequestDto) {

        final var accountEntity = this.accountAccessService.requireOwner(accountId);
        if (Objects.nonNull(patchAccountV1RequestDto.getName())) {
            accountEntity.setName(patchAccountV1RequestDto.getName().trim());
        }
        return this.accountJpaMapper.save(accountEntity);
    }

    /**
     * Get balance for account.
     *
     * @param accountId the account id
     * @return the balance
     */
    @Transactional(readOnly = true)
    public BigDecimal getBalance(final Integer accountId) {

        this.accountAccessService.requireAccountAccess(accountId);
        return this.expenseJpaMapper.sumNetBalance(accountId);
    }

    /**
     * Get current user role for account.
     *
     * @param accountId the account id
     * @return the role
     */
    @Transactional(readOnly = true)
    public AccountMemberRole getCurrentUserRole(final Integer accountId) {

        return this.accountAccessService.getCurrentUserRole(accountId);
    }

    private void createOwnerMembership(final Integer accountId, final Integer userId) {

        final var accountMemberEntity = new AccountMemberEntity();
        accountMemberEntity.setAccountId(accountId);
        accountMemberEntity.setUserId(userId);
        accountMemberEntity.setRole(AccountMemberRole.OWNER);
        this.accountMemberJpaMapper.save(accountMemberEntity);
    }
}
