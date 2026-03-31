package com.Homework.AnimalShelter.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pet_adopters")
public class PetAdopter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "shelter_id")
    private Integer shelterId;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "contact_details", length = 255)
    private String contactDetails;

    @Column(name = "pet_type", length = 10)
    private String petType;

    @Column(name = "documents_list")
    private String documentsList;

    @Column(name = "transportation_tips")
    private String transportationTips;

    @Column(name = "home_setup_recommendations")
    private String homeSetupRecommendations;

    @Column(name = "reasons_for_rejection")
    private String reasonsForRejection;

    @Column(name = "adoption_status", length = 20)
    private String adoptionStatus;

    @Column(name = "trial_period_days")
    private Integer trialPeriodDays;

    @Column(name = "additional_days")
    private Integer additionalDays;

    @Column(name = "trial_start_date")
    private LocalDate trialStartDate;

    @Column(name = "trial_end_date")
    private LocalDate trialEndDate;

    @Column(name = "created_at")
    private java.time.OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private java.time.OffsetDateTime updatedAt;

    // Связи
    @OneToMany(mappedBy = "adopter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AdoptionStage> adoptionStages;

    // Конструкторы
    public PetAdopter() {
    }

    public PetAdopter(String name, String contactDetails) {
        this.name = name;
        this.contactDetails = contactDetails;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShelterId() {
        return shelterId;
    }

    public void setShelterId(Integer shelterId) {
        this.shelterId = shelterId;
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

    public String getDocumentsList() {
        return documentsList;
    }

    public void setDocumentsList(String documentsList) {
        this.documentsList = documentsList;
    }

    public String getTransportationTips() {
        return transportationTips;
    }

    public void setTransportationTips(String transportationTips) {
        this.transportationTips = transportationTips;
    }

    public String getHomeSetupRecommendations() {
        return homeSetupRecommendations;
    }

    public void setHomeSetupRecommendations(String homeSetupRecommendations) {
        this.homeSetupRecommendations = homeSetupRecommendations;
    }

    public String getReasonsForRejection() {
        return reasonsForRejection;
    }

    public void setReasonsForRejection(String reasonsForRejection) {
        this.reasonsForRejection = reasonsForRejection;
    }

    public String getAdoptionStatus() {
        return adoptionStatus;
    }

    public void setAdoptionStatus(String adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }

    public Integer getTrialPeriodDays() {
        return trialPeriodDays;
    }

    public void setTrialPeriodDays(Integer trialPeriodDays) {
        this.trialPeriodDays = trialPeriodDays;
    }

    public Integer getAdditionalDays() {
        return additionalDays;
    }

    public void setAdditionalDays(Integer additionalDays) {
        this.additionalDays = additionalDays;
    }

    public LocalDate getTrialStartDate() {
        return trialStartDate;
    }

    public void setTrialStartDate(LocalDate trialStartDate) {
        this.trialStartDate = trialStartDate;
    }

    public LocalDate getTrialEndDate() {
        return trialEndDate;
    }

    public void setTrialEndDate(LocalDate trialEndDate) {
        this.trialEndDate = trialEndDate;
    }

    public java.time.OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.time.OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public java.time.OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.time.OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<AdoptionStage> getAdoptionStages() {
        return adoptionStages;
    }

    public void setAdoptionStages(List<AdoptionStage> adoptionStages) {
        this.adoptionStages = adoptionStages;
    }

}
