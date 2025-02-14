package com.payment.repository;


import com.payment.entity.PeakHourPricing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeakHourPricingRepository extends JpaRepository<PeakHourPricing, Long> {
}

