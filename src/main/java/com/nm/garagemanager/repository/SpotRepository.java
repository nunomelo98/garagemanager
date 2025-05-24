package com.nm.garagemanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nm.garagemanager.entity.ParkingSpot;

@Repository
public interface SpotRepository extends JpaRepository<ParkingSpot, Long> {
    Optional<ParkingSpot> findFirstByOccupiedFalse();
    Optional<ParkingSpot> findByLatAndLng(double lat, double lng);



}
