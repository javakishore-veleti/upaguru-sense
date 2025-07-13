package com.upaguru.sense.controller;

import com.upaguru.sense.dto.PromptRequest;
import com.upaguru.sense.dto.PromptResponse;
import com.upaguru.sense.service.PromptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prompt")
@RequiredArgsConstructor
public class PromptController {

    private final PromptService promptService;

    @PostMapping("/process")
    public PromptResponse handlePrompt(@RequestBody PromptRequest request) {
        return promptService.processPrompt(request);
    }
}
