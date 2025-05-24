package com.nm.garagemanager.Api.dto.outbound;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PlateStatusResponseDTO {
    private String licensePlate;
    private double priceUntilNow;
    private LocalDateTime entryTime;
    private String timeParked;
    private Double lat;
    private Double lng;
}


