package com.upaguru.sense.service.impl;

import com.upaguru.sense.entity.PromptSessionLog;
import com.upaguru.sense.repository.PromptSessionLogRepository;
import com.upaguru.sense.service.PromptSessionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PromptSessionLogServiceImpl implements PromptSessionLogService {

    private final PromptSessionLogRepository repository;

    @Override
    public PromptSessionLog save(PromptSessionLog entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<PromptSessionLog> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<PromptSessionLog> findAll() {
        return repository.findAll();
    }
}
