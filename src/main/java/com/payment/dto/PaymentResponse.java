package com.payment.dto;
import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentResponse {

    private UUID transactionId;
    private UUID userId;
    private double amount;
    private boolean metroCardPayment;
    private LocalDateTime timestamp;

    public PaymentResponse(UUID transactionId, UUID userId, double amount, boolean metroCardPayment, LocalDateTime timestamp) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.amount = amount;
        this.metroCardPayment = metroCardPayment;
        this.timestamp = timestamp;
    }

    // Getters & Setters
    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean getMetroCardPayment() { // Fixed method name
        return metroCardPayment;
    }

    public void setMetroCardPayment(boolean metroCardPayment) {
        this.metroCardPayment = metroCardPayment;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}