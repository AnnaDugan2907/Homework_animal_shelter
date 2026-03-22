package com.Homework.AnimalShelter.model;

public class ReportHandler {

    private String showMainMenu(UserSession session) {
        return "Главное меню:\n\n" +
                "1️⃣  Узнать информацию о приюте\n\n" +
                "2️⃣ Как взять животное из приюта\n\n" +
                "3️⃣ Вести отчет о питомце\n\n" +
                "4️⃣ Позвать волонтера\n\n" +
                "5️⃣ Оставить контактные данные\n\n" +
                "0️⃣ Вернутся в начала\n\n" +
                "Введите номер варианта.";
    }

    public String handleDailyReport(UserSession session, String message) {
        boolean hasPhoto = message.toLowerCase().contains("photo");
        boolean hasText = message != null && !message.trim().isEmpty() && !message.toLowerCase().contains("photo");

        // Если сообщение содержит и "photo", и текст
        if (message.toLowerCase().contains("photo") && message.replace("photo", "").trim().length() > 0) {
            hasPhoto = true;
            hasText = true;
        }

        session.setLastReportHadPhoto(hasPhoto);
        session.setLastReportHadText(hasText);

        if (hasPhoto && hasText) {
            session.setPoorReportCount(0);
            session.setCurrentStage("2");
            return "✅ Отчёт принят! Спасибо за ответственное отношение.\n\n" + showMainMenu(session);
        } else if (hasPhoto) {
            return "📸 Получил фото! Теперь, пожалуйста, добавьте текст с описанием состояния питомца.";
        } else {
            return "📝 Получил текст! Теперь, пожалуйста, отправьте фото вашего питомца.";
        }
    }

    public void checkReportQuality(UserSession session) {
        if (!session.isLastReportHadPhoto() || !session.isLastReportHadText()) {
            session.setPoorReportCount(session.getPoorReportCount() + 1);
            if (session.getPoorReportCount() >= 3) {
                sendPoorReportWarning(session);
            }
        } else {
            session.setPoorReportCount(0);
        }
    }

    public void sendPoorReportWarning(UserSession session) {
        System.out.println("⚠️ Предупреждение волонтёру: усыновитель заполняет отчёты некачественно.");
        // В реальной системе — отправка сообщения
    }

    public void sendTrialReminder(UserSession session, int daysLeft) {
        String reminder = String.format(
                ("⏱️ Напоминаем: до окончания испытательного срока осталось %d дней.\n\n" +
                        "Пожалуйста, продолжайте отправлять ежедневные отчёты о состоянии питомца."),
                daysLeft);
        System.out.println("Напоминание пользователю: " + reminder);
        // В реальной системе — отправка сообщения
    }

}
