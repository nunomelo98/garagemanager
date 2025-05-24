package com.nm.garagemanager.helpers;

import org.springframework.stereotype.Component;

@Component
public class PriceCalculation {
    public double calculatePrice(double basePrice, int currentOccupancy, int maxCapacity){
        double occupancyRate = (double) currentOccupancy / maxCapacity;

        if(occupancyRate < 0.25){
            return basePrice *0.9;
        }else if (occupancyRate < 0.5) {
            return basePrice;

        }else if (occupancyRate < 0.75) {
            return basePrice * 1.1;
        }else{
            return basePrice * 1.25;
        }
    }

}
