package com.nm.garagemanager.webhook.service;

import java.util.List;

import com.nm.garagemanager.entity.GarageSector;
import com.nm.garagemanager.entity.ParkingSpot;

public class GarageResponse {
    private List<GarageSector> garage;
    private List<ParkingSpot> spots;

    public List<GarageSector> getGarage() {
        return garage;
    }

    public void setGarage(List<GarageSector> garage) {
        this.garage = garage;
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public void setSpots(List<ParkingSpot> spots) {
        this.spots = spots;
    }
}
