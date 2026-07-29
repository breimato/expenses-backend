package com.expenses.common;

import java.math.BigDecimal;
import java.util.Objects;

import org.mapstruct.Mapper;

/** The Interface Decimal Mapper. */
@Mapper(componentModel = "spring")
public interface DecimalMapper {

    /**
     * To string.
     *
     * @param bigDecimal the big decimal
     * @return the string
     */
    default String toString(final BigDecimal bigDecimal) {
        if (Objects.isNull(bigDecimal)) {
            return null;
        }
        return bigDecimal.toPlainString();
    }

    /**
     * To big decimal.
     *
     * @param value the value
     * @return the big decimal
     */
    default BigDecimal toBigDecimal(final String value) {
        if (Objects.isNull(value)) {
            return null;
        }
        return new BigDecimal(value);
    }
}
