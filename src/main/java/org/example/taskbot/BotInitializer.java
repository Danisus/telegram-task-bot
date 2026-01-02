package org.example.taskbot;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.taskbot.bot.TaskBot;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@RequiredArgsConstructor
public class BotInitializer {

    private final TaskBot taskBot;

    @PostConstruct
    public void init() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(taskBot);
            System.out.println("Бот успешно зарегистрирован и запущен!");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}