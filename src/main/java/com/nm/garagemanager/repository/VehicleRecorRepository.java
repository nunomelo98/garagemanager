package com.nm.garagemanager.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.nm.garagemanager.entity.VehicleRecord;

@Repository
public interface VehicleRecorRepository extends JpaRepository<VehicleRecord, Long> {
    Optional<VehicleRecord> findByLicensePlateAndActiveTrue(String licensePlate);
    Optional<VehicleRecord>  findTopByLicensePlateOrderByEntryTimeDesc(String licensePlate);

    List<VehicleRecord> findBySectorAndExitTimeIsNotNullAndEntryTimeBetween(String sector, ZonedDateTime start, ZonedDateTime end);
    Optional<VehicleRecord> findTopBySpotIdAndExitTimeIsNull(Long spotId);

    @Query("SELECT COALESCE(SUM(v.paidAmount), 0.0) " +
       "FROM vehicle_record v " +
       "WHERE v.exitTime >= :startOfDay AND v.exitTime < :endOfDay " +
       "AND v.sector = :sector AND v.paid = true")
double sumRevenueByDateAndSector(
    @Param("startOfDay") LocalDateTime startOfDay,
    @Param("endOfDay") LocalDateTime endOfDay,
    @Param("sector") String sector);

}
