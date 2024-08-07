package com.example.Rehab_offers.service.impl;

import com.example.Rehab_offers.model.dto.AddOfferDTO;
import com.example.Rehab_offers.model.dto.OfferDTO;
import com.example.Rehab_offers.model.entity.OfferEntity;
import com.example.Rehab_offers.repository.OfferRepository;
import com.example.Rehab_offers.service.OfferService;
import com.example.Rehab_offers.service.exception.ObjectNotFoundException;
import java.time.Instant;
import java.time.Period;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

 
  private final Logger LOGGER = LoggerFactory.getLogger(OfferService.class);
  private final OfferRepository offerRepository;
  private final Period retentionPeriod;

  public OfferServiceImpl(OfferRepository offerRepository,
      @Value("${offers.retention.period}") Period retentionPeriod) {
    this.offerRepository = offerRepository;
    this.retentionPeriod = retentionPeriod;
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
  @PreAuthorize("@offerServiceImpl.isOwner(#userDetails, #offerId)")
  public void deleteOffer(UserDetails userDetails, Long offerId) {
    offerRepository.deleteById(offerId);
  }

   @Override
  public boolean isOwner(UserDetails userDetails, Long offerId) {
    //
    return true;
  }



  @Override
  public PagedModel<OfferDTO> getAllOffers(Pageable pageable) {

    return new PagedModel<>(offerRepository
        .findAll(pageable)
        .map(OfferServiceImpl::map)
    );
  }

  @Override
  public void cleanupOldOffers() {
    Instant now = Instant.now();
    Instant deleteBefore = now.minus(retentionPeriod);
    LOGGER.info("Removing all offers older than " + deleteBefore);
    offerRepository.deleteOldOffers(deleteBefore);
    LOGGER.info("Old orders were removed");


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
