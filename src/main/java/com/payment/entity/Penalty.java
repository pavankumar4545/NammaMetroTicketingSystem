package com.payment.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "penalties")
public class Penalty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private double penaltyAmount;

    @Column(nullable = false)
    private LocalDateTime appliedAt;

    public Penalty() {}

    public Penalty(UUID userId, double penaltyAmount, LocalDateTime appliedAt) {
        this.userId = userId;
        this.penaltyAmount = penaltyAmount;
        this.appliedAt = appliedAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public double getPenaltyAmount() {
        return penaltyAmount;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setPenaltyAmount(double penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }
}

