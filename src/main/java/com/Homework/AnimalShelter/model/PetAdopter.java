package com.Homework.AnimalShelter.model;

public class PetAdopter {
    private int id;
    private String name;
    private String contactDetails;
    private String petType;

    // Конструктор по умолчанию
    public PetAdopter() {
    }

    // Конструктор с параметрами
    public PetAdopter(int id, String name, String contactDetails, String petType) {
        this.id = id;
        this.name = name;
        this.contactDetails = contactDetails;
        this.petType = petType;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

}
