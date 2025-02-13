package com.metro.service.impl;

import com.metro.entitiy.Route;
import com.metro.reposetries.RouteRepository;
import com.metro.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {
//
//    private final RouteRepository routeRepository;
//
//    public RouteServiceImpl(RouteRepository routeRepository) {
//        this.routeRepository = routeRepository;
//    }
    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Route addRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteById(Long id) {
        Optional<Route> route = routeRepository.findById(id);
        return route.orElseThrow(() -> new RuntimeException("Route not found"));
    }

    @Override
    public List<Route> getActiveRoutes() {
        return routeRepository.findByStatus("ACTIVE");
    }

    @Override
    public Route updateRoute(Long id, Route updatedRoute) {
        Route existingRoute = getRouteById(id);
        existingRoute.setRouteName(updatedRoute.getRouteName());
        existingRoute.setStartStationId(updatedRoute.getStartStationId());
        existingRoute.setEndStationId(updatedRoute.getEndStationId());
        existingRoute.setDistance(updatedRoute.getDistance());
        existingRoute.setEstimatedTime(updatedRoute.getEstimatedTime());
        existingRoute.setStatus(updatedRoute.getStatus());

        return routeRepository.save(existingRoute);
    }

    @Override
    public void deleteRoute(Long id) {
        if (!routeRepository.existsById(id)) {
            throw new RuntimeException("Route not found");
        }
        routeRepository.deleteById(id);
    }
}