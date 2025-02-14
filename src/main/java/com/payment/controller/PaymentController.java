package com.payment.controller;

import com.payment.dto.PaymentRequest;
import com.payment.dto.PaymentResponse;
import com.payment.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @PostMapping("/pay")
    public ResponseEntity<PaymentResponse> payForRide(@RequestBody PaymentRequest request) {
        PaymentResponse response = paymentService.processPayment(request);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/penalty/{userId}")
    public ResponseEntity<String> applyPenalty(@PathVariable UUID userId) {
        paymentService.applyPenalty(userId);
        return ResponseEntity.ok("Penalty applied successfully.");
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<PaymentResponse>> getPaymentHistory(@PathVariable UUID userId) {
        List<PaymentResponse> history = paymentService.getLast5Transactions(userId);
        return ResponseEntity.ok(history);
    }


    @PostMapping("/notify/payment-success")
    public ResponseEntity<String> paymentSuccess(@RequestParam String transactionId) {
        paymentService.notifyPaymentSuccess(transactionId);
        return ResponseEntity.ok("Payment Notification Sent");
    }

    @PostMapping("/notify/penalty")
    public ResponseEntity<String> penalty(@RequestParam String userId) {
        paymentService.notifyPenalty(userId);
        return ResponseEntity.ok("Penalty Notification Sent");
    }


    @PostMapping("/notify/sos")
    public ResponseEntity<String> sosAlert(@RequestParam String stationId) {
        paymentService.notifySOS(stationId);
        return ResponseEntity.ok("SOS Notification Sent");
    }
}
