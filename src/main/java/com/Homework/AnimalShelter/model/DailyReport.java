package com.Homework.AnimalShelter.model;

import java.time.LocalDate;

public class DailyReport {
    private String userId;
    private LocalDate reportDate;
    private String photoPath;
    private String diet;
    private String healthStatus;
    private String behaviorChanges;

    public DailyReport(String userId, LocalDate reportDate, String photoPath, String diet, String healthStatus, String behaviorChanges) {
        this.userId = userId;
        this.reportDate = reportDate;
        this.photoPath = photoPath;
        this.diet = diet;
        this.healthStatus = healthStatus;
        this.behaviorChanges = behaviorChanges;
    }

    // Геттеры
    public String getUserId() { return userId; }
    public LocalDate getReportDate() { return reportDate; }
    public String getPhotoPath() { return photoPath; }
    public String getDiet() { return diet; }
    public String getHealthStatus() { return healthStatus; }
    public String getBehaviorChanges() { return behaviorChanges; }
}
