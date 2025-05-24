package com.nm.garagemanager.webhook.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WebhookEventDTO {
    @JsonProperty("license_plate")
    private String licensePlate;

    @JsonProperty("entry_time")
    private LocalDateTime entryTime;

    @JsonProperty("exit_time")
    private LocalDateTime exitTime;

    private Double lat;
    private Double lng;

    @JsonProperty("event_type")
    private String eventType;
}
