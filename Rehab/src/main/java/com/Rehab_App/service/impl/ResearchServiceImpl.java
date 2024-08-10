package com.Rehab_App.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.Rehab_App.model.dto.ResearchDTO;
import com.Rehab_App.model.dto.ModelDTO;
import com.Rehab_App.repository.ResearchRepository;
import com.Rehab_App.repository.ModelRepository;
import com.Rehab_App.service.ResearchService;
import org.springframework.stereotype.Service;

@Service
public class ResearchServiceImpl implements ResearchService {
  private final ResearchRepository researchRepository;

  public ResearchServiceImpl(ResearchRepository researchRepository) {
    this.researchRepository = researchRepository;
  }

  @Override
  public List<ResearchDTO> getAllResearch() {

    return researchRepository.getAllResearch().stream()
            .map(research -> new ResearchDTO(
                    research.getName(),
                    research.getModels().stream()
                            .map(model -> new ModelDTO(model.getId(), model.getName()))
                            .sorted(Comparator.comparing(ModelDTO::name))
                            .collect(Collectors.toList())
            ))
            .sorted(Comparator.comparing(ResearchDTO::name))
            .collect(Collectors.toList());
  }
}
