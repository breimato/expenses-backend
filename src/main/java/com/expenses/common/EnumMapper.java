package com.expenses.common;

import org.mapstruct.Mapper;

import com.expenses.api.dto.MovementTypeV1;
import com.expenses.api.dto.RecurringFrequencyV1;

/** The Interface Enum Mapper. */
@Mapper(componentModel = "spring")
public interface EnumMapper {

    /**
     * To movement type.
     *
     * @param movementTypeV1 the movement type v1
     * @return the movement type
     */
    default MovementType toMovementType(final MovementTypeV1 movementTypeV1) {

        if (movementTypeV1 == null) {
            return MovementType.EXPENSE;
        }
        return MovementType.valueOf(movementTypeV1.name());
    }

    /**
     * To movement type for optional filters.
     *
     * @param movementTypeV1 the movement type v1
     * @return the movement type or null
     */
    default MovementType toMovementTypeOrNull(final MovementTypeV1 movementTypeV1) {

        if (movementTypeV1 == null) {
            return null;
        }
        return MovementType.valueOf(movementTypeV1.name());
    }

    /**
     * To movement type v1.
     *
     * @param movementType the movement type
     * @return the movement type v1
     */
    default MovementTypeV1 toMovementTypeV1(final MovementType movementType) {

        if (movementType == null) {
            return MovementTypeV1.EXPENSE;
        }
        return MovementTypeV1.valueOf(movementType.name());
    }

    /**
     * To recurring frequency.
     *
     * @param recurringFrequencyV1 the recurring frequency v1
     * @return the recurring frequency
     */
    default RecurringFrequency toRecurringFrequency(final RecurringFrequencyV1 recurringFrequencyV1) {

        if (recurringFrequencyV1 == null) {
            return RecurringFrequency.MANUAL;
        }
        return RecurringFrequency.valueOf(recurringFrequencyV1.name());
    }

    /**
     * To recurring frequency v1.
     *
     * @param recurringFrequency the recurring frequency
     * @return the recurring frequency v1
     */
    default RecurringFrequencyV1 toRecurringFrequencyV1(final RecurringFrequency recurringFrequency) {

        if (recurringFrequency == null) {
            return RecurringFrequencyV1.MANUAL;
        }
        return RecurringFrequencyV1.valueOf(recurringFrequency.name());
    }
}
