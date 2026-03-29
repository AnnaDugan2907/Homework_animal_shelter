package com.Homework.AnimalShelter.service;

import com.Homework.AnimalShelter.model.Adopter;
import com.Homework.AnimalShelter.model.DailyReport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class AdoptionManager {

        // Приватные поля (инкапсуляция)
        public Map<String, Adopter> adopters = new HashMap<>();
        public List<DailyReport> reports = new ArrayList<>();

        // Геттеры для тестов (решают ошибки: «adopters has private access», «reports has private access»)
        public Map<String, Adopter> getAdopters() {
            return new HashMap<>(adopters); // Копируем карту, чтобы избежать изменений извне
        }

        public List<DailyReport> getReports() {
            return new ArrayList<>(reports); // Копируем список, чтобы избежать изменений извне
        }

        // Метод для работы с датами (заменяет ошибочный plusMinutes для LocalDate)
        public LocalDateTime addMinutesToDateTime(LocalDateTime dateTime, int minutes) {
            return dateTime.plusMinutes(minutes);
        }

        // Добавление нового усыновителя
        public void addAdopter(Adopter adopter) {
            adopters.put(adopter.getUserId(), adopter);
        }

        // Получение пользователя по ID
        public Adopter getUser(String userId) {
            return adopters.get(userId);
        }

    public Collection<Adopter> getAllAdopters() {
        return adopters.values();
    }

        // Сохранение отчёта
        public void saveReport(Adopter user, String diet, String healthStatus, String behaviorChanges, String photoPath) {
            DailyReport report = new DailyReport(user.getUserId(), LocalDate.now(), photoPath, diet, healthStatus, behaviorChanges);
            reports.add(report);
            user.setLastReportDate(LocalDateTime.now());
        }

        // Отправка сообщения (сделан public для тестирования)
        public void sendMessage(String userId, String message) {
            System.out.println("Сообщение отправлено пользователю " + userId + ": " + message);
            // Здесь может быть логика отправки SMS/email
        }

        // Проверка отчётов и отправка напоминаний
        public void checkReports() {
            LocalDateTime now = LocalDateTime.now();
            for (Adopter user : adopters.values()) {
                LocalDateTime lastReport = user.getLastReportDate(); // Убедимся, что это LocalDateTime
                long minutesSinceLastReport = ChronoUnit.MINUTES.between(lastReport, now);

                long checkIntervalMinutes = 120; // интервал в минутах (2 часа)

                if (lastReport == null || minutesSinceLastReport > checkIntervalMinutes) {
                    long daysLeft = calculateDaysLeft(user);
                    sendReminder(user, (int) daysLeft);
                } else {
                    notifyVolunteer(user);
                }
            }
        }

        // Расчёт оставшихся дней до окончания испытательного срока
        public long calculateDaysLeft(Adopter user) {
            long daysLeft = 0;
            LocalDate adoptionStartDate = user.getAdoptionStartDate();

            // Проверка на null
            if (adoptionStartDate == null) {
                return 0; // или выбросить исключение, если это критичная ошибка
            }

            LocalDate endDate = calculateEndDate(user);
            daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), endDate);

            // Если срок уже истёк — возвращаем 0
            if (daysLeft < 0) {
                daysLeft = 0;
            }

            return daysLeft;
        }

        // Отправка напоминания усыновителю
        protected void sendReminder(Adopter user, int daysLeft) {
            String reminder = String.format(
                    "Напоминаем: до окончания испытательного срока осталось %d дней.\n\n" +
                            "Пожалуйста, продолжайте отправлять ежедневные отчёты о состоянии питомца.",
                    daysLeft);
            sendMessage(user.getUserId(), reminder); // Используем public метод sendMessage
        }

        // Уведомление волонтёра о пропущенном отчёте
        protected void notifyVolunteer(Adopter user) {
            System.out.println("Волонтёру нужно проверить пользователя: " + user.getUserId());
            // Здесь интеграция с системой уведомлений
        }


    // Вычисление даты окончания испытательного срока
    private LocalDate calculateEndDate(Adopter user) {
        LocalDate adoptionStartDate = user.getAdoptionStartDate();
        int trialPeriodDays = user.getTrialPeriodDays();
        int extensionDays = user.getExtensionDays();

        if (adoptionStartDate == null) {
            throw new IllegalArgumentException("Дата начала усыновления не задана");
        }

        return adoptionStartDate.plusDays(trialPeriodDays + extensionDays);
    }

    // Проверка срока и обновление статуса
    public void checkTrialStatus(Adopter user) {
        LocalDate endDate = calculateEndDate(user);
        LocalDate today = LocalDate.now();

        if (today.isAfter(endDate)) {
            handleTrialExpiration(user);
        }
    }

    // Обработка истечения испытательного срока
    private void handleTrialExpiration(Adopter user) {
        if (user.isTrialPassed()) {
            sendMessage(user.getUserId(), "Поздравляем! Вы успешно прошли испытательный срок.");
        } else {
            sendMessage(user.getUserId(), "К сожалению, вы не прошли испытательный срок.");
        }
    }
}
