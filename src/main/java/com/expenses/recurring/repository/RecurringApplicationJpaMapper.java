package com.expenses.recurring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expenses.recurring.entity.RecurringApplicationEntity;

/** The Interface Recurring Application Jpa Mapper. */
public interface RecurringApplicationJpaMapper extends JpaRepository<RecurringApplicationEntity, Integer> {

    /**
     * Find by template id and period key.
     *
     * @param templateId the template id
     * @param periodKey the period key
     * @return the optional recurring application entity
     */
    Optional<RecurringApplicationEntity> findByTemplateIdAndPeriodKey(Integer templateId, String periodKey);

    /**
     * Delete all applications linked to a movement.
     *
     * @param movementId the movement id
     */
    void deleteByMovementId(Integer movementId);
}
