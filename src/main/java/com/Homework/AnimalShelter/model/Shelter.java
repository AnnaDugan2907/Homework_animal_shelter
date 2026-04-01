package com.Homework.AnimalShelter.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shelters")
public class Shelter {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "type")
    private String type;

    private String address;

    @Column(name = "schedule")
    private String schedule;

    @Column(name = "contact_security")
    private String contactSecurity;

    @Column(name = "access_rules")
    private String accessRules;

    @Column(name = "safety_guidelines")
    private String safetyGuidelines;

    @Column(name = "location_map")
    private String locationMap;

    private String phone;
    private String email;
    private String description;

    // Конструкторы
    public Shelter() {
    }

    public Shelter(String name, String address, String phone, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getContactSecurity() {
        return contactSecurity;
    }

    public void setContactSecurity(String contactSecurity) {
        this.contactSecurity = contactSecurity;
    }

    public String getAccessRules() {
        return accessRules;
    }

    public void setAccessRules(String accessRules) {
        this.accessRules = accessRules;
    }

    public String getSafetyGuidelines() {
        return safetyGuidelines;
    }

    public void setSafetyGuidelines(String safetyGuidelines) {
        this.safetyGuidelines = safetyGuidelines;
    }

    public String getLocationMap() {
        return locationMap;
    }

    public void setLocationMap(String locationMap) {
        this.locationMap = locationMap;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
