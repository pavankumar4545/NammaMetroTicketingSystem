package com.metro.entitiy;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "metro_card")
public class MetroCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long userId;

    @Column(nullable = false)
    private double balance;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    public MetroCard() {
    }

    public MetroCard(Long id, double balance, Long userId, LocalDateTime expiryDate) {
        this.id = id;
        this.balance = balance;
        this.userId = userId;
        this.expiryDate = expiryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}
