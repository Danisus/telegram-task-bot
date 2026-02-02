 TaskBot — Telegram-бот для управления задачами

Простой и удобный Telegram-бот для ведения списка задач (to-do list).  
Каждый пользователь имеет свой личный список задач, сохранённый в базе данных. Задачи упорядочены по порядку добавления.

 ✨ Основные возможности

- `/start` — приветствие и список команд
- `/add <текст задачи>` — добавить новую задачу (в конец списка)
- `/list` — показать все задачи с номерами и статусом (✓ выполненная, ○ активная)
- `/done <номер>` — отметить задачу как выполненную
- `/delete <номер>` — удалить задачу
- Задачи привязаны к Telegram ID пользователя (личный список)
- Сохранение в PostgreSQL через Spring Data JPA

 🛠 Технологии

- Java 17
- Spring Boot 3 (Core, Data JPA)
- Telegram Bots API (telegrambots-spring-boot-starter или аналог)
- PostgreSQL
- Hibernate / JPA
- Lombok
- Maven
- Docker

 🚀 Запуск проекта

 Вариант 1 — с Docker (рекомендуется)
1. Клонируй репозиторий
   git clone https://github.com/Danisus/task-telegram-bot.git
   cd task-telegram-bot
Запусти docker-compose up -d
Бот запустится и будет готов к работе.

Вариант 2 — локально
Настрой PostgreSQL (база taskbot, user postgres, pass root)
В application.properties / yml укажи подключение к БД и токен бота (лучше через env)
Собери и запустиBashmvn clean package
java -jar target/TaskBot-0.0.1-SNAPSHOT.jar

🤖 Как пользоваться
Найди бота в Telegram по username: @TaskBot (или запусти локально и напиши ему)
Напиши /start — получишь список команд
Примеры:
/add Купить молоко
/add Позвонить другу
/list → увидите:textТвои задачи:
1. ○ Купить молоко
2. ○ Позвонить другу
/done 1 → задача №1 отмечена как выполненная
/delete 2 → задача удалена


📁 Структура проекта
textsrc/main/java/org/example/taskbot/
├── bot/          # TaskBot — обработка обновлений и команд
├── entities/     # Task — JPA-сущность
├── repositories/ # TaskRepository
├── services/     # TaskService — бизнес-логика
├── BotInitializer.java # регистрация бота
└── TaskBotApplication.java

🔮 Что дальше планирую улучшить
Кнопки (InlineKeyboard) вместо текстовых команд
Редактирование задач
Напоминания по времени (scheduler)
Пагинация для больших списков
Тесты (unit + integration)
Полноценный docker-compose с БД
Поддержка нескольких языков

⭐ Если бот понравился — поставь звезду репозиторию!
