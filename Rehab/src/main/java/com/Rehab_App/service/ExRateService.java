package com.Rehab_App.service;

import com.Rehab_App.model.dto.ExRatesDTO;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ExRateService {

  List<String> allSupportedCurrencies();
  boolean hasInitializedExRates();

  ExRatesDTO fetchExRates();

  void updateRates(ExRatesDTO exRatesDTO);

  BigDecimal convert(String from, String to, BigDecimal amount);
}
