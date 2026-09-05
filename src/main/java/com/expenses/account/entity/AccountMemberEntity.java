package com.expenses.account.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import com.expenses.account.AccountMemberRole;

import lombok.Getter;
import lombok.Setter;

/** The Class Account Member Entity. */
@Entity
@Table(name = "account_members")
@Getter
@Setter
public class AccountMemberEntity {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** The account id. */
    @Column(name = "account_id", nullable = false)
    private Integer accountId;

    /** The user id. */
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    /** The role. */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AccountMemberRole role;

    /** The created at. */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /** On create. */
    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
