package com.nm.garagemanager.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name ="vehicle_record")
public class VehicleRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;

    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    private double paidAmount;
    private Boolean paid;
    private Double lat;
    private Double lng;
    private boolean active;
    private String sector;
    private Long spotId;

}
