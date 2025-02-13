package com.metro.service.impl;

import com.metro.entitiy.StationManager;
import com.metro.reposetries.StationManagerRepository;
import com.metro.service.StationManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationManagerServiceImpl implements StationManagerService {
//
//    private final StationManagerRepository stationManagerRepository;
//
//    public StationManagerServiceImpl(StationManagerRepository stationManagerRepository) {
//        this.stationManagerRepository = stationManagerRepository;
//    }
    @Autowired
    private StationManagerRepository stationManagerRepository;

    @Override
    public StationManager addManager(StationManager manager) {
        return stationManagerRepository.save(manager);
    }

    @Override
    public List<StationManager> getAllManagers() {
        return stationManagerRepository.findAll();
    }

    @Override
    public StationManager getManagerById(Long id) {
        Optional<StationManager> manager = stationManagerRepository.findById(id);
        return manager.orElseThrow(() -> new RuntimeException("Manager not found"));
    }

    @Override
    public void deleteManager(Long id) {
        if (!stationManagerRepository.existsById(id)) {
            throw new RuntimeException("Manager not found");
        }
        stationManagerRepository.deleteById(id);
    }
}
