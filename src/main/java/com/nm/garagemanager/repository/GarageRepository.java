package com.nm.garagemanager.repository;





import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nm.garagemanager.entity.GarageSector;

@Repository
public interface GarageRepository extends JpaRepository<GarageSector, Long> {
    Optional<GarageSector> findBySector(String sector);
}
