package com.metro.entitiy;

import jakarta.persistence.*;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String routeName;

    @Column(nullable = false)
    private Long startStationId;

    @Column(nullable = false)
    private Long endStationId;

    @Column(nullable = false)
    private Double distance;

    @Column(nullable = false)
    private Integer estimatedTime;

    @Column(nullable = false)
    private String status;

    public Route() {}

    public Route(Long id, String routeName, Long startStationId, Long endStationId, Double distance, Integer estimatedTime, String status) {
        this.id = id;
        this.routeName = routeName;
        this.startStationId = startStationId;
        this.endStationId = endStationId;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRouteName() { return routeName; }
    public void setRouteName(String routeName) { this.routeName = routeName; }

    public Long getStartStationId() { return startStationId; }
    public void setStartStationId(Long startStationId) { this.startStationId = startStationId; }

    public Long getEndStationId() { return endStationId; }
    public void setEndStationId(Long endStationId) { this.endStationId = endStationId; }

    public Double getDistance() { return distance; }
    public void setDistance(Double distance) { this.distance = distance; }

    public Integer getEstimatedTime() { return estimatedTime; }
    public void setEstimatedTime(Integer estimatedTime) { this.estimatedTime = estimatedTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}