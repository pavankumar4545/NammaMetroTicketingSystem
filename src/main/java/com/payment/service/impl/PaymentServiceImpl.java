package com.payment.service.impl;

import com.payment.dto.PaymentRequest;
import com.payment.dto.PaymentResponse;
import com.payment.entity.PaymentTransaction;
import com.payment.entity.Penalty;
import com.payment.repository.PaymentTransactionRepository;
import com.payment.repository.PenaltyRepository;
import com.payment.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentTransactionRepository paymentTransactionRepository;
    private final PenaltyRepository penaltyRepository;
    private final RestTemplate restTemplate;

    @Value("${notification.service.url}")
    private String notificationServiceUrl;

    @Value("${user.service.url}")
    private String userServiceUrl;

    public PaymentServiceImpl(PaymentTransactionRepository paymentTransactionRepository,
                              PenaltyRepository penaltyRepository,
                              RestTemplate restTemplate) {
        this.paymentTransactionRepository = paymentTransactionRepository;
        this.penaltyRepository = penaltyRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    @Transactional
    public PaymentResponse processPayment(PaymentRequest request) {
        // ✅ Step 1: Check if user exists in UserService
        ResponseEntity<Boolean> response = restTemplate.getForEntity(
                userServiceUrl + "/exists/" + request.getUserId(), Boolean.class);

        if (response.getBody() == null || !response.getBody()) {
            throw new IllegalArgumentException("User ID not found in User Service");
        }

        // ✅ Step 2: Proceed with payment processing
        PaymentTransaction transaction = new PaymentTransaction();
        transaction.setUserId(request.getUserId());
        transaction.setAmount(request.getAmount());
        transaction.setPaymentMethod(request.getMetroCardPayment() ? "MetroCard" : "QR Ticket");
        transaction.setTimestamp(LocalDateTime.now());

        paymentTransactionRepository.save(transaction);

        // ✅ Step 3: Send payment success notification
        notifyPaymentSuccess(transaction.getId().toString());

        return new PaymentResponse(
                transaction.getId(),
                transaction.getUserId(),
                transaction.getAmount(),
                transaction.getPaymentMethod().equals("MetroCard"),
                transaction.getTimestamp()
        );
    }

    @Override
    @Transactional
    public void applyPenalty(UUID userId) {
        Penalty penalty = new Penalty();
        penalty.setUserId(userId);
        penalty.setPenaltyAmount(50.0);
        penalty.setAppliedAt(LocalDateTime.now());

        penaltyRepository.save(penalty);

        // ✅ Notify Notification Service
        notifyPenalty(userId.toString());
    }

    @Override
    public List<PaymentResponse> getLast5Transactions(UUID userId) {
        List<PaymentTransaction> transactions = paymentTransactionRepository.findTop5ByUserIdOrderByTimestampDesc(userId);

        return transactions.stream().map(transaction -> new PaymentResponse(
                transaction.getId(),
                transaction.getUserId(),
                transaction.getAmount(),
                transaction.getPaymentMethod().equals("MetroCard"),
                transaction.getTimestamp()
        )).collect(Collectors.toList());
    }

    @Cacheable(value = "peakHourPricing", key = "#hour")
    public double getPeakHourPrice(int hour) {
        if ((hour >= 7 && hour <= 10) || (hour >= 17 && hour <= 20)) {
            return 1.2;
        }
        return 1.0;
    }

    /**
     * Notify Notification Service about successful payment
     */
    @Override
    public void notifyPaymentSuccess(String transactionId) {
        String message = "Payment successful for transaction ID: " + transactionId;
        restTemplate.postForObject(notificationServiceUrl + "/payment-success", message, String.class);
    }

    /**
     * Notify Notification Service about penalty charge
     */
    @Override
    public void notifyPenalty(String userId) {
        String message = "Penalty charged for user ID: " + userId;
        restTemplate.postForObject(notificationServiceUrl + "/penalty", message, String.class);
    }

    /**
     * Notify Notification Service about SOS alert
     */
    @Override
    public void notifySOS(String stationId) {
        String message = "SOS alert triggered at station ID: " + stationId;
        restTemplate.postForObject(notificationServiceUrl + "/sos", message, String.class);
    }
}
