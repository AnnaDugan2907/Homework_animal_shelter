package com.Homework.AnimalShelter.controller;

import com.Homework.AnimalShelter.exception.ResourceNotFoundException;
import com.Homework.AnimalShelter.model.Reminder;
import com.Homework.AnimalShelter.service.ReminderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Reminder>> getAllReminders() {
        return ResponseEntity.ok(reminderService.getAllReminders());
    }

    /**
     * Добавление нового напоминания
     * HTTP POST /api/reminders
     * @param reminder Объект напоминания для создания
     * @return Созданное напоминание с HTTP‑статусом 201 (CREATED)
     */
    @PostMapping
    @Operation(description = "Добавить новое напоминание")
    public ResponseEntity<Reminder> createReminder(@RequestBody Reminder reminder) {
        Reminder createdReminder = reminderService.createReminder(reminder);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReminder);
    }

    /**
     * Получение напоминаний по кандидату на усыновление
     * HTTP GET /api/reminders/adopter/{adopterId}
     * @param adopterId ID кандидата, для которого нужно получить напоминания
     * @return Список напоминаний для указанного кандидата
     */
    @GetMapping("/adopter/{adopterId}")
    @Operation(description = "Получить напоминания по кандидату")
    public ResponseEntity<List<Reminder>> getRemindersByAdopter(@PathVariable Long adopterId) {
        return ResponseEntity.ok(reminderService.findByAdopterId(adopterId));
    }

    /**
     * Получение неотправленных напоминаний
     * HTTP GET /api/reminders/unsent
     * @return Список всех напоминаний, которые ещё не были отправлены
     */
    @GetMapping("/unsent")
    @Operation(description = "Получить неотправленные напоминания")
    public ResponseEntity<List<Reminder>> getUnsentReminders() {
        return ResponseEntity.ok(reminderService.findUnsentReminders());
    }

    /**
     * Обновление статуса напоминания как отправленного
     * HTTP PATCH /api/reminders/{id}/mark-sent
     * @param id ID напоминания, которое нужно отметить как отправленное
     * @return Обновлённое напоминание
     */
    @PatchMapping("/{id}/mark-sent")
    @Operation(description = "Отметить напоминание как отправленное")
    public ResponseEntity<Reminder> markReminderAsSent(@PathVariable Long id) {
        Reminder updatedReminder = reminderService.markAsSent(id)
                .orElseThrow(() -> new ResourceNotFoundException("Напоминание не найдено"));
        return ResponseEntity.ok(updatedReminder);
    }
}