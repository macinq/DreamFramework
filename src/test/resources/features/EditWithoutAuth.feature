#language: ru
@api

Функционал: Изменение тем без авторизации

  Структура сценария: Выполнение метода PUT по эндпоинту <endpoint>
    Дано отправка запроса по эндпоиту <endpoint> с использованием метода PUT
    Затем ожидаем статус код 403 и сообщение 'Это действие не доступно гостю'
    Примеры:
      |endpoint                |
      |"/5/posts/reply_editor/"|
      |"/5/merge/"             |
      |"/5/poll/"              |
      |"/5/posts/"             |
      |"/5/posts/merge/"       |
      |"/5/posts/move/"        |
      |"/5/posts/split/"       |

  Структура сценария: Выполнение метода PATCH по эндпоинту <endpoint>
    Дано отправка запроса по эндпоиту <endpoint> с использованием метода PATCH
    Затем ожидаем статус код 403 и сообщение 'Это действие не доступно гостю'
    Примеры:
      |endpoint                |
      |"/5/posts/reply_editor/"|
      |"/5/merge/"             |
      |"/5/poll/"              |
      |"/5/posts/"             |
      |"/5/posts/merge/"       |
      |"/5/posts/move/"        |
      |"/5/posts/split/"       |

  Структура сценария: Выполнение метода POST по эндпоинту <endpoint>
    Дано отправка запроса по эндпоиту <endpoint> с использованием метода POST
    Затем ожидаем статус код 403 и сообщение 'Это действие не доступно гостю'
    Примеры:
      |endpoint                |
      |"/5/posts/reply_editor/"|
      |"/5/merge/"             |
      |"/5/poll/"              |
      |"/5/posts/"             |
      |"/5/posts/merge/"       |
      |"/5/posts/move/"        |
      |"/5/posts/split/"       |

  Структура сценария: Выполнение метода DELETE по эндпоинту <endpoint>
    Дано отправка запроса по эндпоиту <endpoint> с использованием метода DELETE
    Затем ожидаем статус код 403 и сообщение 'Это действие не доступно гостю'
    Примеры:
      |endpoint                |
      |"/5/posts/reply_editor/"|
      |"/5/merge/"             |
      |"/5/poll/"              |
      |"/5/posts/"             |
      |"/5/posts/merge/"       |
      |"/5/posts/move/"        |
      |"/5/posts/split/"       |


