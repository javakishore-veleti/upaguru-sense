package com.upaguru.sense.repository;

import com.upaguru.sense.entity.CurriculumInsights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CurriculumInsightsRepository extends JpaRepository<CurriculumInsights, UUID> {
}
