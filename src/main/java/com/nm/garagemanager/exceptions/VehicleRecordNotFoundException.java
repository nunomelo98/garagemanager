package com.nm.garagemanager.exceptions;

public class VehicleRecordNotFoundException extends RuntimeException {
    public VehicleRecordNotFoundException(String plate) {
        super("Vehicle record not found for plate: " + plate);
    }

}
