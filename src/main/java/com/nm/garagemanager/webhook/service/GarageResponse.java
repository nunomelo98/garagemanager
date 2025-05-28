package com.nm.garagemanager.webhook.service;

import java.util.List;

import com.nm.garagemanager.entity.GarageSector;
import com.nm.garagemanager.entity.ParkingSpot;

import lombok.Data;

@Data
public class GarageResponse {
    private List<GarageSector> garage;
    private List<ParkingSpot> spots;
}
