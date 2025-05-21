package com.nm.garagemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nm.garagemanager.entity.ParkingSpot;

public interface SpotRepository extends JpaRepository<ParkingSpot, Long> {


}
