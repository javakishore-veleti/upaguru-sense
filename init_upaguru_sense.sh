#!/bin/bash

# Base package path
BASE_DIR=src/main/java/com/upaguru/sense
RESOURCES_DIR=src/main/resources
PACKAGE_PATH=$BASE_DIR
DTO_DIR=$PACKAGE_PATH/dtos
CONFIG_DIR=$PACKAGE_PATH/config
BROKER_DIR=$PACKAGE_PATH/broker
BROKER_IMPL_DIR=$BROKER_DIR/impl
SERVICE_DIR=$PACKAGE_PATH/service
SERVICE_IMPL_DIR=$SERVICE_DIR/impl
CONTROLLER_DIR=$PACKAGE_PATH/controller

# Create directories
mkdir -p $DTO_DIR $CONFIG_DIR $BROKER_DIR $BROKER_IMPL_DIR $SERVICE_DIR $SERVICE_IMPL_DIR $CONTROLLER_DIR $RESOURCES_DIR

# Create Java classes
echo 'package com.upaguru.sense.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class OpenAiConfig {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.model:gpt-4}")
    private String model;

    private String apiUrl = "https://api.openai.com/v1/chat/completions";
}' > $CONFIG_DIR/OpenAiConfig.java

echo 'package com.upaguru.sense.broker;

import com.upaguru.sense.dtos.PromptRequest;

public interface PromptEngineBroker {
    String getCompletion(PromptRequest request);
}' > $BROKER_DIR/PromptEngineBroker.java

echo 'package com.upaguru.sense.broker.impl;

import com.upaguru.sense.broker.PromptEngineBroker;
import com.upaguru.sense.config.OpenAiConfig;
import com.upaguru.sense.dtos.PromptRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class OpenAiPromptEngineBroker implements PromptEngineBroker {

    private final OpenAiConfig config;
    private final RestTemplate restTemplate;

    @Override
    public String getCompletion(PromptRequest request) {
        return String.format("Prompt completion for %s: %s", request.getUserType(), request.getTaskType());
    }
}' > $BROKER_IMPL_DIR/OpenAiPromptEngineBroker.java

echo 'package com.upaguru.sense.service;

import com.upaguru.sense.dtos.PromptRequest;

public interface PromptService {
    String processPrompt(PromptRequest request);
}' > $SERVICE_DIR/PromptService.java

echo 'package com.upaguru.sense.service.impl;

import com.upaguru.sense.broker.PromptEngineBroker;
import com.upaguru.sense.dtos.PromptRequest;
import com.upaguru.sense.service.PromptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromptServiceImpl implements PromptService {

    private final PromptEngineBroker promptEngineBroker;

    @Override
    public String processPrompt(PromptRequest request) {
        return promptEngineBroker.getCompletion(request);
    }
}' > $SERVICE_IMPL_DIR/PromptServiceImpl.java

echo 'package com.upaguru.sense.dtos;

import lombok.Data;

@Data
public class PromptRequest {
    private String userType;
    private String taskType;
    private String subject;
    private String formatStyle;
    private String tone;
    private String additionalContext;
}' > $DTO_DIR/PromptRequest.java

echo 'package com.upaguru.sense.controller;

import com.upaguru.sense.dtos.PromptRequest;
import com.upaguru.sense.service.PromptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prompt")
@RequiredArgsConstructor
public class PromptController {

    private final PromptService promptService;

    @PostMapping
    public String generatePrompt(@RequestBody PromptRequest request) {
        return promptService.processPrompt(request);
    }
}' > $CONTROLLER_DIR/PromptController.java

echo "Spring Boot scaffolding for upaguru-sense created successfully."
