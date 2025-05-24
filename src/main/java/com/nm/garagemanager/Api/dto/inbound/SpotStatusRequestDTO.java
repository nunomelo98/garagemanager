package com.nm.garagemanager.Api.dto.inbound;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SpotStatusRequestDTO {
    @NotNull(message = "need to intruduce a latitude")
    private double lat;
    @NotNull(message = "need to intruduce a longitude")
    private double lng;

}
