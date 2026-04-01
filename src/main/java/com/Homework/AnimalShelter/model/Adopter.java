package com.Homework.AnimalShelter.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Adopter {

    // Поля
    private String userId;
    private String shelterType; // "кошки" или "собаки"
    private LocalDate adoptionStartDate;
    private LocalDateTime lastReportDate;
    private int trialPeriodDays; // обычно 30
    private boolean trialPassed;
    private int extensionDays; // 14 или 30
    private boolean reportIncomplete; // флаг для предупреждения

    // Конструктор
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
    public String getUserId() {
        return userId;
    }

    public String getShelterType() {
        return shelterType;
    }

    public LocalDate getAdoptionStartDate() {
        return adoptionStartDate;
    }

    public void setAdoptionStartDate(LocalDate adoptionStartDate) {
        if (adoptionStartDate == null) {
            throw new IllegalArgumentException("adoptionStartDate cannot be null");
        }
        this.adoptionStartDate = adoptionStartDate;
    }

    public LocalDateTime getLastReportDate() {
        return lastReportDate;
    }

    // Сеттеры (решают ошибки: «cannot find symbol method setUserId», «setTrialPeriodDays» и т. д.)
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setTrialPeriodDays(int trialPeriodDays) {
        this.trialPeriodDays = trialPeriodDays;
    }

    public void setLastReportDate(LocalDateTime date) {
        this.lastReportDate = date;
    }

    public int getTrialPeriodDays() {
        return trialPeriodDays;
    }

    public void setTrialPassed(boolean passed) {
        this.trialPassed = passed;
    }

    public boolean isTrialPassed() {
        return trialPassed;
    }

    public int getExtensionDays() {
        return extensionDays;
    }

    public void setExtensionDays(int days) {
        if (days < 0) {
            throw new IllegalArgumentException("Extension days cannot be negative");
        }
        this.extensionDays = days;
    }

    public boolean isReportIncomplete() {
        return reportIncomplete;
    }

    public void setReportIncomplete(boolean reportIncomplete) {
        this.reportIncomplete = reportIncomplete;
    }

    // Метод расчёта оставшихся дней испытательного срока
    public int calculateRemainingTrialDays() {
        LocalDate today = LocalDate.now();
        long daysPassed = ChronoUnit.DAYS.between(adoptionStartDate, today);
        int remainingDays = trialPeriodDays - (int) daysPassed;
        return Math.max(0, remainingDays); // Возвращаем 0, если срок истёк
    }

    // Проверка истечения испытательного срока
    public boolean isTrialExpired() {
        LocalDate expirationDate = adoptionStartDate.plusDays(trialPeriodDays);
        return LocalDate.now().isAfter(expirationDate);
    }

    // Продление испытательного срока
    public void extendTrialPeriod(int days) {
        if (days < 0) {
            throw new IllegalArgumentException("Cannot extend with negative days");
        }
        this.extensionDays += days;
        this.trialPeriodDays += days;
    }

    // Обновление статуса по отчётам
    public void updateStatusByReport(LocalDateTime reportDate) {
        this.lastReportDate = reportDate;
        if (isTrialExpired()) {
            this.trialPassed = !isReportIncomplete();
        }
    }

    // Переопределение toString для удобства отладки
    @Override
    public String toString() {
        return "Adopter{" +
                "userId='" + userId + '\'' +
                ", shelterType='" + shelterType + '\'' +
                ", adoptionStartDate=" + adoptionStartDate +
                ", lastReportDate=" + lastReportDate +
                ", trialPeriodDays=" + trialPeriodDays +
                ", trialPassed=" + trialPassed +
                ", extensionDays=" + extensionDays +
                ", reportIncomplete=" + reportIncomplete +
                '}';
    }
}
