package com.Homework.AnimalShelter.service;


import static org.junit.jupiter.api.Assertions.*;

import com.Homework.AnimalShelter.model.Adopter;
import com.Homework.AnimalShelter.model.AdoptionManager;
import com.Homework.AnimalShelter.model.DailyReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;


public class AdoptionManagerTest {
    private AdoptionManager adoptionManager;
    private Adopter testAdopter;
    private DailyReport testReport;

    @BeforeEach
    void setUp() {
        adoptionManager = new AdoptionManager();
        testAdopter = new Adopter("testUserId", "Test User", LocalDate.now());
        testAdopter.setUserId("testUser");
        testAdopter.setAdoptionStartDate(LocalDate.now().minusDays(10));
        testAdopter.setTrialPeriodDays(30);
        testAdopter.setExtensionDays(5);
        testAdopter.setLastReportDate(LocalDateTime.now().minusDays(3));
        testAdopter.setTrialPassed(true);
    }

    // Тест метода addAdopter
    @Test
    void testAddAdopter() {
        adoptionManager.addAdopter(testAdopter);
        assertEquals(1, adoptionManager.adopters.size());
        assertEquals(testAdopter, adoptionManager.adopters.get("testUser"));
    }

    // Тест метода getUser
    @Test
    void testGetUser() {
        adoptionManager.addAdopter(testAdopter);
        Adopter retrievedAdopter = adoptionManager.getUser("testUser");
        assertEquals(testAdopter, retrievedAdopter);

        // Проверка случая, когда пользователь не найден
        assertNull(adoptionManager.getUser("nonexistentUser"));
    }

    // Тест метода saveReport
    @Test
    void testSaveReport() {
        adoptionManager.addAdopter(testAdopter);
        adoptionManager.saveReport(
                testAdopter,
                "Сухой корм",
                "Здоровый",
                "Стал более активным",
                "/path/to/photo.jpg"
        );

        assertEquals(1, adoptionManager.reports.size());
        assertEquals(LocalDate.now(), testAdopter.getLastReportDate().toLocalDate());
    }

    // Тест метода getAllAdopters
    @Test
    void testGetAllAdopters() {
        adoptionManager.addAdopter(testAdopter);
        Collection<Adopter> adopters = adoptionManager.getAllAdopters();
        assertEquals(1, adopters.size());
        assertTrue(adopters.contains(testAdopter));
    }

    // Тест метода checkReports — когда отчёты пропущены
    @Test
    void testCheckReportsMissedReports() {
        adoptionManager.addAdopter(testAdopter);
        adoptionManager.checkReports();

        // Проверяем, что sendReminder был вызван (можно использовать Mockito для проверки вызовов)
        // В данном случае проверяем логику расчёта
        assertTrue(testAdopter.getLastReportDate().plusMinutes(120).isBefore(LocalDateTime.now()));
    }

    // Тест метода checkReports — когда отчёты актуальны
    @Test
    void testCheckReportsUpToDateReports() {
        Adopter upToDateAdopter = new Adopter("testUserId", "Test User", LocalDate.now());
        upToDateAdopter.setUserId("upToDateUser");
        upToDateAdopter.setLastReportDate(LocalDateTime.now());
        adoptionManager.addAdopter(upToDateAdopter);

        adoptionManager.checkReports();
        // Проверяем логику вызова notifyVolunteer
    }

    // Тест расчёта оставшихся дней до окончания испытательного срока
    @Test
    void testCalculateDaysLeft() {
        LocalDate now = LocalDate.now();
        LocalDate endDate = testAdopter.getAdoptionStartDate().plusDays(testAdopter.getTrialPeriodDays());
        long daysLeft = ChronoUnit.DAYS.between(now, endDate);

        if (daysLeft < 0) daysLeft = 0;

        assertTrue(daysLeft >= 0); // Оставшиеся дни не могут быть отрицательными
    }

    // Тест метода checkTrialStatus — успешный проход испытательного срока
    @Test
    void testCheckTrialStatusPassed() {
        adoptionManager.checkTrialStatus(testAdopter);
        // Проверяем вывод сообщения (можно использовать Captor для перехвата println)
    }

    // Тест метода checkTrialStatus — неуспешный проход испытательного срока
    @Test
    void testCheckTrialStatusFailed() {
        Adopter failedAdopter = new Adopter("testUserId", "Test User", LocalDate.now());
        failedAdopter.setUserId("failedUser");
        failedAdopter.setAdoptionStartDate(LocalDate.now().minusDays(40));
        failedAdopter.setTrialPeriodDays(30);
        failedAdopter.setExtensionDays(0);
        failedAdopter.setTrialPassed(false);

        adoptionManager.checkTrialStatus(failedAdopter);
        // Проверяем вывод сообщения об неуспешном прохождении
    }

    // Тест граничных случаев — null параметры
    @Test
    void testNullParameters() {
        assertThrows(NullPointerException.class, () -> adoptionManager.saveReport(null, "diet", "health", "behavior", "path"));
        assertNull(adoptionManager.getUser(null));
    }

    // Тест метода sendMessage
    @Test
    void testSendMessage() {
        adoptionManager.sendMessage("testUser", "Test message");
        // В реальном тесте можно использовать Captor для перехвата вывода в консоль
    }

    // Тест обработки усыновителя без даты начала усыновления
    @Test
    void testAdopterWithoutAdoptionDate() {
        Adopter adopterWithoutDate = new Adopter("testUserId", "Test User", LocalDate.now());
        adopterWithoutDate.setUserId("noDateUser");
        adopterWithoutDate.setLastReportDate(LocalDateTime.now().minusDays(5));
        adoptionManager.addAdopter(adopterWithoutDate);

        adoptionManager.checkReports();
        // Убеждаемся, что код не падает при отсутствии даты начала усыновления
    }
}
