package com.Rehab_App.model.dto;

import com.Rehab_App.model.enums.DeviceTypeEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record AddOfferDTO(
    @NotNull(message = "{add.offer.description.length}")
    @Size(message = "{add.offer.description.length}",
        min = 5,
        max = 500) String description,//not necessarily from message source
    @NotNull @PositiveOrZero Integer mileage,
    @NotNull @PositiveOrZero Integer price,
    @NotNull DeviceTypeEnum deviceType
) {

  public static AddOfferDTO empty() {
    return new AddOfferDTO(null, null, null, null);
  }

}
