package com.payment.service;

import com.payment.dto.PaymentRequest;
import com.payment.dto.PaymentResponse;
import java.util.List;
import java.util.UUID;

public interface PaymentService {

    PaymentResponse processPayment(PaymentRequest request);

    void applyPenalty(UUID userId);

    List<PaymentResponse> getLast5Transactions(UUID userId);

    void notifyPaymentSuccess(String transactionId);

    void notifyPenalty(String userId);

    void notifySOS(String stationId);
}
