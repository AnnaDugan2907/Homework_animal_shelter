package com.Homework.AnimalShelter.model;

public class UserSession {
    private String currentStage;
    private String shelterType;
    private String userContacts;
    private String volunteerIssue;
    private boolean lastReportHadPhoto;
    private boolean lastReportHadText;
    private int poorReportCount;
    private long adoptionDate;
    private int trialPeriodDays;
    private int additionalTrialDays;
    private Long lastTrialReminderTimestamp;
    private boolean trialPassed;
    private boolean hasAdopted;
    private String chatId;
;

    public UserSession() {
        this.currentStage = "0"; // по умолчанию стартовая стадия
        this.poorReportCount = 0;
        this.additionalTrialDays = 0;
        this.trialPassed = false;
        this.hasAdopted = false;
    }

    // Геттеры и сеттеры

    public String getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }

    public String getShelterType() {
        return shelterType;
    }

    public void setShelterType(String shelterType) {
        this.shelterType = shelterType;
    }

    public String getUserContacts() {
        return userContacts;
    }

    public void setUserContacts(String userContacts) {
        this.userContacts = userContacts;
    }

    public String getVolunteerIssue() {
        return volunteerIssue;
    }

    public void setVolunteerIssue(String volunteerIssue) {
        this.volunteerIssue = volunteerIssue;
    }

    public boolean isLastReportHadPhoto() {
        return lastReportHadPhoto;
    }

    public void setLastReportHadPhoto(boolean lastReportHadPhoto) {
        this.lastReportHadPhoto = lastReportHadPhoto;
    }

    public boolean isLastReportHadText() {
        return lastReportHadText;
    }

    public void setLastReportHadText(boolean lastReportHadText) {
        this.lastReportHadText = lastReportHadText;
    }

    public int getPoorReportCount() {
        return poorReportCount;
    }

    public void setPoorReportCount(int poorReportCount) {
        this.poorReportCount = poorReportCount;
    }

    public long getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(long adoptionDate) {
        this.adoptionDate = adoptionDate;
    }

    public int getTrialPeriodDays() {
        return trialPeriodDays;
    }

    public void setTrialPeriodDays(int trialPeriodDays) {
        this.trialPeriodDays = trialPeriodDays;
    }

    public int getAdditionalTrialDays() {
        return additionalTrialDays;
    }

    public void setAdditionalTrialDays(int additionalTrialDays) {
        this.additionalTrialDays = additionalTrialDays;
    }

    public Long getLastTrialReminderTimestamp() {
        return lastTrialReminderTimestamp;
    }

    public void setLastTrialReminderTimestamp(Long lastTrialReminderTimestamp) {
        this.lastTrialReminderTimestamp = lastTrialReminderTimestamp;
    }

    public boolean isTrialPassed() {
        return trialPassed;
    }

    public void setTrialPassed(boolean trialPassed) {
        this.trialPassed = trialPassed;
    }

    public boolean hasAdopted() {
        return hasAdopted;
    }

    public void setHasAdopted(boolean hasAdopted) {
        this.hasAdopted = hasAdopted;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
