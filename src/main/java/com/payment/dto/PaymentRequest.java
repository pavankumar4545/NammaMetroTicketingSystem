package com.payment.dto;

import java.util.UUID;


public class PaymentRequest {
    private UUID userId;
    private double amount;
    private boolean metroCardPayment;

    public PaymentRequest(UUID userId, double amount, boolean metroCardPayment) {
        this.userId = userId;
        this.amount = amount;
        this.metroCardPayment = metroCardPayment;
    }

    // Getters & Setters
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

    public boolean getMetroCardPayment() {  // Fixed method name
        return metroCardPayment;
    }

    public void setMetroCardPayment(boolean metroCardPayment) {
        this.metroCardPayment = metroCardPayment;
    }
}