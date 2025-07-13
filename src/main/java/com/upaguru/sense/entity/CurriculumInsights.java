package com.upaguru.sense.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "curriculum_insights", schema = "upaguru_sense")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurriculumInsights {

    @Id
    private UUID id;

    @Column(name = "source", columnDefinition = "TEXT")
    private String source;

    @Column(name = "extracted_topics", columnDefinition = "TEXT")
    private String extractedTopics;

    @Column(name = "grade_level")
    private String gradeLevel;

    @Column(name = "insights", columnDefinition = "jsonb")
    private String insights;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}
