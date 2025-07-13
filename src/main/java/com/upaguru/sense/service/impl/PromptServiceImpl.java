package com.upaguru.sense.service.impl;

import com.upaguru.sense.broker.PromptEngineBroker;
import com.upaguru.sense.dto.PromptRequest;
import com.upaguru.sense.dto.PromptResponse;
import com.upaguru.sense.entity.PromptSessionLog;
import com.upaguru.sense.repository.PromptSessionLogRepository;
import com.upaguru.sense.service.PromptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PromptServiceImpl implements PromptService {

    private final PromptSessionLogRepository logRepository;
    private final PromptEngineBroker promptEngineBroker;

    @Override
    public PromptResponse processPrompt(PromptRequest request) {
        long startTime = System.currentTimeMillis();

        // 1. Call the broker to get LLM response
        String output = promptEngineBroker.getCompletion(request);

        long latency = System.currentTimeMillis() - startTime;

        // 2. Log the session
        PromptSessionLog log = new PromptSessionLog();
        log.setId(UUID.randomUUID().toString());
        log.setUserId(request.getUserId() != null ? request.getUserId() : "anonymous");
        log.setInputType(request.getInputType());
        log.setPersona(request.getPersona());
        log.setIntent(request.getIntent());
        log.setModel("gpt-4"); // Or pull from config.getModel()
        log.setPrompt(request.getInput());
        log.setCompletion(output);
        log.setTokenCount(0); // Update if token usage is tracked
        log.setLatency(latency);

        logRepository.save(log);

        // 3. Return API response
        return new PromptResponse(output, 0, latency);
    }
}
