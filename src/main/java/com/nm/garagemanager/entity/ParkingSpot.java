package com.nm.garagemanager.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "parking_spot")
public class ParkingSpot {
    @Id
    private Long id;
    private String sector;
    private double lat;
    private double lng;
    private Boolean occupied;

}
