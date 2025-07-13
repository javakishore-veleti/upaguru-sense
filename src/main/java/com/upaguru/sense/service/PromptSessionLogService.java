package com.upaguru.sense.service;

import com.upaguru.sense.entity.PromptSessionLog;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PromptSessionLogService {
    PromptSessionLog save(PromptSessionLog entity);
    Optional<PromptSessionLog> findById(String id);
    List<PromptSessionLog> findAll();
}
