package com.Rehab_App.repository;

import java.util.List;
import com.Rehab_App.model.entity.ResearchEntity;
import com.Rehab_App.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResearchRepository extends JpaRepository<ResearchEntity, Long> {

//  @Query("SELECT r FROM ResearchEntity r "
//      + "JOIN FETCH r.models ")
  @EntityGraph(
      value = "researchdWithModels",
      attributePaths = "models"
  )
  @Query("SELECT r FROM ResearchEntity r")
  List<ResearchEntity> getAllResearch();

}