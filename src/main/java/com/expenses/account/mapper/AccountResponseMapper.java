package com.expenses.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.expenses.api.dto.AccountMemberRoleV1Dto;
import com.expenses.api.dto.AccountV1Dto;
import com.expenses.api.dto.AccountV1ResponseDto;
import com.expenses.api.dto.GetAccountsV1ResponseDto;
import com.expenses.account.entity.AccountEntity;
import com.expenses.common.DecimalMapper;

import java.util.List;
import java.util.Map;

/** The Interface Account Response Mapper. */
@Mapper(componentModel = "spring", uses = DecimalMapper.class)
public interface AccountResponseMapper {

    /**
     * To account v1 dto.
     *
     * @param accountEntity the account entity
     * @param balance the balance
     * @param role the role
     * @return the account v1 dto
     */
    @Mapping(target = "id", source = "accountEntity.id")
    @Mapping(target = "name", source = "accountEntity.name")
    @Mapping(target = "isDefault", expression = "java(role == com.expenses.api.dto.AccountMemberRoleV1Dto.OWNER && accountEntity.isDefault())")
    @Mapping(target = "balance", source = "balance")
    @Mapping(target = "role", source = "role")
    AccountV1Dto toAccountV1Dto(AccountEntity accountEntity, String balance, AccountMemberRoleV1Dto role);

    /**
     * To account v1 response.
     *
     * @param accountEntity the account entity
     * @param balance the balance
     * @param role the role
     * @return the account v1 response dto
     */
    default AccountV1ResponseDto toAccountV1Response(
            final AccountEntity accountEntity,
            final String balance,
            final AccountMemberRoleV1Dto role) {

        return AccountV1ResponseDto.builder()
                .account(this.toAccountV1Dto(accountEntity, balance, role))
                .build();
    }

    /**
     * To get accounts v1 response.
     *
     * @param accountEntityList the account entity list
     * @param balanceByAccountId balances keyed by account id as string
     * @param roleByAccountId roles keyed by account id
     * @return the get accounts v1 response dto
     */
    default GetAccountsV1ResponseDto toGetAccountsV1Response(
            final List<AccountEntity> accountEntityList,
            final Map<Integer, String> balanceByAccountId,
            final Map<Integer, AccountMemberRoleV1Dto> roleByAccountId) {

        final var accountV1DtoList = accountEntityList.stream()
                .map(accountEntity -> this.toAccountV1Dto(
                        accountEntity,
                        balanceByAccountId.get(accountEntity.getId()),
                        roleByAccountId.get(accountEntity.getId())))
                .toList();
        return GetAccountsV1ResponseDto.builder()
                .accounts(accountV1DtoList)
                .build();
    }
}
