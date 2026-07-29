package com.expenses.common;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.openapitools.jackson.nullable.JsonNullable;

/** The Interface Json Nullable Mapper. */
@Mapper(componentModel = "spring")
public interface JsonNullableMapper {

    /**
     * Map string.
     *
     * @param value the value
     * @return the json nullable
     */
    @Named("mapString")
    default JsonNullable<String> mapString(final String value) {
        if (Objects.isNull(value)) {
            return JsonNullable.undefined();
        }
        return JsonNullable.of(value);
    }

    /**
     * Unmap string.
     *
     * @param jsonNullable the json nullable
     * @return the string
     */
    @Named("unmapString")
    default String unmapString(final JsonNullable<String> jsonNullable) {
        if (Objects.isNull(jsonNullable) || !jsonNullable.isPresent()) {
            return null;
        }
        return jsonNullable.get();
    }

    /**
     * Map offset date time.
     *
     * @param localDateTime the local date time
     * @return the json nullable
     */
    @Named("mapOffsetDateTime")
    default JsonNullable<OffsetDateTime> mapOffsetDateTime(final LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return JsonNullable.undefined();
        }
        return JsonNullable.of(localDateTime.atOffset(ZoneOffset.UTC));
    }

    /**
     * Map integer.
     *
     * @param value the value
     * @return the json nullable
     */
    @Named("mapInteger")
    default JsonNullable<Integer> mapInteger(final Integer value) {
        if (Objects.isNull(value)) {
            return JsonNullable.undefined();
        }
        return JsonNullable.of(value);
    }
}
