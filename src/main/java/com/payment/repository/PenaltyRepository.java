package com.payment.repository;
import com.payment.entity.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PenaltyRepository extends JpaRepository<Penalty, UUID> {
}
