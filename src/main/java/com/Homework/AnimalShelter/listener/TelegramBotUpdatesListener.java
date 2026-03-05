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
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    private Bot bot; // создаем поле для экземпляра Bot

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
        bot = new Bot(); // инициализация экземпляра
    }
//
//    @PostConstruct
//    public void init() {
//        telegramBot.setUpdatesListener(this);
//    }

//    @Override
//    public int process(List<Update> updates) {
//        updates.forEach(update -> {
//            logger.info("Processing update: {}", update);
//            // Process your updates here
//        });
//        return UpdatesListener.CONFIRMED_UPDATES_ALL;
//    }

//    @Override
//    public int process(List<Update> updates) {
//        updates.forEach(update -> {
//            logger.info("Processing update: {}", update);
//
//            if (update.message() != null && update.message().text() != null) {
//                String messageText = update.message().text();
//                Long chatId = update.message().chat().id();
//
//                if (messageText.equals("/start")) {
//                    sendMessage(chatId, "Добро пожаловать! Я ваш помощник.");
//                } else {
//                    // Обработка других сообщений
//                    // Например, вызов метода обработки диалогов
//                    // String response = processUserMessage(chatId, messageText);
//                    // sendMessage(chatId, response);
//                }
//            }
//        });
//        return UpdatesListener.CONFIRMED_UPDATES_ALL;
//    }
//
//    private void sendMessage(Long chatId, String text) {
//        telegramBot.execute(new SendMessage(chatId, text));
//    }


    @Override
    public int process(List<Update> updates) {
        for (Update update : updates) {
            logger.info("Обработка обновления: {}", update);

            if (update.message() != null && update.message().text() != null) {
                String messageText = update.message().text();
                Long chatId = update.message().chat().id();

                // Используем chatId как userId
                String userId = chatId.toString();

                // Обработка сообщения через ваш класс Bot
                String response = bot.processMessage(userId, messageText);

                // Отправка ответа пользователю
                sendMessage(chatId, response);
            }
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void sendMessage(Long chatId, String text) {
        telegramBot.execute(new SendMessage(chatId, text));
    }
}

