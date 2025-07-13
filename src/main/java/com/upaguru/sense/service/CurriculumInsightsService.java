package com.upaguru.sense.service;

import com.upaguru.sense.entity.CurriculumInsights;

import java.util.List;
import java.util.UUID;

public interface CurriculumInsightsService {
    CurriculumInsights save(CurriculumInsights entity);
    CurriculumInsights findById(UUID id);
    List<CurriculumInsights> findAll();
}
