package com.metro.service;


import com.metro.entitiy.MetroEntry;

public interface MetroService {
    MetroEntry checkIn(Long userId, String stationName);
    MetroEntry checkOut(Long userId, String stationName);
}

