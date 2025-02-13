package com.metro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SOSAlertService {

    private static final String TOPIC = "sos_alerts";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendSOSAlert(Long userId, String stationName) {
        String message = "🚨 SOS Alert from User " + userId + " at " + stationName;
        kafkaTemplate.send(TOPIC, message);
    }
}
