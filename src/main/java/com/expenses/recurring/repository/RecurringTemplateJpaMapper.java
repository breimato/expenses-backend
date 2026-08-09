package com.expenses.recurring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.expenses.common.RecurringFrequency;
import com.expenses.recurring.entity.RecurringTemplateEntity;

/** The Interface Recurring Template Jpa Mapper. */
public interface RecurringTemplateJpaMapper extends JpaRepository<RecurringTemplateEntity, Integer> {

    /**
     * Find by id and user id.
     *
     * @param id the id
     * @param userId the user id
     * @return the optional template
     */
    Optional<RecurringTemplateEntity> findByIdAndUserId(Integer id, Integer userId);

    /**
     * Find by criteria for user.
     *
     * @param userId the user id
     * @param categoryId the category id
     * @return the list
     */
    @Query("""
            SELECT recurringTemplateEntity FROM RecurringTemplateEntity recurringTemplateEntity
            WHERE recurringTemplateEntity.userId = :userId
              AND (:categoryId IS NULL OR recurringTemplateEntity.categoryId = :categoryId)
            ORDER BY recurringTemplateEntity.id ASC
            """)
    List<RecurringTemplateEntity> findByCriteria(
            @Param("userId") Integer userId,
            @Param("categoryId") Integer categoryId);

    /**
     * Find auto-apply monthly templates due on or before the given day of month.
     *
     * @param frequency the frequency
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
     * Find auto-apply monthly templates due for a specific user.
     *
     * @param userId the user id
     * @param frequency the frequency
     * @param dayOfMonth the day of month
     * @return the list
     */
    @Query("""
            SELECT recurringTemplateEntity FROM RecurringTemplateEntity recurringTemplateEntity
            WHERE recurringTemplateEntity.userId = :userId
              AND recurringTemplateEntity.enabled = true
              AND recurringTemplateEntity.autoApply = true
              AND recurringTemplateEntity.frequency = :frequency
              AND recurringTemplateEntity.dayOfMonth IS NOT NULL
              AND recurringTemplateEntity.dayOfMonth <= :dayOfMonth
            ORDER BY recurringTemplateEntity.id ASC
            """)
    List<RecurringTemplateEntity> findDueAutoApplyTemplatesForUser(
            @Param("userId") Integer userId,
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
