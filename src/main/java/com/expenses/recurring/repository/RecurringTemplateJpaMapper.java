package com.expenses.recurring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.expenses.common.RecurringFrequency;
import com.expenses.recurring.entity.RecurringTemplateEntity;

/** The Interface Recurring Template Jpa Mapper. */
public interface RecurringTemplateJpaMapper extends JpaRepository<RecurringTemplateEntity, Integer> {

    /**
     * Find by criteria.
     *
     * @param categoryId the category id
     * @return the list
     */
    @Query("""
            SELECT recurringTemplateEntity FROM RecurringTemplateEntity recurringTemplateEntity
            WHERE (:categoryId IS NULL OR recurringTemplateEntity.categoryId = :categoryId)
            ORDER BY recurringTemplateEntity.id ASC
            """)
    List<RecurringTemplateEntity> findByCriteria(@Param("categoryId") Integer categoryId);

    /**
     * Find auto-apply monthly templates due on or before the given day of month.
     *
     * @param dayOfMonth the day of month
     * @return the list
     */
    @Query("""
            SELECT recurringTemplateEntity FROM RecurringTemplateEntity recurringTemplateEntity
            WHERE recurringTemplateEntity.enabled = true
              AND recurringTemplateEntity.autoApply = true
              AND recurringTemplateEntity.frequency = :frequency
              AND recurringTemplateEntity.dayOfMonth IS NOT NULL
              AND recurringTemplateEntity.dayOfMonth <= :dayOfMonth
            ORDER BY recurringTemplateEntity.id ASC
            """)
    List<RecurringTemplateEntity> findDueAutoApplyTemplates(
            @Param("frequency") RecurringFrequency frequency,
            @Param("dayOfMonth") int dayOfMonth);

    /**
     * Check if any template exists for category.
     *
     * @param categoryId the category id
     * @return true if exists
     */
    boolean existsByCategoryId(Integer categoryId);
}
