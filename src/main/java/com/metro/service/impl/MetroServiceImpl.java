package com.metro.service.impl;

import com.metro.entitiy.MetroEntry;

import com.metro.reposetries.MetroEntryRepository;
import com.metro.service.MetroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class MetroServiceImpl implements MetroService {

    @Autowired
    private MetroEntryRepository metroEntryRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    private static final String ACTIVE_USERS_KEY = "active_users:";
    private static final double MIN_BALANCE = 20.0;

    private static final String USER_SERVICE_VALIDATE_QR = "http://localhost:8080/api/v1/user/validate-qr";

    @Override
    public MetroEntry checkIn(String userId, String stationName, String qrCodeBase64) {  // ✅ Changed userId to String
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("userId", userId);
        requestBody.put("qrCodeBase64", qrCodeBase64);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Boolean> response = restTemplate.postForEntity(
                USER_SERVICE_VALIDATE_QR, request, Boolean.class);

        if (!Boolean.TRUE.equals(response.getBody())) {
            throw new RuntimeException("Invalid QR Code or Metro Card.");
        }

        MetroEntry entry = new MetroEntry();
        entry.setUserId(userId);
        entry.setStationName(stationName);
        entry.setCheckInTime(LocalDateTime.now());
        entry.setPenaltyApplied(false);

        metroEntryRepository.save(entry);

        redisTemplate.opsForValue().set(ACTIVE_USERS_KEY + userId, stationName);

        return entry;
    }

    @Override
    public MetroEntry checkOut(String userId, String stationName) {  // ✅ Changed userId to String
        Optional<MetroEntry> optionalEntry = metroEntryRepository.findByUserIdAndCheckOutTimeIsNull(userId);
        if (optionalEntry.isEmpty()) {
            throw new RuntimeException("No active check-in found for user: " + userId);
        }

        MetroEntry entry = optionalEntry.get();
        entry.setCheckOutTime(LocalDateTime.now());

        long minutesTraveled = Duration.between(entry.getCheckInTime(), entry.getCheckOutTime()).toMinutes();
        entry.setFare(calculateFare(minutesTraveled));
        entry.setPenaltyApplied(minutesTraveled > 90);

        metroEntryRepository.save(entry);

        redisTemplate.delete(ACTIVE_USERS_KEY + userId);

        return entry;
    }

    private double calculateFare(long minutes) {
        double baseFare = 10.0;
        double extraCharge = (minutes > 30) ? ((minutes - 30) * 0.5) : 0.0;
        return baseFare + extraCharge;
    }
}
