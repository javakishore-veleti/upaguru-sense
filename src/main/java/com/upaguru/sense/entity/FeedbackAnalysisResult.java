package com.upaguru.sense.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "feedback_analysis_result", schema = "upaguru_sense")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackAnalysisResult {

    @Id
    private UUID id;

    @Column(name = "raw_feedback", columnDefinition = "TEXT")
    private String rawFeedback;

    @Column(name = "sentiment_score")
    private Float sentimentScore;

    @Column(name = "dominant_topics", columnDefinition = "TEXT")
    private String dominantTopics;

    @Column(name = "named_entities", columnDefinition = "TEXT")
    private String namedEntities;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "analyzed_at", updatable = false)
    private Date analyzedAt;

    @PrePersist
    protected void onCreate() {
        analyzedAt = new Date();
    }
}
