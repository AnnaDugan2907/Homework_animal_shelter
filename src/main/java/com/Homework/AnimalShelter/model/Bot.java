package com.Homework.AnimalShelter.model;

import com.pengrad.telegrambot.model.Update;

import java.util.HashMap;
import java.util.Map;

public class Bot {
    private Map<String, UserSession> sessions = new HashMap<>();

    public String processMessage(String userId, String message) {
        UserSession session = sessions.getOrDefault(userId, new UserSession());

        String response;

        switch (session.getCurrentStage()) {
            case 0:
                response = welcomeUser(session);
                break;
            case 1:
                response = handleStage1(session, message);
                break;
            case 2:
                response = handleMainMenu(session, message);
                break;

            default:
                response = "Начинаем заново. Введите 'привет' для начала.";
                session.setCurrentStage(0);
        }

        sessions.put(userId, session);
        return response;
    }

    private String welcomeUser(UserSession session) {
        session.setCurrentStage(1);
        return "Привет! Я виртуальный помощник приюта для животных.\n\n" +
                "Выберите приют:\n" +
                "1️⃣ Приют для кошек\n" +
                "2️⃣ Приют для собак";
    }

    private String handleStage1(UserSession session, String message) {
        if (message.equals("1") || message.toLowerCase().contains("кошек")) {
            session.setShelterType("cats");
            session.setCurrentStage(2);
            return showMainMenu(session);
        } else if (message.equals("2") || message.toLowerCase().contains("собак")) {

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
                return welcomeUser(session);

            case "1":
                return "Информация о приюте: ..."; // добавьте конкретное сообщение
            case "2":
                return "Для получения животного обратитесь к волонтеру ...";
            case "3":
                return "Введите отчет о питомце.";
            case "4":
                return "Волонтер вызван. Ожидайте.";

            default:
                return "Пожалуйста, выберите вариант из меню.";
        }
    }
}
