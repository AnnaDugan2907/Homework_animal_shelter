package com.Homework.AnimalShelter.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "interaction_logs")
public class InteractionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "interaction_type")
    private String interactionType;


    private String action;
    private String details;

    // Конструкторы
    public InteractionLog() {
    }

    public InteractionLog(Long userId, String action, String details) {
        this.userId = userId;
        this.timestamp = new Date();
        this.action = action;
        this.details = details;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(String interactionType) {
        this.interactionType = interactionType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
