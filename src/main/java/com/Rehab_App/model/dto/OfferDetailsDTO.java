package com.Rehab_App.model.dto;

import com.Rehab_App.model.enums.EngineTypeEnum;
import java.util.List;

public record OfferDetailsDTO(Long id,
                              String description,
                              Integer mileage,
                              Integer price,
                              EngineTypeEnum engineType,
                              List<String> allCurrencies) {

}
