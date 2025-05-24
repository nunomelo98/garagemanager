package com.nm.garagemanager.exceptions;

public class PlateStatusNotFoundException extends RuntimeException{
    public PlateStatusNotFoundException(String licensePlate){
        super("License plate not found: " + licensePlate);
    }

}
