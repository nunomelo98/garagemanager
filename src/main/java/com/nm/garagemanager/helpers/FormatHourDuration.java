package com.nm.garagemanager.helpers;

import java.time.Duration;

import org.springframework.stereotype.Component;

@Component
public class FormatHourDuration {
     public static String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        return String.format("%02dh:%02dm:%02ds", hours, minutes, seconds);
    }

}
