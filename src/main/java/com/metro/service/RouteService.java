package com.metro.service;

import com.metro.entitiy.Route;

import java.util.List;

public interface RouteService {
    Route addRoute(Route route);
    List<Route> getAllRoutes();
    Route getRouteById(Long id);
    List<Route> getActiveRoutes();
    Route updateRoute(Long id, Route route);
    void deleteRoute(Long id);
}