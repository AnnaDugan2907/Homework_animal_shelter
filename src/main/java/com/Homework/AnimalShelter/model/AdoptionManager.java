package com.Homework.AnimalShelter.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class AdoptionManager {
    private Map<String, Adopter> adopters = new HashMap<>();
    private List<DailyReport> reports = new ArrayList<>();

    // Добавление нового усыновителя
    public void addAdopter(Adopter adopter) {
        adopters.put(adopter.getUserId(), adopter);
    }

    // Получение пользователя по ID
    public Adopter getUser(String userId) {
        return adopters.get(userId);
    }

    // Сохранение отчета
    public void saveReport(Adopter user, String diet, String healthStatus, String behaviorChanges, String photoPath) {
        DailyReport report = new DailyReport(user.getUserId(), LocalDate.now(), photoPath, diet, healthStatus, behaviorChanges);
        reports.add(report);
        user.setLastReportDate(LocalDate.now());
    }

    // Получение всех пользователей
    public Collection<Adopter> getAllAdopters() {
        return adopters.values();
    }

    // Проверка пропущенных отчетов и отправка напоминаний
    public void checkReports() {
        LocalDate now = LocalDate.now();
        for (Adopter user : adopters.values()) {
            LocalDate lastReport = user.getLastReportDate();
            long minutesSinceLastReport = lastReport == null ? Long.MAX_VALUE : ChronoUnit.MINUTES.between(lastReport, now);

            long checkIntervalMinutes = 120; // пример интервала в минутах (2 часа)

            if (lastReport == null || minutesSinceLastReport > checkIntervalMinutes) {
                // Расчет оставшихся дней до окончания испытательного срока
                long daysLeft = 0;
                if (user.getAdoptionStartDate() != null) {
                    LocalDate endDate = user.getAdoptionStartDate().plusDays(user.getTrialPeriodDays());
                    daysLeft = ChronoUnit.DAYS.between(now, endDate);
                    if (daysLeft < 0) {
                        daysLeft = 0; // если срок уже истек
                    }
                }
                // Вызов метода отправки напоминания с количеством дней
                sendReminder(user, (int) daysLeft);
            } else {
                notifyVolunteer(user);
            }
        }
    }

    // Отправка напоминания усыновителю
    private void sendReminder(Adopter user, int daysLeft) {
        String reminder = String.format(
                ("Напоминаем: до окончания испытательного срока осталось %d дней.\n\n" +
                        "Пожалуйста, продолжайте отправлять ежедневные отчёты о состоянии питомца."),
                daysLeft);
        // В реальной системе отправляем сообщение пользователю
        System.out.println("Напоминание пользователю: " + reminder + user.getUserId());
    }

    // Уведомление волонтера о пропущенном отчете
    private void notifyVolunteer(Adopter user) {
        System.out.println("Волонтеру нужно проверить пользователя: " + user.getUserId());
        // Здесь интеграция с системой уведомлений
    }

    // Проверка срока и обновление статуса
    public void checkTrialStatus(Adopter user) {
        LocalDate endDate = user.getAdoptionStartDate().plusDays(user.getTrialPeriodDays() + user.getExtensionDays());
        if (LocalDate.now().isAfter(endDate)) {
            if (user.isTrialPassed()) {
                sendMessage(user.getUserId(), "Поздравляем! Вы успешно прошли испытательный срок.\n\n" +
                        "Теперь вы полноправный хозяин [питомца]. Спасибо за заботу!");
            } else {
                sendMessage(user.getUserId(), "К сожалению, вы не прошли испытательный срок.\n\n" +
                                        "Инструкции по дальнейшим шагам:\n" +
                                        "1. Свяжитесь с куратором приюта\n" +
                                        "2. Обсудите возможность продления срока\n" +
                                        "3. При необходимости верните животное в приют\n\n" +
                                        "Куратор свяжется с вами в ближайшее время.");
            }
        }
    }

    // Отправка сообщения пользователю
    private void sendMessage(String userId, String message) {
        System.out.println("Отправка сообщения пользователю " + userId + ": " + message);
    }
}
