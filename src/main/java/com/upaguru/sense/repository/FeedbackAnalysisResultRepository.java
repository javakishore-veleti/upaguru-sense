package com.upaguru.sense.repository;

import com.upaguru.sense.entity.FeedbackAnalysisResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FeedbackAnalysisResultRepository extends JpaRepository<FeedbackAnalysisResult, UUID> {
}
