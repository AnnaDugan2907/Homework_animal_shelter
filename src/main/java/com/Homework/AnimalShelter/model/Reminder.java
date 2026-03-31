package com.Homework.AnimalShelter.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reminders")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "adopter_id")
    private Long adopterId;

    private String message;

    @Column(name = "reminder_date")
    private LocalDate reminderDate; // изменено с LocalDateTime на LocalDate

    @Column(name = "reminder_type")
    private String reminderType; // соответствует столбцу reminder_type

    private boolean sent;

    @Column(name = "processed")
    private boolean processed = false; // добавлено поле для столбца processed

    // Конструкторы
    public Reminder() {}

    public Reminder(Long adopterId, String message, LocalDate reminderDate, String reminderType) {
        this.adopterId = adopterId;
        this.message = message;
        this.reminderDate = reminderDate;
        this.reminderType = reminderType;
        this.sent = false;
        this.processed = false;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdopterId() {
        return adopterId;
    }

    public void setAdopterId(Long adopterId) {
        this.adopterId = adopterId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(LocalDate reminderDate) {
        this.reminderDate = reminderDate;
    }

    public String getReminderType() {
        return reminderType;
    }

    public void setReminderType(String reminderType) {
        this.reminderType = reminderType;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}
