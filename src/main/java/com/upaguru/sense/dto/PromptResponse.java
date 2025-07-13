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
