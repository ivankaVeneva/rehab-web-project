package com.Rehab_App.model.dto;

import com.Rehab_App.model.enums.EngineTypeEnum;

public record OfferSummaryDTO(Long id,
                              String description,
                              Integer mileage,
                              EngineTypeEnum engineType) {

}
