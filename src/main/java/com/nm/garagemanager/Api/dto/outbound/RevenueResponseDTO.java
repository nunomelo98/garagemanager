package com.nm.garagemanager.Api.dto.outbound;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RevenueResponseDTO {
    private double amount;
    private String currency;
    private LocalDateTime timestamp;
}