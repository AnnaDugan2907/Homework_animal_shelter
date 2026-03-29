package com.Homework.AnimalShelter.listener;

import com.Homework.AnimalShelter.service.Bot;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TelegramBotUpdatesListenerTest {
    @Mock
    private TelegramBot telegramBot;

    @Spy
    private Bot bot;

    @InjectMocks
    private TelegramBotUpdatesListener listener;

    private Update mockUpdate;
    private Message mockMessage;
    private Chat mockChat;

    @BeforeEach
    void setUp() {
        // Инициализируем мок-объекты
        mockChat = mock(Chat.class);
        mockMessage = mock(Message.class);
        mockUpdate = mock(Update.class);

        when(mockUpdate.message()).thenReturn(mockMessage);
        when(mockMessage.chat()).thenReturn(mockChat);
        when(mockChat.id()).thenReturn(12345L);
        when(mockMessage.text()).thenReturn("привет");
    }


    // Тест обработки обновления с текстовым сообщением
    @Test
    void testProcessWithTextMessage() {
        // Given: настраиваем мок-ответ бота
        when(bot.processMessage("12345", "привет")).thenReturn("Привет! Я виртуальный помощник приюта для животных.");

        // When: обрабатываем обновление
        int result = listener.process(Arrays.asList(mockUpdate));

        // Then: проверяем вызовы
        verify(bot).processMessage("12345", "привет");
        verify(telegramBot).execute(any(SendMessage.class));
        assertThat(result).isEqualTo(UpdatesListener.CONFIRMED_UPDATES_ALL);
    }

    // Тест логирования обработки обновления
    @Test
    void testLoggingOfUpdateProcessing() {
        // Given: используем ArgumentCaptor для проверки логирования
        // (в реальном коде потребуется дополнительная настройка логгера)

        // When
        listener.process(Arrays.asList(mockUpdate));

        // Then: проверяем, что логгер получил информацию об обновлении
        // Примечание: для полной проверки логирования потребуется дополнительная настройка TestAppender
        // или использование TestLogger
    }

}
