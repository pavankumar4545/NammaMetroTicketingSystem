package com.metro.controller;

import com.metro.entitiy.MetroEntry;
import com.metro.service.MetroService;
import com.metro.service.SOSAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/metro")
public class MetroController {

    @Autowired
    private MetroService metroService;

    @Autowired
    private SOSAlertService sosAlertService;

    @PostMapping("/checkin")
    public ResponseEntity<?> checkIn(@RequestParam Long userId, @RequestParam String stationName) {
        try {
            MetroEntry entry = metroService.checkIn(userId, stationName);
            return ResponseEntity.ok(entry);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkOut(@RequestParam Long userId, @RequestParam String stationName) {
        try {
            MetroEntry entry = metroService.checkOut(userId, stationName);
            return ResponseEntity.ok(entry);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/sos")
    public ResponseEntity<String> sendSOS(@RequestParam Long userId, @RequestParam String stationName) {
        sosAlertService.sendSOSAlert(userId, stationName);
        return ResponseEntity.ok("🚨 SOS Alert Sent Successfully!");
    }
}
