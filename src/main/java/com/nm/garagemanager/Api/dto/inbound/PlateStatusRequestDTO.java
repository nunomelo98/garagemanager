package com.nm.garagemanager.Api.dto.inbound;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PlateStatusRequestDTO {
    @NotBlank(message = "licensePlate is Required")
    private String license_plate;
}
