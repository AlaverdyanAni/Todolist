# Todolist
Веб-приложение для управления списком задач (to-do list) на основе Spring Boot. Приложение позволяет пользователям создавать, редактировать и удалять задачи.

Получать задачи по пользователю/категории. У каждой задачи один пользователь, который ее создал. У каждой задачи может только один Label, своего рода категория задачи.

### Сущности (модель)

- Сущность User с полями:

 -id (тип Long, автоинкрементируемый primary key)

 -имя (тип String)

 -электронная почта (тип String)

- Сущность Task с полями:

 -id (тип Long, автоинкрементируемый primary key)

 -название (тип String)

 -описание (тип String)

 -статус (тип Enum: "Новая", "В процессе", "Завершена")

-дата создания (тип LocalDateTime)

 -дата обновления (тип LocalDateTime)

- Сущность Label с полями:

 -id (тип Long, автоинкрементируемый primary key)

 -название (тип String)

- Созданы классы DTO.

- Для каждой сущности создан JPA репозиторий.

- Использован Liquibase для создания/обновления таблиц в базе данных

- Сервисы для каждой сущности реализуют CRUD операции.

- Написаны тесты на сервисы, используя Mockito.

- Добавлены мапперы для каждой сущности.

- Добавлены контроллеры, реализующие CRUD операции для каждого вида сущности (User, Task, Label).

- В контроллер для задач добавлены методы:

  -Поиск всех задач определенного пользователя

  -Поиск задач с определеными статусами у определенного пользователя

  Используемые технологии

  - Java 11
  - Maven
  - Spring Boot
  - Liquibase
  - Postgresql
  - Mapstruct
  - Lombok
  - Springdoc
