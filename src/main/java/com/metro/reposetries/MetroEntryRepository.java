package com.metro.reposetries;

import com.metro.entitiy.MetroEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MetroEntryRepository extends JpaRepository<MetroEntry,Long> {
    Optional<MetroEntry> findByUserIdAndCheckOutTimeIsNull(Long userId);
}
