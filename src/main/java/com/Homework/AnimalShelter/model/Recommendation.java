package com.Homework.AnimalShelter.model;

import com.Homework.AnimalShelter.info.ApplicableFor;
import com.Homework.AnimalShelter.info.RecommendationType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "recommendations")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shelter_id")
    private Long shelterId;

    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private RecommendationType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "applicable_for")
    private ApplicableFor applicableFor;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Конструкторы
    public Recommendation() {}

    public Recommendation(String title, String description, Long shelterId,
                          RecommendationType type, ApplicableFor applicableFor) {
        this.title = title;
        this.description = description;
        this.shelterId = shelterId;
        this.type = type;
        this.applicableFor = applicableFor;
        this.createdAt = LocalDateTime.now();
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShelterId() {
        return shelterId;
    }

    public void setShelterId(Long shelterId) {
        this.shelterId = shelterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RecommendationType getType() {
        return type;
    }

    public void setType(RecommendationType type) {
        this.type = type;
    }

    public ApplicableFor getApplicableFor() {
        return applicableFor;
    }

    public void setApplicableFor(ApplicableFor applicableFor) {
        this.applicableFor = applicableFor;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
