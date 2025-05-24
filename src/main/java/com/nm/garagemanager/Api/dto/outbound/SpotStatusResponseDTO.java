package com.nm.garagemanager.Api.dto.outbound;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpotStatusResponseDTO {
    private boolean ocupied;
    private LocalDateTime entryTime;
    private LocalDateTime timeParked;

}
