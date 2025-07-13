package com.upaguru.sense.service;

import com.upaguru.sense.entity.FeedbackAnalysisResult;

import java.util.List;
import java.util.UUID;

public interface FeedbackAnalysisResultService {
    FeedbackAnalysisResult save(FeedbackAnalysisResult entity);
    FeedbackAnalysisResult findById(UUID id);
    List<FeedbackAnalysisResult> findAll();
}
