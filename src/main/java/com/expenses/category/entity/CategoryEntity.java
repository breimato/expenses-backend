package com.expenses.category.entity;

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

/** The Class Category Entity. */
@Entity
@Table(name = "categories")
@Getter
@Setter
public class CategoryEntity {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** The name. */
    @Column(nullable = false, length = 100)
    private String name;

    /** The color. */
    @Column(nullable = false, length = 20)
    private String color;

    /** The icon. */
    @Column(length = 20)
    private String icon;

    /** The sort order. */
    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    /** The movement type. */
    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type", nullable = false, length = 10)
    private MovementType movementType = MovementType.EXPENSE;

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
