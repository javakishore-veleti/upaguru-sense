#!/bin/bash

BASE_DIR="src/main/java/com/upaguru/sense"
RESOURCES_DIR="src/main/resources"

mkdir -p $BASE_DIR/{controller,dto,service,service/impl,entity,repository,config,broker,broker/impl}
mkdir -p $RESOURCES_DIR

# Entity: PromptSessionLog
cat <<EOF > $BASE_DIR/entity/PromptSessionLog.java
package com.upaguru.sense.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromptSessionLog {
    @Id
    private UUID id;

    private String persona;
    private String intent;
    private String model;

    @Lob
    private String prompt;

    @Lob
    private String completion;

    private int tokenCount;
    private long latency;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
    }
}
EOF

# DTO: PromptRequest & PromptResponse
cat <<EOF > $BASE_DIR/dto/PromptRequest.java
package com.upaguru.sense.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromptRequest {
    private String persona;
    private String intent;
    private String input;
}
EOF

cat <<EOF > $BASE_DIR/dto/PromptResponse.java
package com.upaguru.sense.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromptResponse {
    private String output;
    private int tokensUsed;
    private long responseTime;
}
EOF

# Repository
cat <<EOF > $BASE_DIR/repository/PromptSessionLogRepository.java
package com.upaguru.sense.repository;

import com.upaguru.sense.entity.PromptSessionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PromptSessionLogRepository extends JpaRepository<PromptSessionLog, UUID> {}
EOF

# Service Interface & Impl
cat <<EOF > $BASE_DIR/service/PromptService.java
package com.upaguru.sense.service;

import com.upaguru.sense.dto.PromptRequest;
import com.upaguru.sense.dto.PromptResponse;

public interface PromptService {
    PromptResponse processPrompt(PromptRequest request);
}
EOF

cat <<EOF > $BASE_DIR/service/impl/PromptServiceImpl.java
package com.upaguru.sense.service.impl;

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

    @Override
    public PromptResponse processPrompt(PromptRequest request) {
        // Placeholder logic
        String output = "[Persona: " + request.getPersona() + "] " +
                        "[Intent: " + request.getIntent() + "] " +
                        "Processed: " + request.getInput();

        PromptSessionLog log = new PromptSessionLog();
        log.setId(UUID.randomUUID());
        log.setPersona(request.getPersona());
        log.setIntent(request.getIntent());
        log.setPrompt(request.getInput());
        log.setCompletion(output);
        log.setModel("gpt-4");
        log.setTokenCount(100);
        log.setLatency(200);

        logRepository.save(log);

        return new PromptResponse(output, 100, 200);
    }
}
EOF

# Controller
cat <<EOF > $BASE_DIR/controller/PromptController.java
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
EOF

# Config
cat <<EOF > $BASE_DIR/config/OpenAiConfig.java
package com.upaguru.sense.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class OpenAiConfig {

    @Value("\${openai.api.key}")
    private String apiKey;

    @Value("\${openai.model:gpt-4}")
    private String model;

    private String apiUrl = "https://api.openai.com/v1/chat/completions";
}
EOF

# Application.java (optional if missing)
APP_FILE="src/main/java/com/upaguru/sense/UpaguruSenseApplication.java"
mkdir -p $(dirname "$APP_FILE")
cat <<EOF > $APP_FILE
package com.upaguru.sense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UpaguruSenseApplication {
    public static void main(String[] args) {
        SpringApplication.run(UpaguruSenseApplication.class, args);
    }
}
EOF

echo "✅ Code generation for upaguru-sense completed."
