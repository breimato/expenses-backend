package com.expenses.auth.entity;

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

/** The Class User Entity. */
@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** The email. */
    @Column(nullable = false, unique = true, length = 255)
    private String email;

    /** The password hash. */
    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    /** The display name. */
    @Column(name = "display_name", nullable = false, length = 100)
    private String displayName;

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
