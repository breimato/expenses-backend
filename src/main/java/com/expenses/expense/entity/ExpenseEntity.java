package com.expenses.expense.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.expenses.common.MovementType;

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

/** The Class Expense Entity. */
@Entity
@Table(name = "expenses")
@Getter
@Setter
public class ExpenseEntity {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** The category id. */
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    /** The amount. */
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    /** The description. */
    @Column(nullable = false, length = 500)
    private String description;

    /** The expense date. */
    @Column(name = "expense_date", nullable = false)
    private LocalDate expenseDate;

    /** The movement type. */
    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type", nullable = false, length = 10)
    private MovementType movementType = MovementType.EXPENSE;

    /** Whether income offsets spending in analytics averages. */
    @Column(name = "offsets_spending_average", nullable = false)
    private boolean offsetsSpendingAverage;

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
