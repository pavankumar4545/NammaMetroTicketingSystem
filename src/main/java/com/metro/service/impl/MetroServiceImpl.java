package com.metro.service.impl;

import com.metro.entitiy.MetroCard;
import com.metro.entitiy.MetroEntry;
import com.metro.reposetries.MetroCardRepository;
import com.metro.reposetries.MetroEntryRepository;
import com.metro.service.MetroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MetroServiceImpl implements MetroService {

    @Autowired
    private MetroEntryRepository metroEntryRepository;

    @Autowired
    private MetroCardRepository metroCardRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String ACTIVE_USERS_KEY = "active_users:";
    private static final double MIN_BALANCE = 20.0;

    @Override
    public MetroEntry checkIn(Long userId, String stationName) {
        MetroCard metroCard = metroCardRepository.findByUserId(userId);
        if (metroCard == null || metroCard.getBalance() < MIN_BALANCE) {
            throw new RuntimeException("Invalid Metro Card or insufficient balance.");
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
    public MetroEntry checkOut(Long userId, String stationName) {
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
