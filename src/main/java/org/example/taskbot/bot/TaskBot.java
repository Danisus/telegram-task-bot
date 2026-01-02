package org.example.taskbot.bot;

import lombok.RequiredArgsConstructor;
import org.example.taskbot.entities.Task;
import org.example.taskbot.services.TaskService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskBot extends TelegramLongPollingBot {

    private final TaskService taskService;
    private final String botToken = "8486016454:AAFk5zbUczt-RPvlVQU7QLupGI8JC4ZwgcU";
    private final String botUsername = "TaskBot";

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            long userId = update.getMessage().getFrom().getId();
            long chatId = update.getMessage().getChat().getId();

            String response = processCommand(userId, message);
            sendMessage(chatId, response);
        }
    }


    private String processCommand(long userId, String text) {
        if (text.equals("/start")) {
            return "Привет! Я бот для задач.\n" +
                    "Команды:\n" +
                    "/add <задача> — добавить задачу\n" +
                    "/list — показать все задачи\n" +
                    "/done <номер> — завершить задачу\n" +
                    "/delete <номер> — удалить задачу";
        }

        if(text.startsWith("/add ")){
            String description = text.substring(5).trim();
            if (description.isEmpty()) {
                return "Пожалуйста, укажи текст задачи после /add";
            }
            taskService.addTask(userId, description);
            return "задача добавлена";
        }

        if(text.equals("/list")){
            List<Task> tasks = taskService.getTasks(userId);
            if (tasks.isEmpty()) {
                return "У тебя нет задач. Добавь первую с помощью /add <текст>";
            }
            StringBuilder sb = new StringBuilder("Твои задачи:\n");
            int index = 1;
            for (Task task : tasks) {
                String status = task.isCompleted() ? "✓" : "○";
                sb.append(index++).append(". ").append(status).append(" ").append(task.getDescription()).append("\n");
            }
            return sb.toString();
        }

        if (text.startsWith("/done ")){
            try {
                int num = Integer.parseInt(text.substring(6).trim());
                taskService.completeTask(userId, num);
                return "Задача выполнена";
            } catch (NumberFormatException e) {
                return "Ошибка: укажи правильный номер";
            } catch (RuntimeException e) {
                return e.getMessage();
            }
        }

        if (text.startsWith("/delete ")){
            try {
                int num = Integer.parseInt(text.substring(8).trim());
                taskService.deleteTask(userId, num);
                return "Задача удалена";
            } catch (NumberFormatException e) {
                return "Ошибка: укажи правильный номер";
            } catch (RuntimeException e) {
                return e.getMessage();
            }
        }

        return "Неизвестная команда. Напиши /start для помощи.";
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

}

