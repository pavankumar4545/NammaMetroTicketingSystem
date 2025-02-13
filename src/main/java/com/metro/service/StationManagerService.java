package com.metro.service;

import com.metro.entitiy.StationManager;

import java.util.List;

public interface StationManagerService {
    StationManager addManager(StationManager manager);
    List<StationManager> getAllManagers();
    StationManager getManagerById(Long id);
    void deleteManager(Long id);
}
