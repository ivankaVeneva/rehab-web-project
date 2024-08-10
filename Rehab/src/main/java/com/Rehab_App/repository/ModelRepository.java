package com.Rehab_App.repository;

import com.Rehab_App.model.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {


    List<ModelEntity> findAllByResearchId (Long id);
}
