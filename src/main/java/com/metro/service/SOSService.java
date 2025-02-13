package com.metro.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SOSService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public SOSService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void triggerSOS(Long userId, String stationName) {
        String message = "SOS Alert! User " + userId + " at station " + stationName;
        kafkaTemplate.send("metro_sos_topic", message);
    }
}