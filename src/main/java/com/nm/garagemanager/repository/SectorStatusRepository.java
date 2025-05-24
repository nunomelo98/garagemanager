package com.nm.garagemanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nm.garagemanager.entity.SectorStatus;

@Repository
public interface SectorStatusRepository extends JpaRepository<SectorStatus, Long> {
    Optional<SectorStatus> findBySector(String sector);
}
