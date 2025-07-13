package com.upaguru.sense.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prompt_session_log", schema = "upaguru_sense")
public class PromptSessionLog {

    @Id
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "input_type")
    private String inputType; // e.g., "lesson-plan", "feedback", "summarize"

    private String persona;

    @Column(name = "model_used")
    private String modelUsed;

    @Column(name = "prompt_template", columnDefinition = "TEXT")
    private String promptTemplate;

    @Column(name = "model_input", columnDefinition = "TEXT")
    private String modelInput;

    @Column(name = "model_output", columnDefinition = "TEXT")
    private String modelOutput;

    @Column(name = "token_usage")
    private int tokenUsage;

    @Column(name = "latency_ms")
    private int latencyMs;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    private String intent;

    @Lob
    private String prompt;

    @Lob
    private String completion;

    private int tokenCount;
    private long latency;
    private String model;

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
    }
}