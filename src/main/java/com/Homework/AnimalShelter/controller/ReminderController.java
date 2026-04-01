package com.Homework.AnimalShelter.controller;

import com.Homework.AnimalShelter.exception.ResourceNotFoundException;
import com.Homework.AnimalShelter.model.Reminder;
import com.Homework.AnimalShelter.service.ReminderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления напоминаниями
 * Обрабатывает HTTP‑запросы, связанные с напоминаниями для кандидатов
 */
@RestController
@RequestMapping("/api/reminders")
@Tag(name = "Управление напоминаниями", description = "API для управления напоминаниями")
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    /**
     * Получение всех напоминаний
     * HTTP GET /api/reminders
     * @return Список всех напоминаний в системе
     */
    @GetMapping
    @Operation(description = "Получить все напоминания")
    public List<Reminder> getAllReminders() {
        return reminderService.getAllReminders();
    }

    /**
     * Добавление нового напоминания
     * HTTP POST /api/reminders
     * @param reminder Объект напоминания для создания
     * @return Созданное напоминание с HTTP‑статусом 201 (CREATED)
     */
    @PostMapping
    @Operation(description = "Добавить новое напоминание")
    @ResponseStatus(HttpStatus.CREATED)
    public Reminder createReminder(@RequestBody Reminder reminder) {
        return reminderService.createReminder(reminder);
    }
    /**
     * Получение напоминаний по кандидату на усыновление
     * HTTP GET /api/reminders/adopter/{adopterId}
     * @param adopterId ID кандидата, для которого нужно получить напоминания
     * @return Список напоминаний для указанного кандидата
     */
    @GetMapping("/adopter/{adopterId}")
    @Operation(description = "Получить напоминания по кандидату")
    public List<Reminder> getRemindersByAdopter(@PathVariable Long adopterId) {
        return reminderService.findByAdopterId(adopterId);
    }

    /**
     * Получение неотправленных напоминаний
     * HTTP GET /api/reminders/unsent
     * @return Список всех напоминаний, которые ещё не были отправлены
     */
    @GetMapping("/unsent")
    @Operation(description = "Получить неотправленные напоминания")
    public List<Reminder> getUnsentReminders() {
        return reminderService.findUnsentReminders();
    }

    /**
     * Обновление статуса напоминания как отправленного
     * HTTP PATCH /api/reminders/{id}/mark-sent
     * @param id ID напоминания, которое нужно отметить как отправленное
     * @return Обновлённое напоминание
     */
    @PatchMapping("/{id}/mark-sent")
    @Operation(description = "Отметить напоминание как отправленное")
    public Reminder markReminderAsSent(@PathVariable Long id) {
        return reminderService.markAsSent(id)
                .orElseThrow(() -> new ResourceNotFoundException("Напоминание не найдено"));
    }
}