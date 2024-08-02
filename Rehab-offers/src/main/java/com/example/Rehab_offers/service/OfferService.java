package com.example.Rehab_offers.service;

import com.example.Rehab_offers.model.dto.AddOfferDTO;
import com.example.Rehab_offers.model.dto.OfferDTO;
import java.util.List;

public interface OfferService {

  OfferDTO createOffer(AddOfferDTO addOfferDTO);

  void deleteOffer(Long offerId);

  OfferDTO getOfferById(Long id);

  List<OfferDTO> getAllOffers();

}
