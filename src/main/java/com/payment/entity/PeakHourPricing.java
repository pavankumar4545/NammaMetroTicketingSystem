package com.payment.entity;
import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "peak_hour_pricing")
public class PeakHourPricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private double fareMultiplier; // Example: 1.2 for 20% increase

    public PeakHourPricing() {}

    public PeakHourPricing(LocalTime startTime, LocalTime endTime, double fareMultiplier) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.fareMultiplier = fareMultiplier;
    }

    public Long getId() {
        return id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public double getFareMultiplier() {
        return fareMultiplier;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setFareMultiplier(double fareMultiplier) {
        this.fareMultiplier = fareMultiplier;
    }
}

