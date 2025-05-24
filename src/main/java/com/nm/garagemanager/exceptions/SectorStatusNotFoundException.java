package com.nm.garagemanager.exceptions;

public class SectorStatusNotFoundException extends RuntimeException {
    public SectorStatusNotFoundException(String sector) {
        super("Sector status not found for sector: " + sector);
    }

}
