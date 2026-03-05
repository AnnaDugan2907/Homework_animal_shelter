package com.Homework.AnimalShelter.model;

public class UserSession {
    private int currentStage; // этап взаимодействия
    private String shelterType; // "cats" или "dogs"

    // геттеры и сеттеры
    public int getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(int currentStage) {
        this.currentStage = currentStage;
    }

    public String getShelterType() {
        return shelterType;
    }

    public void setShelterType(String shelterType) {
        this.shelterType = shelterType;
    }
}
