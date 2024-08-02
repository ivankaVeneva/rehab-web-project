package com.Rehab_App.service;

import com.Rehab_App.model.dto.AddOfferDTO;
import com.Rehab_App.model.dto.OfferDetailsDTO;
import com.Rehab_App.model.dto.OfferSummaryDTO;
import java.util.List;

public interface OfferService {

  void createOffer(AddOfferDTO addOfferDTO);

  void deleteOffer(long offerId);

  OfferDetailsDTO getOfferDetails(Long id);

  List<OfferSummaryDTO> getAllOffersSummary();
}
