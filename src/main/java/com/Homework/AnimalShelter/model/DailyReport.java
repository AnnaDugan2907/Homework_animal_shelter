package com.Homework.AnimalShelter.model;

import java.time.LocalDate;

public class DailyReport {
    private String userId;
    private LocalDate date;
    private String photoPath; // путь к фото
    private String diet;
    private String healthStatus;
    private String behaviorChanges;

    public DailyReport(String userId, LocalDate date, String photoPath, String diet, String healthStatus, String behaviorChanges) {
        this.userId = userId;
        this.date = date;
        this.photoPath = photoPath;
        this.diet = diet;
        this.healthStatus = healthStatus;
        this.behaviorChanges = behaviorChanges;
    }

    // Геттеры
    public String getUserId() { return userId; }
    public LocalDate getDate() { return date; }
    public String getPhotoPath() { return photoPath; }
    public String getDiet() { return diet; }
    public String getHealthStatus() { return healthStatus; }
    public String getBehaviorChanges() { return behaviorChanges; }
}
