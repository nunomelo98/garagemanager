package com.nm.garagemanager.exceptions;

public class SpotNotFoundException extends RuntimeException {
    public SpotNotFoundException(Long id) {
        super("Parking spot not found for ID: " + id);
    }

}
