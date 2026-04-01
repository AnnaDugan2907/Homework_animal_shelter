package com.Homework.AnimalShelter.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AdopterTest {

    private Adopter adopter;

    @BeforeEach
    void setUp() {
        // Инициализируем объект для тестов
        adopter = new Adopter("user123", "кошки", LocalDate.of(2023, 10, 1));
    }

    // Тест конструктора и геттеров
    @Test
    void testConstructorAndGetters() {
        assertEquals("user123", adopter.getUserId());
        assertEquals("кошки", adopter.getShelterType());
        assertEquals(LocalDate.of(2023, 10, 1), adopter.getAdoptionStartDate());
        assertNull(adopter.getLastReportDate());
        assertEquals(30, adopter.getTrialPeriodDays());
        assertFalse(adopter.isTrialPassed());
        assertEquals(0, adopter.getExtensionDays());
        assertFalse(adopter.isReportIncomplete());
    }

    // Тест сеттера lastReportDate
    @Test
    void testSetLastReportDate() {
        LocalDateTime reportDate = LocalDateTime.of(2023, 10, 15,0,0);
        adopter.setLastReportDate(reportDate);
        assertEquals(reportDate, adopter.getLastReportDate());
    }

    // Тест расчёта оставшихся дней испытательного срока
    @Test
    void testCalculateRemainingTrialDays() {
        // Если сегодня 2023-10-10, осталось 20 дней из 30
        LocalDate today = LocalDate.of(2023, 10, 10);
        org.junit.jupiter.api.Assumptions.assumeTrue(today.equals(LocalDate.now()),
                "Тест предполагает, что сегодня 10 октября 2023");
        assertEquals(20, adopter.calculateRemainingTrialDays());
    }

    // Тест проверки истечения испытательного срока
    @Test
    void testIsTrialExpired() {
        // Если сегодня после 30 дней с начала (30 октября), срок истёк
        adopter.setAdoptionStartDate(LocalDate.of(2023, 10, 1));
        LocalDate expiredDate = LocalDate.of(2023, 10, 31);
        org.junit.jupiter.api.Assumptions.assumeTrue(expiredDate.equals(LocalDate.now()),
                "Тест предполагает, что сегодня 31 октября 2023");
        assertTrue(adopter.isTrialExpired());

        // Если до истечения срока, не истёк
        adopter.setAdoptionStartDate(LocalDate.of(2023, 10, 1));
        LocalDate notExpiredDate = LocalDate.of(2023, 10, 15);
        org.junit.jupiter.api.Assumptions.assumeTrue(notExpiredDate.equals(LocalDate.now()),
                "Тест предполагает, что сегодня 15 октября 2023");
        assertFalse(adopter.isTrialExpired());
    }

    // Тест продления испытательного срока
    @Test
    void testExtendTrialPeriod() {
        adopter.extendTrialPeriod(14);
        assertEquals(44, adopter.getTrialPeriodDays()); // 30 + 14
        assertEquals(14, adopter.getExtensionDays());

        // Проверка на отрицательные значения
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            adopter.extendTrialPeriod(-5);
        });
        assertEquals("Cannot extend with negative days", exception.getMessage());
    }

    // Тест обновления статуса по отчётам
    @Test
    void testUpdateStatusByReport() {
        LocalDateTime reportDate = LocalDateTime.of(2023, 10, 15,0,0);
        adopter.updateStatusByReport(reportDate);
        assertEquals(reportDate, adopter.getLastReportDate());

        // Если отчёт неполный и срок истёк — trialPassed = false
        adopter.setReportIncomplete(true);
        adopter.setAdoptionStartDate(LocalDate.of(2023, 9, 1)); // срок истёк
        adopter.updateStatusByReport(LocalDateTime.now());
        assertFalse(adopter.isTrialPassed());

        // Если отчёт полный и срок истёк — trialPassed = true
        adopter.setReportIncomplete(false);
        adopter.updateStatusByReport(LocalDateTime.now());
        assertTrue(adopter.isTrialPassed());
    }

    // Тест toString
    @Test
    void testToString() {
        String toStringResult = adopter.toString();
        assertTrue(toStringResult.contains("userId='user123'"));
        assertTrue(toStringResult.contains("shelterType='кошки'"));
        assertTrue(toStringResult.contains("adoptionStartDate=" + LocalDate.of(2023, 10, 1)));
        assertTrue(toStringResult.contains("trialPeriodDays=30"));
        assertTrue(toStringResult.contains("trialPassed=false"));
    }

    // Тест сеттера extensionDays с валидацией
    @Test
    void testSetExtensionDays() {
        adopter.setExtensionDays(7);
        assertEquals(7, adopter.getExtensionDays());

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            adopter.setExtensionDays(-3);
        });
        assertEquals("Extension days cannot be negative", exception.getMessage());
    }

}
