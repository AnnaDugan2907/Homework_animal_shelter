package com.Homework.AnimalShelter.service;

import com.Homework.AnimalShelter.model.ReportHandler;
import com.Homework.AnimalShelter.model.UserSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.*;

public class ReportHandlerTest {
    private ReportHandler reportHandler;
    private UserSession testSession;

    @BeforeEach
    void setUp() {
        reportHandler = new ReportHandler();
        testSession = new UserSession();
    }

    // Тест отображения главного меню
    @Test
    void testShowMainMenu() {
        String menu = reportHandler.showMainMenu(testSession);
        System.out.println("Меню: " + menu);
        assertTrue("Проверка наличия пункта 'Главное меню' в меню", menu.contains("Главное меню"));
        assertTrue("Проверка наличия пункта 'Узнать информацию о приюте' в меню", menu.contains("1️⃣  Узнать информацию о приюте"));
        assertTrue("Проверка наличия пункта 'Вернутся в начала' в меню", menu.contains("0️⃣ Вернутся в начала"));
        assertTrue("Проверка наличия пункта 'Введите номер варианта.' в меню", menu.endsWith("Введите номер варианта."));

    }

    // Тест обработки отчёта с фото и текстом (успешный случай)
    @Test
    void testHandleDailyReportWithPhotoAndText() {
        String message = "Photo: attached, Pet is active and eating well";
        String result = reportHandler.handleDailyReport(testSession, message);

        assertTrue("Проверка наличия пункта 'Отчёт принят!'", result.contains("✅ Отчёт принят!"));
        assertTrue("Проверка наличия пункта 'Была фотография'", testSession.isLastReportHadPhoto());
        assertTrue("Проверка наличия пункта 'Был Текст'", testSession.isLastReportHadText());
        assertEquals("2", testSession.getCurrentStage());
        assertEquals(0, testSession.getPoorReportCount());
    }

    // Тест обработки отчёта только с фото attached
    @Test
    void testHandleDailyReportWithOnlyPhoto() {
        String message = "photo";
        String result = reportHandler.handleDailyReport(testSession, message);

        System.out.println("Результат обработки: " + result);

        assertTrue("Проверка наличия пункта 'Получил фото!'", result.contains("📸 Получил фото!"));
        assertTrue("Проверка наличия пункта 'Была фотография'", testSession.isLastReportHadPhoto());
        assertFalse("Проверка наличия пункта 'Был Текст'", testSession.isLastReportHadText());
    }

    // Тест обработки отчёта только с текстом
    @Test
    void testHandleDailyReportWithOnlyText() {
        String message = "Pet is active and eating well";
        String result = reportHandler.handleDailyReport(testSession, message);

        assertTrue("Проверка наличия пункта 'Получил текст!'", result.contains("📝 Получил текст!"));
        assertFalse("Проверка наличия пункта 'Была фотография'", testSession.isLastReportHadPhoto());
        assertTrue("Проверка наличия пункта 'Был Текст'", testSession.isLastReportHadText());
    }

    // Тест проверки качества отчёта (некачественный отчёт — нет фото)
    @Test
    void testCheckReportQualityWithoutPhoto() {
        testSession.setLastReportHadPhoto(false);
        testSession.setLastReportHadText(true);
        testSession.setPoorReportCount(1);

        reportHandler.checkReportQuality(testSession);

        assertEquals(2, testSession.getPoorReportCount()); // Увеличился на 1
        assertFalse("Проверка наличия пункта 'Была фотография'", testSession.isLastReportHadPhoto());
    }

    // Тест проверки качества отчёта (некачественный отчёт — нет текста)
    @Test
    void testCheckReportQualityWithoutText() {
        testSession.setLastReportHadPhoto(true);
        testSession.setLastReportHadText(false);
        testSession.setPoorReportCount(1);

        reportHandler.checkReportQuality(testSession);

        assertEquals(2, testSession.getPoorReportCount()); // Увеличился на 1
        assertFalse("Проверка наличия пункта 'Был Текст'", testSession.isLastReportHadText());
    }

    // Тест проверки качества отчёта (качественный отчёт — сбрасывает счётчик)
    @Test
    void testCheckReportQualityWithGoodReport() {
        testSession.setLastReportHadPhoto(true);
        testSession.setLastReportHadText(true);
        testSession.setPoorReportCount(2);

        reportHandler.checkReportQuality(testSession);

        assertEquals(0, testSession.getPoorReportCount()); // Сбросился до 0
        assertTrue("Проверка наличия пункта 'Была фотография'", testSession.isLastReportHadPhoto());
        assertTrue("Проверка наличия пункта 'Был Текст'", testSession.isLastReportHadText());
    }

    // Тест отправки предупреждения волонтёру (при 3 некачественных отчётах)
    @Test
    void testSendPoorReportWarning() {
        testSession.setLastReportHadPhoto(false);
        testSession.setLastReportHadText(false);
        testSession.setPoorReportCount(2);

        // Симулируем 3 некачественных отчёта
        reportHandler.checkReportQuality(testSession); // 3-й некачественный отчёт

        // Проверяем, что предупреждение отправлено (вывод в консоль)
        // В реальной системе можно перехватить вывод или использовать мок-объект
    }

    // Тест отправки напоминания о испытательном сроке
    @Test
    void testSendTrialReminder() {
        int daysLeft = 5;
        reportHandler.sendTrialReminder(testSession, daysLeft);

        // Проверяем вывод напоминания (в реальной системе — проверка отправки сообщения)
        // Можно использовать мок-объект для проверки отправки
    }

    // Тест обработки пустого сообщения
    @Test
    void testHandleDailyReportWithEmptyMessage() {
        String message = "";
        String result = reportHandler.handleDailyReport(testSession, message);

        assertFalse("Проверка наличия пункта 'Была фотография'", testSession.isLastReportHadPhoto());
        assertFalse("Проверка наличия пункта 'Был Текст'", testSession.isLastReportHadText());
        assertTrue("Проверка наличия пункта 'Получил текст!'", result.contains("📝 Получил текст!")); // или другой ожидаемый текст
    }

    // Тест обработки null-сообщения
    @Test
    void testHandleDailyReportWithNullMessage() {

        String result = reportHandler.handleDailyReport(testSession, null);

        assertFalse("Проверка наличия пункта 'Была фотография'", testSession.isLastReportHadPhoto());
        assertFalse("Проверка наличия пункта 'Был Текст'", testSession.isLastReportHadText());
        // Проверяем, что метод не крашится и возвращает осмысленное сообщение
    }
}
