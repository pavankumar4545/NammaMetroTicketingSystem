package com.metro.reposetries;

import com.metro.entitiy.StationManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationManagerRepository extends JpaRepository<StationManager, Long> {
    StationManager findByEmail(String email);
}