package com.expenses.recurring.mapper;

import com.expenses.api.dto.PostRecurringTemplateV1RequestDto;
import com.expenses.common.DecimalMapper;
import com.expenses.recurring.entity.RecurringTemplateEntity;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-29T10:58:36+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class PostRecurringTemplateRequestMapperImpl implements PostRecurringTemplateRequestMapper {

    @Autowired
    private DecimalMapper decimalMapper;

    @Override
    public RecurringTemplateEntity toRecurringTemplateEntity(PostRecurringTemplateV1RequestDto postRecurringTemplateV1RequestDto) {
        if ( postRecurringTemplateV1RequestDto == null ) {
            return null;
        }

        RecurringTemplateEntity recurringTemplateEntity = new RecurringTemplateEntity();

        if ( postRecurringTemplateV1RequestDto.getSortOrder() != null ) {
            recurringTemplateEntity.setSortOrder( postRecurringTemplateV1RequestDto.getSortOrder() );
        }
        else {
            recurringTemplateEntity.setSortOrder( postRecurringTemplateV1RequestDto.getSortOrder() != null ? postRecurringTemplateV1RequestDto.getSortOrder() : 0 );
        }
        recurringTemplateEntity.setLabel( postRecurringTemplateV1RequestDto.getLabel() );
        recurringTemplateEntity.setAmount( decimalMapper.toBigDecimal( postRecurringTemplateV1RequestDto.getAmount() ) );
        recurringTemplateEntity.setCategoryId( postRecurringTemplateV1RequestDto.getCategoryId() );

        return recurringTemplateEntity;
    }
}
