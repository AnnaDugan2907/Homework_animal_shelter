package com.Homework.AnimalShelter.controller;

import com.Homework.AnimalShelter.model.PetAdopter;
import com.Homework.AnimalShelter.model.PetReport;
import com.Homework.AnimalShelter.service.PetReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Контроллер для управления отчётами о питомцах
 * Обрабатывает HTTP‑запросы, связанные с отчётами о состоянии питомцев
 */
@RestController
@RequestMapping("/api/pet-reports")
@Tag(name = "Управление отчётами о питомцах", description = "API для управления отчётами о питомцах")
public class PetReportController {

    @Autowired
    private PetReportService petReportService;

    /**
     * Получение всех отчётов о питомцах
     * HTTP GET /api/pet-reports
     * @return Список всех отчётов в системе
     */
    @GetMapping
    @Operation(description = "Получить все отчёты о питомцах")
    public List<PetReport> getAllPetReports() {
        return petReportService.getAllPetReports();
    }

    /**
     * Добавление нового отчёта о питомце
     * HTTP POST /api/pet-reports
     * @param petReport Объект отчёта для создания
     * @return Созданный отчёт с HTTP‑статусом 201 (CREATED)
     */
    @PostMapping
    @Operation(description = "Добавить новый отчёт о питомце")
    public ResponseEntity<PetReport> createPetReport(@RequestBody PetReport petReport) {
        PetReport createdReport = petReportService.createPetReport(petReport);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReport);
    }

    /**
     * Получение отчётов по кандидату на усыновление
     * HTTP GET /api/pet-reports/adopter/{adopterId}
     * @param adopterId ID кандидата, для которого нужно получить отчёты
     * @return Список отчётов для указанного кандидата
     */
    @GetMapping("/adopter/{adopterId}")
    @Operation(description = "Получить отчёты по кандидату")
    public List<PetReport> getReportsByAdopterId(@PathVariable Long adopterId) {
        return petReportService.findByAdopterId(adopterId);
    }

    /**
     * Получение отчётов с жалобами
     * HTTP GET /api/pet-reports/complaints
     * @return Список отчётов, содержащих жалобы или проблемы
     */
    @GetMapping("/complaints")
    @Operation(description = "Получить отчёты с жалобами")
    public List<PetReport> getComplaintReports() {
        return petReportService.findComplaintReports();
    }

    /**
     * Получение отчётов за конкретную дату
     * HTTP GET /api/pet-reports/date/{date}
     * @param date Дата отчёта в формате YYYY-MM-DD
     * @return Список отчётов за указанную дату
     */
    @GetMapping("/date/{date}")
    @Operation(description = "Получить отчёты за конкретную дату")
    public List<PetReport> getReportsByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return petReportService.findByReportDate(date);
    }

    /**
     * Получение отчётов по статусу
     * HTTP GET /api/pet-reports/status/{status}
     * @param status Статус отчёта (нормальный, плохой, отправлено)
     * @return Список отчётов с указанным статусом
     */
    @GetMapping("/status/{status}")
    @Operation(description = "Получить отчёты по статусу")
    public List<PetReport> getReportsByStatus(@PathVariable String status) {
        return petReportService.findByReportStatus(status);
    }

    /**
     * Получение отчётов кандидата за период
     * HTTP GET /api/pet-reports/adopter/{adopterId}/period
     * @param adopterId ID кандидата
     * @param startDate Начальная дата периода (YYYY-MM-DD)
     * @param endDate Конечная дата периода (YYYY-MM-DD)
     * @return Список отчётов за указанный период
     */
    @GetMapping("/adopter/{adopterId}/period")
    @Operation(description = "Получить отчёты кандидата за период")
    public List<PetReport> getReportsByAdopterInPeriod(
            @PathVariable Long adopterId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        PetAdopter adopter = new PetAdopter();
        adopter.setId(adopterId);
        return petReportService.findReportsByAdopterInPeriod(adopter, startDate, endDate);
    }
}
