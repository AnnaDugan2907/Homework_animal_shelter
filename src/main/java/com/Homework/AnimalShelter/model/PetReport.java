package com.Homework.AnimalShelter.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "pet_reports")
public class PetReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adopter_id")
    private PetAdopter adopter;

    @Column(name = "report_date")
    private LocalDate reportDate;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "diet")
    private String diet;

    @Column(name = "well_being")
    private String wellBeing;

    @Column(name = "behavior_changes")
    private String behaviorChanges;

    @Column(name = "report_status")
    private String reportStatus;

    @Column(name = "is_complaint")
    private boolean complaint;

    @Column(name = "comments")
    private String comments;

    // Конструкторы
    public PetReport() {}

    public PetReport(PetAdopter adopter, LocalDate reportDate) {
        this.adopter = adopter;
        this.reportDate = reportDate;
        this.complaint = false;
        this.reportStatus = "отправлено";
    }

    // Геттеры и сеттеры (опущены для краткости; реализуйте для всех полей)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public PetAdopter getAdopter() { return adopter; }
    public void setAdopter(PetAdopter adopter) { this.adopter = adopter; }

    public LocalDate getReportDate() { return reportDate; }
    public void setReportDate(LocalDate reportDate) { this.reportDate = reportDate; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public String getDiet() { return diet; }
    public void setDiet(String diet) { this.diet = diet; }

    public String getWellBeing() { return wellBeing; }
    public void setWellBeing(String wellBeing) { this.wellBeing = wellBeing; }

    public String getBehaviorChanges() { return behaviorChanges; }
    public void setBehaviorChanges(String behaviorChanges) { this.behaviorChanges = behaviorChanges; }

    public String getReportStatus() { return reportStatus; }
    public void setReportStatus(String reportStatus) { this.reportStatus = reportStatus; }

    public boolean isComplaint() { return complaint; }
    public void setComplaint(boolean complaint) { this.complaint = complaint; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
}
