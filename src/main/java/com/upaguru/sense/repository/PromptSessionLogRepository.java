package com.upaguru.sense.repository;

import com.upaguru.sense.entity.PromptSessionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromptSessionLogRepository extends JpaRepository<PromptSessionLog, String> {
}
