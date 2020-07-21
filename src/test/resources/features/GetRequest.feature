#language: ru
@test

Функционал: Отправка Api запроса без авторизации

  Структура сценария: Отправим запрос по эндпоинту <endpoint>
    Дано отпрака запроса по эндпоиту <endpoint>
    Затем запрос удачно обработан
    Примеры:
    |endpoint                 |
    |/{id}/posts/reply_editor/|
    |/{id}/merge/             |
    |/{id}/poll/              |
    |/{id}/posts/             |
    |/{id}/posts/merge/       |
    |/{id}/posts/move/        |
    |/{id}/posts/split/       |


