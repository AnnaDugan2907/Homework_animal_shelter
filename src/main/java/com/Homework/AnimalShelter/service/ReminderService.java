package com.Homework.AnimalShelter.service;

import com.Homework.AnimalShelter.model.Reminder;
import com.Homework.AnimalShelter.repositor.ReminderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReminderService {

    private final ReminderRepository reminderRepository;

    public ReminderService(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    /**
     * Получение всех напоминаний
     * @return Список всех напоминаний в системе
     */
    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }

    /**
     * Создание нового напоминания
     * @param reminder Объект напоминания для сохранения
     * @return Сохранённое напоминание с присвоенным ID
     */
    public Reminder createReminder(Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    /**
     * Поиск напоминаний по ID кандидата
     * @param adopterId ID кандидата на усыновление
     * @return Список напоминаний для указанного кандидата
     */
    public List<Reminder> findByAdopterId(Long adopterId) {
        return reminderRepository.findByAdopterId(adopterId);
    }

    /**
     * Поиск неотправленных напоминаний
     * @return Список напоминаний, где sent = false
     */
    public List<Reminder> findUnsentReminders() {
        return reminderRepository.findBySentFalse(); // исправлено: было findByIsSentFalse()
    }

    /**
     * Отметка напоминания как отправленного
     * @param id ID напоминания
     * @return Optional с обновлённым напоминанием
     */
    public Optional<Reminder> markAsSent(Long id) {
        return reminderRepository.findById(id).map(reminder -> {
            reminder.setSent(true);
            return reminderRepository.save(reminder);
        });
    }
}
