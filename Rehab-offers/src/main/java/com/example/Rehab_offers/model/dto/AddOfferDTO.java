package com.example.Rehab_offers.model.dto;

import com.example.Rehab_offers.model.enums.DeviceTypeEnum;

public record AddOfferDTO(
    String description,
    Integer mileage,
    Integer price,
    DeviceTypeEnum  deviceType
) {
}
