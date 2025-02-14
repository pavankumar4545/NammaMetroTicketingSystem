package com.metro.entitiy;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "metro_entries")
public class MetroEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;  // ✅ Changed to String (UUID)

    private String stationName;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    private Double fare;
    private Boolean penaltyApplied;
    private Double penaltyAmount = 0.0;

    public MetroEntry() {}

    public MetroEntry(Long id, String userId, String stationName, LocalDateTime checkInTime, LocalDateTime checkOutTime, Double fare, Boolean penaltyApplied, Double penaltyAmount) {
        this.id = id;
        this.userId = userId;
        this.stationName = stationName;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.fare = fare;
        this.penaltyApplied = penaltyApplied;
        this.penaltyAmount = penaltyAmount;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getStationName() { return stationName; }
    public void setStationName(String stationName) { this.stationName = stationName; }

    public LocalDateTime getCheckInTime() { return checkInTime; }
    public void setCheckInTime(LocalDateTime checkInTime) { this.checkInTime = checkInTime; }

    public LocalDateTime getCheckOutTime() { return checkOutTime; }
    public void setCheckOutTime(LocalDateTime checkOutTime) { this.checkOutTime = checkOutTime; }

    public Double getFare() { return fare; }
    public void setFare(Double fare) { this.fare = fare; }

    public Boolean getPenaltyApplied() { return penaltyApplied; }
    public void setPenaltyApplied(Boolean penaltyApplied) { this.penaltyApplied = penaltyApplied; }

    public Double getPenaltyAmount() { return penaltyAmount; }
    public void setPenaltyAmount(Double penaltyAmount) { this.penaltyAmount = penaltyAmount; }
}
