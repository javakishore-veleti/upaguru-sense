package com.upaguru.sense.broker.impl;

import com.upaguru.sense.broker.PromptEngineBroker;
import com.upaguru.sense.config.OpenAiConfig;
import com.upaguru.sense.dto.PromptRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class OpenAiPromptEngineBroker implements PromptEngineBroker {

    private final OpenAiConfig config;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String getCompletion(PromptRequest request) {
        try {
            // Prepare headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(config.getApiKey());

            // Construct message payload (system + user prompt)
            List<Map<String, String>> messages = List.of(
                    Map.of("role", "system", "content", "You are an expert AI educator assistant. Always be clear and structured."),
                    Map.of("role", "user", "content", buildPrompt(request))
            );

            // Request body
            Map<String, Object> requestBody = Map.of(
                    "model", config.getModel(),
                    "messages", messages,
                    "temperature", 0.7,
                    "max_tokens", 500
            );

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

            // Send request to OpenAI API
            ResponseEntity<String> response = restTemplate.postForEntity(
                    config.getApiUrl(),
                    entity,
                    String.class
            );

            // Parse response to extract content
            JsonNode root = objectMapper.readTree(response.getBody());
            return root
                    .path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText();

        } catch (Exception e) {
            log.error("Error calling OpenAI API: {}", e.getMessage(), e);
            return "Sorry, I couldn't generate a response at the moment.";
        }
    }

    private String buildPrompt(PromptRequest request) {
        return String.format(
                "You are responding as a '%s'.\n" +
                        "Your task: %s\n" +
                        "Subject: %s\n" +
                        "Format: %s\n" +
                        "Tone: %s\n" +
                        "Additional context: %s\n\n" +
                        "Input: %s",
                request.getPersona(),
                request.getTaskType(),
                request.getTopic(),
                request.getFormat(),
                request.getTone(),
                request.getAdditionalContext(),
                request.getPrompt()
        );
    }
}
