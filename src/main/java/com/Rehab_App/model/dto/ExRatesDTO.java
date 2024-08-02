package com.Rehab_App.model.dto;

import java.math.BigDecimal;
import java.util.Map;

public record ExRatesDTO(String base, Map<String, BigDecimal> rates) {
/*
 {
  "base": "USD",
  "rates": {
    ...
    "BGN": 1.8266,
    ....
    "EUR": 0.934216,
     ...
  }
}
 */
}
