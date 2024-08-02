package com.example.Rehab_offers.service.impl;

import com.example.Rehab_offers.model.dto.AddOfferDTO;
import com.example.Rehab_offers.model.dto.OfferDTO;
import com.example.Rehab_offers.model.entity.OfferEntity;
import com.example.Rehab_offers.repository.OfferRepository;
import com.example.Rehab_offers.service.OfferService;
import com.example.Rehab_offers.service.exception.ObjectNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;

  public OfferServiceImpl(OfferRepository offerRepository) {
    this.offerRepository = offerRepository;
  }

  @Override
  public OfferDTO createOffer(AddOfferDTO addOfferDTO) {
    OfferEntity offerEntity = offerRepository.save(map(addOfferDTO));
    return map(offerEntity);
  }

  @Override
  public OfferDTO getOfferById(Long id) {
    return offerRepository
        .findById(id)
        .map(OfferServiceImpl::map)
        .orElseThrow(ObjectNotFoundException::new);
  }

  @Override
  public void deleteOffer(Long offerId) {
    offerRepository.deleteById(offerId);
  }

  @Override
  public List<OfferDTO> getAllOffers() {
    return offerRepository
        .findAll()
        .stream()
        .map(OfferServiceImpl::map)
        .toList();
  }

  private static OfferDTO map(OfferEntity offerEntity) {
    // todo - use mapped (e.g. ModelMapper, MapStruct)
    return new OfferDTO(
        offerEntity.getId(),
        offerEntity.getDescription(),
        offerEntity.getMileage(),
        offerEntity.getPrice(),
        offerEntity.getDevice()
    );
  }

  private static OfferEntity map(AddOfferDTO addOfferDTO) {
    // todo - use mapped (e.g. ModelMapper)
    return new OfferEntity()
        .setDescription(addOfferDTO.description())
        .setDevice(addOfferDTO.deviceType())
        .setMileage(addOfferDTO.mileage())
        .setPrice(addOfferDTO.price());
  }
}
