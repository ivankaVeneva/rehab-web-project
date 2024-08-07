package com.example.Rehab_offers.repository;

import com.example.Rehab_offers.model.entity.OfferEntity;
import java.time.Instant;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

  @Transactional
  @Modifying
  @Query("DELETE FROM OfferEntity o WHERE o.created < :olderThan")
  void deleteOldOffers(Instant olderThan);
}
