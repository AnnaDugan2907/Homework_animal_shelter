package com.Homework.AnimalShelter.service;

import com.Homework.AnimalShelter.model.PetAdopter;
import com.Homework.AnimalShelter.model.PetReport;
import com.Homework.AnimalShelter.repositor.PetReportRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PetReportService {
    private final PetReportRepository petReportRepository;

    public PetReportService(PetReportRepository petReportRepository) {
        this.petReportRepository = petReportRepository;
    }

    /**
     * Получение всех отчётов о питомцах
     * @return Список всех отчётов в системе
     */
    public List<PetReport> getAllPetReports() {
        return petReportRepository.findAll();
    }

    /**
     * Создание нового отчёта о питомце
     * @param petReport Объект отчёта для сохранения
     * @return Сохранённый отчёт с присвоенным ID
     */
    public PetReport createPetReport(PetReport petReport) {
        return petReportRepository.save(petReport);
    }

    /**
     * Поиск отчётов по ID кандидата
     * @param adopterId ID кандидата на усыновление
     * @return Список отчётов для указанного кандидата
     */
    public List<PetReport> findByAdopterId(Long adopterId) {
        return petReportRepository.findByAdopterId(adopterId);
    }

    /**
     * Поиск отчётов с жалобами (где complaint = true)
     * @return Список отчётов, содержащих жалобы
     */
    public List<PetReport> findComplaintReports() {
        return petReportRepository.findByComplaintTrue();
    }

    /**
     * Поиск отчётов по дате
     * @param reportDate Дата отчёта
     * @return Список отчётов за указанную дату
     */
    public List<PetReport> findByReportDate(LocalDate reportDate) {
        return petReportRepository.findByReportDate(reportDate);
    }

    /**
     * Поиск отчётов по статусу
     * @param reportStatus Статус отчёта
     * @return Список отчётов с указанным статусом
     */
    public List<PetReport> findByReportStatus(String reportStatus) {
        return petReportRepository.findByReportStatus(reportStatus);
    }

    /**
     * Поиск отчётов кандидата за период
     * @param adopter Кандидат на усыновление
     * @param startDate Начальная дата периода
     * @param endDate Конечная дата периода
     * @return Список отчётов за указанный период
     */
    public List<PetReport> findReportsByAdopterInPeriod(
            PetAdopter adopter, LocalDate startDate, LocalDate endDate) {
        return petReportRepository.findByAdopterAndReportDateBetween(adopter, startDate, endDate);
    }
}
