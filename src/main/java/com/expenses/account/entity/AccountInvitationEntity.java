package com.expenses.account.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/** The Class Account Invitation Entity. */
@Entity
@Table(name = "account_invitations")
@Getter
@Setter
public class AccountInvitationEntity {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** The account id. */
    @Column(name = "account_id", nullable = false)
    private Integer accountId;

    /** The token. */
    @Column(nullable = false, length = 36)
    private String token;

    /** The created by user id. */
    @Column(name = "created_by_user_id", nullable = false)
    private Integer createdByUserId;

    /** The expires at. */
    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    /** The accepted at. */
    @Column(name = "accepted_at")
    private LocalDateTime acceptedAt;

    /** The accepted by user id. */
    @Column(name = "accepted_by_user_id")
    private Integer acceptedByUserId;

    /** The revoked at. */
    @Column(name = "revoked_at")
    private LocalDateTime revokedAt;

    /** The created at. */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /** On create. */
    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
