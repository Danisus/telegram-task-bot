# 🤖 Task Telegram Bot

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-green?style=flat-square&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-blue?style=flat-square&logo=postgresql)
![Telegram](https://img.shields.io/badge/Telegram-Bot-2CA5E0?style=flat-square&logo=telegram)
![Docker](https://img.shields.io/badge/Docker-ready-blue?style=flat-square&logo=docker)

Telegram-бот для ведения личного списка задач.
Каждый пользователь имеет свой список, данные сохраняются в PostgreSQL.

---

## ✨ Команды бота

| Команда | Описание |
|---------|----------|
| /start | Приветствие и список команд |
| /add задача | Добавить новую задачу |
| /list | Показать все задачи со статусами |
| /done номер | Отметить задачу выполненной |
| /delete номер | Удалить задачу |

---

## 💬 Пример работы

    /add Купить молоко
    /add Позвонить другу
    /list

    Твои задачи:
    1. ○ Купить молоко
    2. ○ Позвонить другу

    /done 1
    ✅ Задача №1 выполнена!

    /list

    Твои задачи:
    1. ✓ Купить молоко
    2. ○ Позвонить другу

    /delete 2
    🗑️ Задача №2 удалена

---

## 🛠 Технологии

- **Java 17**
- **Spring Boot 3** (Core, Data JPA)
- **Telegram Bots API**
- **PostgreSQL** / **Hibernate**
- **Lombok**, **Maven**
- **Docker & Docker Compose**

---

## 🚀 Запуск

### С Docker (рекомендуется)

    git clone https://github.com/Danisus/task-telegram-bot.git
    cd task-telegram-bot

Укажи токен в docker-compose.yml:

    environment:
      BOT_TOKEN: your_bot_token_here
      BOT_USERNAME: your_bot_username

    docker-compose up -d

### Локально

В application.properties:

    bot.token=your_bot_token_here
    bot.username=your_bot_username

    mvn spring-boot:run

> 🔑 Токен получить у @BotFather в Telegram

---

## 📁 Структура проекта

    src/main/java/org/example/taskbot/
    ├── bot/            # TaskBot — обработка команд
    ├── entities/       # Task — JPA-сущность
    ├── repositories/   # TaskRepository
    ├── services/       # TaskService — бизнес-логика
    ├── BotInitializer.java
    └── TaskBotApplication.java

---

## 🔮 Планирую добавить

- InlineKeyboard вместо текстовых команд
- Редактирование задач
- Напоминания (Scheduler)
- Пагинация для больших списков
- Тесты
- Поддержка нескольких языков
