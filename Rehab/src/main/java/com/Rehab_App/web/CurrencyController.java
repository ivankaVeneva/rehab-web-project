package com.Rehab_App.web;

import com.Rehab_App.model.dto.ConversionResultDTO;
import com.Rehab_App.service.ExRateService;
import com.Rehab_App.service.exception.ApiObjectNotFoundException;
import com.Rehab_App.web.aop.WarnIfExecutionExceeds;
import java.math.BigDecimal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

  private final ExRateService exRateService;

  public CurrencyController(ExRateService exRateService) {
    this.exRateService = exRateService;
  }

  @WarnIfExecutionExceeds(
      threshold = 800
  )
  @GetMapping("/api/convert")
  public ResponseEntity<ConversionResultDTO> convert(
      @RequestParam("from") String from,
      @RequestParam("to") String to,
      @RequestParam("amount") BigDecimal amount
  ) {
    BigDecimal result = exRateService.convert(from, to, amount);
    return ResponseEntity.ok(new ConversionResultDTO(
        from,
        to,
        amount,
        result
    ));
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(ApiObjectNotFoundException.class)
  @ResponseBody
  public NotFoundErrorInfo handleApiObjectNotFoundException(ApiObjectNotFoundException apiObjectNotFoundException) {
    return new NotFoundErrorInfo("NOT FOUND", apiObjectNotFoundException.getId());
  }


  public record NotFoundErrorInfo(String code, Object id) {

  }
}
