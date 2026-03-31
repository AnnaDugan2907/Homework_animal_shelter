package com.Homework.AnimalShelter.service;

import com.Homework.AnimalShelter.model.InteractionLog;
import com.Homework.AnimalShelter.repositor.InteractionLogRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InteractionLogService {

    private final InteractionLogRepository interactionLogRepository;

    public InteractionLogService(InteractionLogRepository interactionLogRepository) {
        this.interactionLogRepository = interactionLogRepository;
    }

    /**
     * Получение всех логов взаимодействий
     * @return Список всех логов в системе
     */
    public List<InteractionLog> getAllInteractionLogs() {
        return interactionLogRepository.findAll();
    }

    /**
     * Создание нового лога взаимодействия
     * @param interactionLog Объект лога для сохранения
     * @return Сохранённый лог с присвоенным ID
     */
    public InteractionLog createInteractionLog(InteractionLog interactionLog) {
        return interactionLogRepository.save(interactionLog);
    }

    /**
     * Поиск логов по ID пользователя
     * @param userId ID пользователя
     * @return Список логов для указанного пользователя
     */
    public List<InteractionLog> findByUserId(Long userId) {
        return interactionLogRepository.findByUserId(userId);
    }

    /**
     * Поиск логов за определённый период времени
     * @param startDate Начальная дата периода
     * @param endDate Конечная дата периода
     * @return Список логов, созданных в указанном временном диапазоне
     */
    public List<InteractionLog> findByDateRange(Date startDate, Date endDate) {
        return interactionLogRepository.findByTimestampBetween(startDate, endDate);
    }

    /**
     * Поиск логов по ID пользователя за период времени
     * @param userId ID пользователя
     * @param startDate Начальная дата периода
     * @param endDate Конечная дата периода
     * @return Список логов для указанного пользователя за указанный период
     */
    public List<InteractionLog> findByUserIdAndDateRange(Long userId, Date startDate, Date endDate) {
        return interactionLogRepository.findByUserIdAndTimestampBetween(userId, startDate, endDate);
    }

    public List<InteractionLog> findByInteractionType(String interactionType) {
        return interactionLogRepository.findByInteractionType(interactionType);
    }
}
