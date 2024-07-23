package com.Rehab_App.model.dto;

import java.math.BigDecimal;

public record ConversionResultDTO(String from, String to, BigDecimal amount, BigDecimal result) {

}
