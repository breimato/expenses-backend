package com.expenses.recurring.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.expenses.common.MovementType;
import com.expenses.common.RecurringFrequency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/** The Class Recurring Template Entity. */
@Entity
@Table(name = "recurring_templates")
@Getter
@Setter
public class RecurringTemplateEntity {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** The label. */
    @Column(nullable = false, length = 100)
    private String label;

    /** The amount. */
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    /** The category id. */
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    /** The last used at. */
    @Column(name = "last_used_at")
    private LocalDateTime lastUsedAt;

    /** The movement type. */
    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type", nullable = false, length = 10)
    private MovementType movementType = MovementType.EXPENSE;

    /**
     * Analytics average flag: incomes reduce net spending; expenses are excluded from net spending
     * (e.g. savings/investments that leave the bank but are not consumption).
     */
    @Column(name = "offsets_spending_average", nullable = false)
    private boolean offsetsSpendingAverage;

    /** The frequency. */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private RecurringFrequency frequency = RecurringFrequency.MANUAL;

    /** The day of month. */
    @Column(name = "day_of_month")
    private Integer dayOfMonth;

    /** Whether to auto apply. */
    @Column(name = "auto_apply", nullable = false)
    private boolean autoApply;

    /** Whether the template is enabled. */
    @Column(nullable = false)
    private boolean enabled = true;

    /** The created at. */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /** The updated at. */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /** On create. */
    @PrePersist
    void onCreate() {
        final var now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    /** On update. */
    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
