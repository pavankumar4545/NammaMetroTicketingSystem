package com.metro.service;

import com.metro.entitiy.MetroEntry;

public interface MetroService {
    MetroEntry checkIn(String userId, String stationName, String qrCodeBase64);  // ✅ Changed Long to String
    MetroEntry checkOut(String userId, String stationName);  // ✅ Changed Long to String
}
