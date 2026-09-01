package com.expenses.account.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/** The Class Account Entity. */
@Entity
@Table(name = "accounts")
@Getter
@Setter
public class AccountEntity {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** The user id. */
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    /** The name. */
    @Column(nullable = false, length = 100)
    private String name;

    /** Whether this is the default account. */
    @Column(name = "is_default", nullable = false)
    private boolean isDefault;

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
