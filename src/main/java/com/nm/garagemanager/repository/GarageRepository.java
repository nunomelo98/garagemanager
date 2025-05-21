package com.nm.garagemanager.repository;





import org.springframework.data.jpa.repository.JpaRepository;

import com.nm.garagemanager.entity.GarageSector;

public interface GarageRepository extends JpaRepository<GarageSector, Long> {
}
