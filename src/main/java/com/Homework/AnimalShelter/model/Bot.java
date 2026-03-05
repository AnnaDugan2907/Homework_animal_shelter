package com.Homework.AnimalShelter.model;


import java.util.HashMap;
import java.util.Map;

public class Bot {

    // Хранение пользовательских сессий по их уникальному идентификатору
    private Map<String, UserSession> sessions = new HashMap<>();


    public String processMessage(String userId, String message) {

        // Получение текущей сессии пользователя или создание новой, если нет
        UserSession session = sessions.getOrDefault(userId, new UserSession());

        String response;

        switch (session.getCurrentStage()) {
            case 0:
                // Приветствие и выбор приюта
                response = welcomeUser(session);
                break;
            case 1:
                // Обработка выбора приюта
                response = handleStage1(session, message);
                break;
            case 2:
                // Обработка главного меню
                response = handleMainMenu(session, message);
                break;

            default:
                // Если что-то пошло не так, начинаем заново
                response = "Начинаем заново. Введите 'привет' для начала.";
                session.setCurrentStage(0);
        }
        // Обновление сессии пользователя
        sessions.put(userId, session);
        return response;
    }

    private String welcomeUser(UserSession session) {

        // Установка этапа 1 (выбор приюта)
        session.setCurrentStage(1);
        return "Привет! Я виртуальный помощник приюта для животных.\n\n" +
                "Выберите приют:\n" +
                "1️⃣ Приют для кошек\n" +
                "2️⃣ Приют для собак";
    }

    private String handleStage1(UserSession session, String message) {
        if (message.equals("1") || message.toLowerCase().contains("кошек")) {
            // Пользователь выбрал кошек
            session.setShelterType("cats");
            session.setCurrentStage(2);
            return showMainMenu(session);
        } else if (message.equals("2") || message.toLowerCase().contains("собак")) {
            // Пользователь выбрал
            session.setShelterType("dogs");
            session.setCurrentStage(2);
            return showMainMenu(session);
        } else {
            return "Пожалуйста, выберите вариант:\n1️⃣ Приют для кошек\n2️⃣ Приют для собак";
        }
    }

    private String showMainMenu(UserSession session) {
        return "Главное меню:\n" +
                "1. Узнать информацию о приюте\n" +
                "2. Как взять животное из приюта\n" +
                "3. Вести отчет о питомце\n" +
                "4. Позвать волонтера\n" +
                "0. Вернутся в начала\n" +
                "Введите номер варианта.";
    }

    private String handleMainMenu(UserSession session, String message) {

        switch (message) {

            case "0":
                // Возврат к приветствию
                return welcomeUser(session);

            case "1":
                // Информация о приюте
                return "Информация о приюте: ...";
            case "2":
                // Инструкция по взятию животного
                return "Для получения животного обратитесь к волонтеру ...";
            case "3":
                // Ведение отчета о питомце
                return "Введите отчет о питомце.";
            case "4":
                // Вызов волонтера
                return "Волонтер вызван. Ожидайте.";

            default:
                // Некорректный ввод
                return "Пожалуйста, выберите вариант из меню.";
        }
    }
}
