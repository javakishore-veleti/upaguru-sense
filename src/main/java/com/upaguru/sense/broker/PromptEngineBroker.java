package com.upaguru.sense.broker;

import com.upaguru.sense.dto.PromptRequest;

public interface PromptEngineBroker {
    String getCompletion(PromptRequest request);
}
