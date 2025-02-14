package com.payment.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment_transactions")
public class PaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String paymentMethod; // "Metro Card" or "QR Ticket"

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public PaymentTransaction() {}

    public PaymentTransaction(UUID userId, double amount, String paymentMethod, LocalDateTime timestamp) {
        this.userId = userId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
