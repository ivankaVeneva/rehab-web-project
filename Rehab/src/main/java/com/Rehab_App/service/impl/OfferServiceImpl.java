package com.Rehab_App.service.impl;

import com.Rehab_App.model.dto.AddOfferDTO;
import com.Rehab_App.model.dto.ExRatesDTO;
import com.Rehab_App.model.dto.OfferDetailsDTO;
import com.Rehab_App.model.dto.OfferSummaryDTO;
import com.Rehab_App.model.entity.OfferEntity;
import com.Rehab_App.repository.OfferRepository;
import com.Rehab_App.service.ExRateService;
import com.Rehab_App.service.OfferService;
import com.Rehab_App.service.exception.ObjectNotFoundException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

@Service
public class OfferServiceImpl implements OfferService {

  private final Logger LOGGER = LoggerFactory.getLogger(OfferServiceImpl.class);
  private final RestClient offerRestClient;
  private final OfferRepository offerRepository;
  private final ExRateService exRateService;

  public OfferServiceImpl(
      @Qualifier("offersRestClient") RestClient offerRestClient,
      OfferRepository offerRepository,
      ExRateService exRateService) {
    this.offerRestClient = offerRestClient;
    this.offerRepository = offerRepository;
    this.exRateService = exRateService;
  }

  @Override
  public void createOffer(AddOfferDTO addOfferDTO) {
    LOGGER.info("Creating new offer...");

        offerRestClient
        .post()
        .uri("/offers")
        .body(addOfferDTO)
        .retrieve();
  }

  @Override
  public void deleteOffer(long offerId) {
    offerRepository.deleteById(offerId);
  }

  @Override
  public OfferDetailsDTO getOfferDetails(Long id) {

    return offerRestClient
        .get()
        .uri("/offers/{id}", id)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .body(OfferDetailsDTO.class);
  }

  @Override
  public List<OfferSummaryDTO> getAllOffersSummary() {
    LOGGER.info("Get all offers...");

    return offerRestClient
        .get()
        .uri("/offers")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .body(new ParameterizedTypeReference<List<OfferSummaryDTO>>(){});
  }
}
