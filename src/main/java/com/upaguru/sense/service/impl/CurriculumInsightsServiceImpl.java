package com.upaguru.sense.service.impl;

import com.upaguru.sense.entity.CurriculumInsights;
import com.upaguru.sense.repository.CurriculumInsightsRepository;
import com.upaguru.sense.service.CurriculumInsightsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CurriculumInsightsServiceImpl implements CurriculumInsightsService {

    private final CurriculumInsightsRepository repository;

    @Override
    public CurriculumInsights save(CurriculumInsights entity) {
        return repository.save(entity);
    }

    @Override
    public CurriculumInsights findById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<CurriculumInsights> findAll() {
        return repository.findAll();
    }
}
