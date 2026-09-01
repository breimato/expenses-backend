package com.expenses.account.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
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

/** The Class Account Transfer Entity. */
@Entity
@Table(name = "account_transfers")
@Getter
@Setter
public class AccountTransferEntity {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** The user id. */
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    /** The from account id. */
    @Column(name = "from_account_id", nullable = false)
    private Integer fromAccountId;

    /** The to account id. */
    @Column(name = "to_account_id", nullable = false)
    private Integer toAccountId;

    /** The amount. */
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    /** The transfer date. */
    @Column(name = "transfer_date", nullable = false)
    private LocalDate transferDate;

    /** The description. */
    @Column(nullable = false, length = 500)
    private String description;

    /** The created at. */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /** On create. */
    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
