package com.Homework.AnimalShelter.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "adoption_stages")
public class AdoptionStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adopter_id")
    private PetAdopter adopter;

    @Column(name = "stage", length = 50)
    private String stage;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    // Конструкторы
    public AdoptionStage() {
    }

    public AdoptionStage(PetAdopter adopter, String stage, Date startDate) {
        this.adopter = adopter;
        this.stage = stage;
        this.startDate = startDate;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetAdopter getAdopter() {
        return adopter;
    }

    public void setAdopter(PetAdopter adopter) {
        this.adopter = adopter;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
