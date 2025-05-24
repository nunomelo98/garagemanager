package com.nm.garagemanager.exceptions;


public class SpotNotFoundforlatlngException extends RuntimeException {
    public SpotNotFoundforlatlngException(double lat, double lng) {
        super(String.format("Spot not found at location [lat: %.6f, lng: %.6f]", lat, lng));
    }
}