#language: ru
@test

Функционал: Отправка Api запроса без авторизации

  Структура сценария: Отправим запрос по эндпоинту <endpoint>
    Дано отправка запроса по эндпоиту <endpoint> ожидаемый ответ <statuscode>
    Примеры:
    |endpoint                | statuscode |
    |"/5/posts/reply_editor/"|    403     |
    |"/5/merge/"             |    405     |
    |"/5/poll/"              |    405     |
    |"/5/posts/"             |    200     |
    |"/5/posts/merge/"       |    405     |
    |"/5/posts/move/"        |    405     |
    |"/5/posts/split/"       |    405     |


