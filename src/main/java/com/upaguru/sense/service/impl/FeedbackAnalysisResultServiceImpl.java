package com.upaguru.sense.service.impl;

import com.upaguru.sense.entity.FeedbackAnalysisResult;
import com.upaguru.sense.repository.FeedbackAnalysisResultRepository;
import com.upaguru.sense.service.FeedbackAnalysisResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FeedbackAnalysisResultServiceImpl implements FeedbackAnalysisResultService {

    private final FeedbackAnalysisResultRepository repository;

    @Override
    public FeedbackAnalysisResult save(FeedbackAnalysisResult entity) {
        return repository.save(entity);
    }

    @Override
    public FeedbackAnalysisResult findById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<FeedbackAnalysisResult> findAll() {
        return repository.findAll();
    }
}
