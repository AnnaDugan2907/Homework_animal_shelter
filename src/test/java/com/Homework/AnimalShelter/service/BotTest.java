package com.Homework.AnimalShelter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class BotTest {
    private Bot bot;
    private String userId = "user123";

    @BeforeEach
    void setUp() {
        bot = new Bot();
    }

    // Тест стартового сообщения
    @Test
    void testWelcomeMessage() {
        String response = bot.processMessage(userId, "привет");

        assertThat(response)
                .as("Приветственное сообщение должно содержать выбор приюта")
                .contains("Привет! Я виртуальный помощник приюта для животных.")
                .contains("Приют для кошек")
                .contains("Приют для собак");
    }

    // Тест выбора приюта для кошек
    @Test
    void testSelectCatsShelter() {
        // Сначала получаем приветственное сообщение
        bot.processMessage(userId, "привет");

        // Выбираем приют для кошек
        String response = bot.processMessage(userId, "1");

        assertThat(response)
                .as("После выбора приюта для кошек должно отображаться главное меню")
                .contains("Главное меню")
                .contains("Узнать информацию о приюте")
                .contains("Как взять животное из приюта");
    }

    // Тест выбора приюта для собак
    @Test
    void testSelectDogsShelter() {
        bot.processMessage(userId, "привет");
        String response = bot.processMessage(userId, "2");

        assertThat(response)
                .as("После выбора приюта для собак должно отображаться главное меню")
                .contains("Главное меню");
    }

    // Тест некорректного выбора приюта
    @Test
    void testInvalidShelterSelection() {
        bot.processMessage(userId, "привет");
        String response = bot.processMessage(userId, "3");

        assertThat(response)
                .as("При неверном выборе должен быть запрос повторить выбор")
                .contains("Пожалуйста, выберите вариант");
    }

    // Тест перехода в главное меню из любого состояния
    @Test
    void testReturnToMainMenu() {
        // Имитируем выбор приюта
        bot.processMessage(userId, "привет");
        bot.processMessage(userId, "1"); // кошки

        // Переходим в главное меню через пункт 0
        String response = bot.processMessage(userId, "0");

        assertThat(response)
                .as("Переход в главное меню должен показывать приветственное сообщение")
                .contains("Привет! Я виртуальный помощник приюта для животных.");
    }

    // Тест получения информации о приюте (кошки)
    @Test
    void testGetShelterInfoForCats() {
        // Проходим путь до главного меню
        bot.processMessage(userId, "привет");
        bot.processMessage(userId, "1"); // выбираем кошек

        // Запрашиваем информацию о приюте
        String response = bot.processMessage(userId, "1");

        assertThat(response)
                .as("Должна отображаться информация о приюте для кошек")
                .doesNotContain("Пожалуйста, выберите приют сначала");
    }

    // Тест меню взятия животного (кошки)
    @Test
    void testAdoptionMenuForCats() {
        bot.processMessage(userId, "привет");
        bot.processMessage(userId, "1"); // кошки
        String response = bot.processMessage(userId, "2"); // как взять животное

        assertThat(response)
                .as("Должно отображаться меню процедур для кошек")
                .contains("Правила знакомства с животным")
                .contains("Необходимые документы")
                .contains("Обустройство дома для щенка/котёнка");
    }

    // Тест меню взятия животного (собаки)
    @Test
    void testAdoptionMenuForDogs() {
        bot.processMessage(userId, "привет");
        bot.processMessage(userId, "2"); // собаки
        String response = bot.processMessage(userId, "2"); // как взять животное

        assertThat(response)
                .as("Должно отображаться меню процедур для собак")
                .contains("Советы кинолога по первичному общению")
                .contains("Рекомендации по кинологам");
    }

    // Тест формы ежедневного отчёта
    @Test
    void testDailyReportForm() {
        bot.processMessage(userId, "привет");
        bot.processMessage(userId, "1"); // кошки
        String response = bot.processMessage(userId, "3"); // вести отчёт

        assertThat(response)
                .as("Должно отображаться сообщение с формой отчёта")
                .contains("📋 Форма ежедневного отчёта:")
                .contains("• Фото вашего питомца")
                .contains("• Текст с описанием его состояния и поведения");
    }

    // Тест запроса волонтёра
    @Test
    void testVolunteerRequest() {
        bot.processMessage(userId, "привет");
        bot.processMessage(userId, "1"); // кошки
        String response = bot.processMessage(userId, "4"); // позвать волонтёра

        assertThat(response)
                .as("Должен запрашиваться детальный вопрос для волонтёра")
                .contains("опишите ваш вопрос или ситуацию подробнее");
    }

    // Тест сохранения контактных данных
    @Test
    void testContactCollection() {
        bot.processMessage(userId, "привет");
        bot.processMessage(userId, "1"); // кошки
        bot.processMessage(userId, "5"); // оставить контакты

        String response = bot.processMessage(userId, "+79991234567, email@example.com");

        assertThat(response)
                .as("Контакты должны успешно сохраняться")
                .contains("Ваши контакты успешно сохранены")
                .contains("Чем ещё могу помочь");
    }

    // Тест обработки некорректного ввода в главном меню
    @Test
    void testInvalidMainMenuInput() {
        bot.processMessage(userId, "привет");
        bot.processMessage(userId, "1"); // кошки

        String response = bot.processMessage(userId, "999");

        assertThat(response)
                .as("При неверном вводе в главном меню должно быть предупреждение")
                .contains("Пожалуйста, выберите вариант из главного меню");
    }

    // Тест полного цикла: выбор приюта → главное меню → информация о приюте
    @Test
    void testFullFlow() {
        // Шаг 1: приветствие
        String welcome = bot.processMessage(userId, "привет");
        assertThat(welcome).contains("Привет! Я виртуальный помощник приюта для животных.");

        // Шаг 2: выбор приюта для кошек
        String shelterSelection = bot.processMessage(userId, "1");
        assertThat(shelterSelection).contains("Главное меню");

        // Шаг 3: информация о приюте
        String shelterInfo = bot.processMessage(userId, "1");
        assertThat(shelterInfo).isNotEmpty();

        // Шаг 4: возврат в главное меню
        String mainMenu = bot.processMessage(userId, "0");
        assertThat(mainMenu).contains("Главное меню");
    }

    // Тест обработки null-сообщений
    @Test
    void testNullMessageHandling() {
        String result = bot.processMessage(userId, null);

        assertThat(result)
                .as("Бот должен корректно обрабатывать null-сообщения")
                .contains("Начинаем заново");
    }

    // Тест обработки пустого сообщения
    @Test
    void testEmptyMessageHandling() {
        String result = bot.processMessage(userId, "");

        assertThat(result)
                .as("Бот должен корректно обрабатывать пустые сообщения")
                .contains("Начинаем заново");
    }
}
