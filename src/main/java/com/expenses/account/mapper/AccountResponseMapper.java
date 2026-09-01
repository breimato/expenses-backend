package com.expenses.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.expenses.api.dto.AccountV1Dto;
import com.expenses.api.dto.AccountV1ResponseDto;
import com.expenses.api.dto.GetAccountsV1ResponseDto;
import com.expenses.account.entity.AccountEntity;
import com.expenses.common.DecimalMapper;

import java.util.List;

/** The Interface Account Response Mapper. */
@Mapper(componentModel = "spring", uses = DecimalMapper.class)
public interface AccountResponseMapper {

    /**
     * To account v1 dto.
     *
     * @param accountEntity the account entity
     * @param balance the balance
     * @return the account v1 dto
     */
    @Mapping(target = "id", source = "accountEntity.id")
    @Mapping(target = "name", source = "accountEntity.name")
    @Mapping(target = "isDefault", source = "accountEntity.default")
    @Mapping(target = "balance", source = "balance")
    AccountV1Dto toAccountV1Dto(AccountEntity accountEntity, String balance);

    /**
     * To account v1 response.
     *
     * @param accountEntity the account entity
     * @param balance the balance
     * @return the account v1 response dto
     */
    default AccountV1ResponseDto toAccountV1Response(final AccountEntity accountEntity, final String balance) {

        return AccountV1ResponseDto.builder()
                .account(this.toAccountV1Dto(accountEntity, balance))
                .build();
    }

    /**
     * To get accounts v1 response.
     *
     * @param accountEntityList the account entity list
     * @param balanceByAccountId balances keyed by account id as string
     * @return the get accounts v1 response dto
     */
    default GetAccountsV1ResponseDto toGetAccountsV1Response(
            final List<AccountEntity> accountEntityList,
            final java.util.Map<Integer, String> balanceByAccountId) {

        final var accountV1DtoList = accountEntityList.stream()
                .map(accountEntity -> this.toAccountV1Dto(
                        accountEntity,
                        balanceByAccountId.get(accountEntity.getId())))
                .toList();
        return GetAccountsV1ResponseDto.builder()
                .accounts(accountV1DtoList)
                .build();
    }
}
