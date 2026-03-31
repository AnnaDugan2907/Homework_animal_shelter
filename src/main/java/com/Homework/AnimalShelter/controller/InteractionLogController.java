package com.Homework.AnimalShelter.controller;


import com.Homework.AnimalShelter.model.InteractionLog;
import com.Homework.AnimalShelter.service.InteractionLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Контроллер для управления логами взаимодействий
 * Обрабатывает HTTP‑запросы, связанные с логами взаимодействий пользователей с системой
 */
@RestController
@RequestMapping("/api/interaction-logs")
@Tag(name = "Управление логами взаимодействий", description = "API для управления логами взаимодействий")
public class InteractionLogController {

    @Autowired
    private InteractionLogService interactionLogService;

    /**
     * Получение всех логов взаимодействий
     * HTTP GET /api/interaction-logs
     *
     * @return Список всех логов в системе
     */
    @GetMapping
    @Operation(description = "Получить все логи взаимодействий")
    public ResponseEntity<List<InteractionLog>> getAllInteractionLogs() {
        return ResponseEntity.ok(interactionLogService.getAllInteractionLogs());
    }

    /**
     * Добавление нового лога взаимодействия
     * HTTP POST /api/interaction-logs
     *
     * @param interactionLog Объект лога для создания
     * @return Созданный лог с HTTP‑статусом 201 (CREATED)
     */
    @PostMapping
    @Operation(description = "Добавить новый лог взаимодействия")
    public ResponseEntity<InteractionLog> createInteractionLog(@RequestBody InteractionLog interactionLog) {
        InteractionLog createdLog = interactionLogService.createInteractionLog(interactionLog);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLog);
    }

    /**
     * Получение логов по пользователю
     * HTTP GET /api/interaction-logs/user/{userId}
     *
     * @param userId ID пользователя, для которого нужно получить логи
     * @return Список логов для указанного пользователя
     */
    @GetMapping("/user/{userId}")
    @Operation(description = "Получить логи по пользователю")
    public ResponseEntity<List<InteractionLog>> getLogsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(interactionLogService.findByUserId(userId));
    }

    /**
     * Получение логов за определённый период
     * HTTP GET /api/interaction-logs/period?startDate=...&endDate=...
     *
     * @param startDate Начальная дата периода (формат: YYYY-MM-DD)
     * @param endDate   Конечная дата периода (формат: YYYY-MM-DD)
     * @return Список логов, созданных в указанном временном диапазоне
     */
    @GetMapping("/period")
    @Operation(description = "Получить логи за период")
    public ResponseEntity<List<InteractionLog>> getLogsByPeriod(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return ResponseEntity.ok(interactionLogService.findByDateRange(startDate, endDate));
    }

    /**
     * Получение логов по пользователю за период
     * HTTP GET /api/interaction-logs/user/{userId}/period?startDate=...&endDate=...
     *
     * @param userId    ID пользователя, для которого нужно получить логи
     * @param startDate Начальная дата периода (формат: YYYY-MM-DD)
     * @param endDate   Конечная дата периода (формат: YYYY-MM-DD)
     * @return Список логов для указанного пользователя за указанный период
     */
    @GetMapping("/user/{userId}/period")
    @Operation(description = "Получить логи по пользователю за период")
    public ResponseEntity<List<InteractionLog>> getLogsByUserAndPeriod(
            @PathVariable Long userId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        List<InteractionLog> logs = interactionLogService.findByUserIdAndDateRange(userId, startDate, endDate);
        return ResponseEntity.ok(logs);
    }


    @GetMapping("/type/{interactionType}")
    @Operation(description = "Получить логи по типу взаимодействия")
    public ResponseEntity<List<InteractionLog>> getLogsByInteractionType(@PathVariable String interactionType) {
        List<InteractionLog> logs = interactionLogService.findByInteractionType(interactionType);
        return ResponseEntity.ok(logs);
    }
}