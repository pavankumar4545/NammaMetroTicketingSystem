package com.metro.reposetries;

import com.metro.entitiy.MetroCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetroCardRepository extends JpaRepository<MetroCard, Long> {
    MetroCard findByUserId(Long userId);
}
