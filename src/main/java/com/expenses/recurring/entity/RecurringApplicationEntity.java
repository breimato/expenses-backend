package com.expenses.recurring.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/** The Class Recurring Application Entity. */
@Entity
@Table(name = "recurring_applications")
@Getter
@Setter
public class RecurringApplicationEntity {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** The template id. */
    @Column(name = "template_id", nullable = false)
    private Integer templateId;

    /** The period key (YYYY-MM). */
    @Column(name = "period_key", nullable = false, length = 7)
    private String periodKey;

    /** The movement id. */
    @Column(name = "movement_id", nullable = false)
    private Integer movementId;

    /** The applied at. */
    @Column(name = "applied_at", nullable = false)
    private LocalDateTime appliedAt;
}
