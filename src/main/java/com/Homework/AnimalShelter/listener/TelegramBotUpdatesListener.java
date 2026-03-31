package com.Homework.AnimalShelter.listener;

import com.Homework.AnimalShelter.model.Bot;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener  {

    // Логгер для вывода информации и отладки
    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    // Экземпляр класса Bot для обработки логики диалога
    private Bot bot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
        bot = new Bot();
    }
    @Override
    public int process(List<Update> updates) {
        for (Update update : updates) {
            logger.info("Обработка обновления: {}", update);

            // Проверка наличия текстового сообщения
            if (update.message() != null && update.message().text() != null) {
                String messageText = update.message().text();
                Long chatId = update.message().chat().id();
                String userId = chatId.toString();

                // Обработка сообщения через логику бота
                String response = bot.processMessage(userId, messageText);

                // Отправка ответа пользователю
                sendMessage(chatId, response);
            }
        }

        // Возвращаем статус, подтверждающий, что все обновления обработаны
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void sendMessage(Long chatId, String text) {
        telegramBot.execute(new SendMessage(chatId, text));
    }
}

