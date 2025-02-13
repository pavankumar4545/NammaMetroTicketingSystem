package com.metro.controller;

import com.metro.entitiy.StationManager;
import com.metro.service.StationManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metro/managers")
public class StationManagerController {

    private final StationManagerService stationManagerService;

    public StationManagerController(StationManagerService stationManagerService) {
        this.stationManagerService = stationManagerService;
    }

    @PostMapping
    public ResponseEntity<StationManager> addManager(@RequestBody StationManager manager) {
        return ResponseEntity.ok(stationManagerService.addManager(manager));
    }

    @GetMapping
    public ResponseEntity<List<StationManager>> getAllManagers() {
        return ResponseEntity.ok(stationManagerService.getAllManagers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationManager> getManagerById(@PathVariable Long id) {
        return ResponseEntity.ok(stationManagerService.getManagerById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteManager(@PathVariable Long id) {
        stationManagerService.deleteManager(id);
        return ResponseEntity.ok("Manager deleted successfully.");
    }
}
