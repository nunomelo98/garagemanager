package com.nm.garagemanager.helpers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class PaymentCalculator {
    public static double calculatePayment(LocalDateTime entryTime, LocalDateTime exitTime, double hourlyRate) {
        if (entryTime == null || exitTime == null) {
            throw new IllegalArgumentException("Entry and exit time must not be null");
        }

        Duration duration = Duration.between(entryTime, exitTime);
        double hours = duration.toMinutes() / 60.0;


        return Math.round(hours * hourlyRate * 100.0) / 100.0;
    }

}
