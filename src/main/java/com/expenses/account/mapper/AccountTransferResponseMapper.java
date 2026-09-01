package com.expenses.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.expenses.api.dto.AccountTransferV1Dto;
import com.expenses.api.dto.AccountTransferV1ResponseDto;
import com.expenses.account.entity.AccountTransferEntity;
import com.expenses.common.DecimalMapper;

/** The Interface Account Transfer Response Mapper. */
@Mapper(componentModel = "spring", uses = DecimalMapper.class)
public interface AccountTransferResponseMapper {

    /**
     * To account transfer v1 dto.
     *
     * @param accountTransferEntity the account transfer entity
     * @return the account transfer v1 dto
     */
    @Mapping(target = "amount", source = "amount")
    AccountTransferV1Dto toAccountTransferV1Dto(AccountTransferEntity accountTransferEntity);

    /**
     * To account transfer v1 response.
     *
     * @param accountTransferEntity the account transfer entity
     * @return the account transfer v1 response dto
     */
    default AccountTransferV1ResponseDto toAccountTransferV1Response(final AccountTransferEntity accountTransferEntity) {

        return AccountTransferV1ResponseDto.builder()
                .transfer(this.toAccountTransferV1Dto(accountTransferEntity))
                .build();
    }
}
