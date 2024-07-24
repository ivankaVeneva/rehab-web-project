package com.Rehab_App.model.dto;

import com.Rehab_App.model.enums.DeviceTypeEnum;

public record OfferSummaryDTO(Long id,
                              String description,
                              Integer mileage,
                              DeviceTypeEnum deviceType) {

}
