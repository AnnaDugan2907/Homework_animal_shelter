package com.Homework.AnimalShelter.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Adopter {
    private String userId;
    private String shelterType; // "кошки" или "собаки"
    private LocalDate adoptionStartDate;
    private LocalDate lastReportDate;
    private int trialPeriodDays; // обычно 30
    private boolean trialPassed;
    private int extensionDays; // 14 или 30
    private boolean reportIncomplete; // флаг для предупреждения

    public Adopter(String userId, String shelterType, LocalDate adoptionStartDate) {
        this.userId = userId;
        this.shelterType = shelterType;
        this.adoptionStartDate = adoptionStartDate;
        this.lastReportDate = null;
        this.trialPeriodDays = 30;
        this.trialPassed = false;
        this.extensionDays = 0;
        this.reportIncomplete = false;
    }

    // Геттеры и сеттеры
    public String getUserId() { return userId; }
    public String getShelterType() { return shelterType; }
    public LocalDate getAdoptionStartDate() { return adoptionStartDate; }
    public LocalDate getLastReportDate() { return lastReportDate; }
    public void setLastReportDate(LocalDate date) { this.lastReportDate = date; }
    public int getTrialPeriodDays() { return trialPeriodDays; }
    public void setTrialPassed(boolean passed) { this.trialPassed = passed; }
    public boolean isTrialPassed() { return trialPassed; }
    public int getExtensionDays() { return extensionDays; }
    public void setExtensionDays(int days) { this.extensionDays = days; }
    public boolean isReportIncomplete() { return reportIncomplete; }
    public void setReportIncomplete(boolean reportIncomplete) { this.reportIncomplete = reportIncomplete; }
}
