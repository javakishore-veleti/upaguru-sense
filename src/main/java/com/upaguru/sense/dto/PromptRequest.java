package com.upaguru.sense.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromptRequest {

    private String userId;            // ID of the user invoking the prompt
    private String persona;           // Role or user type e.g. "principal", "new teacher"
    private String taskType;          // Task like "lesson-plan", "summarize", etc.
    private String prompt;            // Raw input text

    private String topic;             // Topic like "Photosynthesis"
    private String gradeLevel;        // e.g. "7"
    private String format;            // e.g. "bullets", "paragraph"
    private String tone;              // e.g. "friendly", "formal"
    private String additionalContext; // Optional extra info

    private String inputType;

    private String intent;
    private String promptTemplate;
    private String input;
    private String userType;
}