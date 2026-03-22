package com.Homework.AnimalShelter.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Bot  {

    private Map<String, UserSession> sessions = new HashMap<>();
    private ShelterInfoProvider shelterInfoProvider = new ShelterInfoProvider();
    private ReportHandler reportHandler = new ReportHandler();
    private AdoptionManager adoptionManager = new AdoptionManager();

    public String processMessage(String userId, String message) {
        UserSession session = sessions.getOrDefault(userId, new UserSession());

        String response;

        switch (session.getCurrentStage()) {
            case "0":
                response = welcomeUser(session);
                break;
            case "1":
                response = handleStage1(session, message);
                break;
            case "2":
                response = handleMainMenu(session, message);
                break;
            case "3":
                response = handleContactCollection(session, message);
                break;
            case "4":
                response = handleVolunteerRequest(session, message);
                break;
            case "5":
                response = handleAdoptionInfo(session, message);
                if ("cats".equals(session.getShelterType())) {
                    response = handleAdoptionInfo(session, message);
                } else {
                    response = handleAdoptionInfo1(session, message);
                }
                break;
            case "6":
                response = reportHandler.handleDailyReport(session, message);
                break;
            default:
                response = "Начинаем заново. Введите 'привет' для начала.";
                session.setCurrentStage("0");
        }

        sessions.put(userId, session);
        return response;
    }

    private String welcomeUser(UserSession session) {
        session.setCurrentStage("1");
        return "Привет! Я виртуальный помощник приюта для животных.\n\n" +
                "Выберите приют:\n\n" +
                "1️⃣ Приют для кошек\n\n" +
                "2️⃣ Приют для собак";
    }

    private String handleStage1(UserSession session, String message) {
        if (message.equals("1") || message.toLowerCase().contains("кошек")) {
            session.setShelterType("cats");
            session.setCurrentStage("2");
            return showMainMenu(session);
        } else if (message.equals("2") || message.toLowerCase().contains("собак")) {
            session.setShelterType("dogs");
            session.setCurrentStage("2");
            return showMainMenu(session);
        } else {
            return "❎ Пожалуйста, выберите вариант:\n\n" +
                    "1️⃣ Приют для кошек\n\n" +
                    "2️⃣ Приют для собак";
        }
    }

    private String handleContactCollection(UserSession session, String message) {
        session.setUserContacts(message);
        session.setCurrentStage("2");
        return "✅ Ваши контакты успешно сохранены!\n\n" +
                "Чем ещё могу помочь? Выберите пункт меню:\n\n" +
                showMainMenu(session);
    }

    public String showMainMenu(UserSession session) {
        return "Главное меню:\n\n" +
                "1️⃣  Узнать информацию о приюте\n\n" +
                "2️⃣ Как взять животное из приюта\n\n" +
                "3️⃣ Вести отчет о питомце\n\n" +
                "4️⃣ Позвать волонтера\n\n" +
                "5️⃣ Оставить контактные данные\n\n" +
                "0️⃣ Вернутся в начала\n\n" +
                "Введите номер варианта.";
    }

    private String handleMainMenu(UserSession session, String message) {
        switch (message) {
            case "0":
                return welcomeUser(session);
            case "1":
                if ("cats".equals(session.getShelterType())) {
                    session.setCurrentStage("5");
                    return shelterInfoProvider.getCatsShelterInfo();
                } else if ("dogs".equals(session.getShelterType())) {
                    session.setCurrentStage("5");
                    return shelterInfoProvider.getDogsShelterInfo();
                } else {
                    return "Пожалуйста, выберите приют сначала.";
                }
            case "2":
                session.setCurrentStage("5");
                if ("cats".equals(session.getShelterType())) {
                    return showAdoptionMenu(session);
                } else {
                    return showAdoptionMenu1(session);
                }
            case "3":
                session.setCurrentStage("6");
                return showDailyReportForm(session);
            case "4":
                session.setCurrentStage("4");
                session.setVolunteerIssue("Общий запрос на связь с волонтёром");
                return "Пожалуйста, опишите ваш вопрос или ситуацию подробнее — я передам его волонтёру:";
            case "5":
                session.setCurrentStage("3");
                return "Пожалуйста, отправьте ваши контактные данные (телефон и/или email):";
            default:
                return "❎ Пожалуйста, выберите вариант из главного меню.";
        }
    }

    private String showAdoptionMenu(UserSession session) {
        return "Как взять животное из приюта:\n\n" +
                "1️⃣ Правила знакомства с животным\n\n" +
                "2️⃣ Необходимые документы\n\n" +
                "3️⃣ Рекомендации по транспортировке\n\n" +
                "4️⃣ Обустройство дома для щенка/котёнка\n\n" +
                "5️⃣ Обустройство дома для взрослого животного\n\n" +
                "6️⃣ Обустройство для животного с ограниченными возможностями\n\n" +
                "7️⃣ Причины отказа в выдаче животного\n\n" +
                "0️⃣ Назад в главное меню\n\n" +
                "Выберите пункт:";
    }

    private String showAdoptionMenu1(UserSession session) {
        return "Как взять животное из приюта:\n\n" +
                "1️⃣ Правила знакомства с животным\n\n" +
                "2️⃣ Необходимые документы\n\n" +
                "3️⃣ Рекомендации по транспортировке\n\n" +
                "4️⃣ Обустройство дома для щенка/котёнка\n\n" +
                "5️⃣ Обустройство дома для взрослого животного\n\n" +
                "6️⃣ Обустройство для животного с ограниченными возможностями\n\n" +
                "7️⃣ Советы кинолога по первичному общению\n\n" +
                "8️⃣ Рекомендации по кинологам\n\n" +
                "9️⃣ Причины отказа в выдаче животного\n\n" +
                "0️⃣ Назад в главное меню\n\n" +
                "Выберите пункт:";
    }

    private String showDailyReportForm(UserSession session) {
        return "📋 Форма ежедневного отчёта:\n\n" +
                "Пожалуйста, отправьте:\n" +
                "• Фото вашего питомца\n" +
                "• Текст с описанием его состояния и поведения\n\n" +
                "Отправьте фото и текст в одном сообщении или по очереди.";
    }

    private String handleAdoptionInfo(UserSession session, String message) {
        switch (message) {
            case "0":
                session.setCurrentStage("2");
                return showMainMenu(session);
            case "1":
                return shelterInfoProvider.getAcquaintanceRules(session.getShelterType());
            case "2":
                return shelterInfoProvider.getRequiredDocuments(session.getShelterType());
            case "3":
                return shelterInfoProvider.getTransportationTips(session.getShelterType());
            case "4":
                return shelterInfoProvider.getHomeSetupForYoung(session.getShelterType());
            case "5":
                return shelterInfoProvider.getHomeSetupForAdult(session.getShelterType());
            case "6":
                return shelterInfoProvider.getHomeSetupForDisabled();
            case "7":
                return shelterInfoProvider.getRejectionReasons(session.getShelterType());
            default:
                return "Пожалуйста, выберите пункт из меню.\n" + showAdoptionMenu(session);
        }
    }

    private String handleAdoptionInfo1(UserSession session, String message) {
        switch (message) {
            case "0":
                session.setCurrentStage("2");
                return showMainMenu(session);
            case "1":
                return shelterInfoProvider.getAcquaintanceRules(session.getShelterType());
            case "2":
                return shelterInfoProvider.getRequiredDocuments(session.getShelterType());
            case "3":
                return shelterInfoProvider.getTransportationTips(session.getShelterType());
            case "4":
                return shelterInfoProvider.getHomeSetupForYoung(session.getShelterType());
            case "5":
                return shelterInfoProvider.getHomeSetupForAdult(session.getShelterType());
            case "6":
                return shelterInfoProvider.getHomeSetupForDisabled();
            case "7":
                return shelterInfoProvider.getDogTrainingTips();
            case "8":
                return shelterInfoProvider.getRecommendedTrainers();
            case "9":
                return shelterInfoProvider.getRejectionReasons(session.getShelterType());
            default:
                return "Пожалуйста, выберите пункт из меню.\n" + showAdoptionMenu(session);
        }
    }


    private AdoptionManager manager = new AdoptionManager();
    private String handleVolunteerRequest(UserSession session, String message) {
        String issue = session.getVolunteerIssue();
        String userContacts = session.getUserContacts();

        String volunteerMessage = String.format(
                "\n\n**НОВЫЙ ЗАПРОС ОТ ПОЛЬЗОВАТЕЛЯ**\n" +
                        "Приют: %s\n" +
                        "Проблема: %s\n" +
                        "Сообщение пользователя: %s\n" +
                        "Контакты пользователя: %s\n\n",
                (session.getShelterType().equals("cats") ? "Кошки" : "Собаки"),
                issue,
                message,
                (userContacts != null ? userContacts : "не указаны"));

        System.out.println(volunteerMessage); // В реальной системе — отправка в чат/email

        // Очищаем временные данные и возвращаемся во главное меню
        session.setVolunteerIssue(null);
        session.setCurrentStage("2");

        return "🤝 Волонтёр получил ваш запрос и свяжется с вами в ближайшее время.\n\n" +
                showMainMenu(session);
    }

    // Метод обработки входящих сообщений
    public String processMessage(String userId, String message, Optional<String> photoPath) {
        Adopter user = manager.getUser(userId);
        if (user == null) {
            return "Пользователь не найден. Пожалуйста, обратитесь к волонтеру для регистрации.";
        }

        if (message.equalsIgnoreCase("/report")) {
            return "Пожалуйста, отправьте отчет, заполнив форму или ответив на вопросы.";
        }

        if (photoPath.isPresent() || (message != null && !message.trim().isEmpty())) {
            return handleReport(user, message, photoPath);
        }

        return "Пожалуйста, отправьте отчет или используйте команду /report.";
    }

    private String handleReport(Adopter user, String text, Optional<String> photoPath) {
        boolean hasPhoto = photoPath.isPresent();
        boolean hasText = text != null && !text.trim().isEmpty();

        if (!hasPhoto && !hasText) {
            return "Пожалуйста, отправьте фото и описание.";
        }
        if (hasPhoto && !hasText) {
            return "Пожалуйста, опишите состояние животного.";
        }
        if (!hasPhoto && hasText) {
            return "Пожалуйста, отправьте фото животного.";
        }

        // Сохраняем отчет
        manager.saveReport(user, text, "здоровье и самочувствие", "поведение", photoPath.get());

        // Проверка полноты отчета
        if (text.length() < 50) {
            sendWarning(user);
        }

        // Обновляем дату последнего отчета
        user.setLastReportDate(LocalDate.now());

        // Проверка срока
        manager.checkTrialStatus(user);

        return "Спасибо за отчет!";
    }

    private void sendWarning(Adopter user) {
        System.out.println("Отправка предупреждения пользователю: " + user.getUserId());
        // Реализовать отправку сообщения
    }

    // Метод для запуска проверки пропущенных отчетов
    public void runDailyCheck() {
        manager.checkReports();
    }

}
