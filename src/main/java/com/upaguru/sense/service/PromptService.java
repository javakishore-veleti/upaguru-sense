package com.upaguru.sense.service;

import com.upaguru.sense.dto.PromptRequest;
import com.upaguru.sense.dto.PromptResponse;

public interface PromptService {
    PromptResponse processPrompt(PromptRequest request);
}
