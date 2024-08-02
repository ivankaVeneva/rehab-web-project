package com.Rehab_App.model.dto;

import com.Rehab_App.model.enums.DeviceTypeEnum;
import java.util.List;

public record OfferDetailsDTO(Long id,
                              String description,
                              Integer mileage,
                              Integer price,
                              DeviceTypeEnum deviceType,
                              List<String> allCurrencies) {

}
