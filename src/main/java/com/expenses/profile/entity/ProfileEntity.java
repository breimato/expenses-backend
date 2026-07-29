package com.expenses.profile.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/** The Class Profile Entity. */
@Entity
@Table(name = "profile")
@Getter
@Setter
public class ProfileEntity {

    /** The singleton profile id. */
    public static final int SINGLETON_ID = 1;

    /** The id. */
    @Id
    private Integer id;

    /** The display name. */
    @Column(name = "display_name", nullable = false, length = 100)
    private String displayName;

    /** The balance. */
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

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
