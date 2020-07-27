# language: ru

@Edit
@Profile
Функционал: Проверка возможности редактирования профиля.

  Структура сценария: Успешное редактирование профиля с использованием браузера <имя_браузера>
    Дано Открыт браузер <имя_браузера>
    Дано Открыта веб-страница по адресу: 'https://misago.rnd.lanit.ru:58443/'
    Дано Выполнен вход в аккаунт
      | Название                   | Значение                   |
      | login1                     | test225777                 |
      | login2                     | test225999                 |
      | password                   | qawsderfgt1                |
    Когда Пользователь жмет на кнопку ПРОФИЛЬ
    Тогда Появляется окно ИНФОРМАЦИЯ

    Когда Пользователь жмет на кнопку ИЗМЕНИТЬ НАСТРОЙКИ
    Тогда Открывается страница НАСТРОЙКИ

    Когда Пользователь настраивает ОПЦИИ
    И Затем жмет на кнопку СОХРАНИТЬ ОПЦИИ
    Тогда Всплывает попап ДАННЫЕ ОПЦИЙ ОБНОВЛЕНЫ

    Когда Пользователь жмет на кнопку ИЗМЕНИТЬ ДЕТАЛИ
    Тогда Открывается страница ДЕТАЛИ
    Когда Пользователь заполняет страницу данными
      | Название                  | Значение                    |
      | имя                       | test                        |
      | биография                 | test                        |
      | место                     | test                        |
      | twitter                   | test                        |
      | skype                     | test                        |
      | веб-сайт                  | https://test.net            |
    И Затем жмет на кнопку СОХРАНИТЬ ДЕТАЛИ
    Тогда Всплывает попап ДАННЫЕ ДЕТАЛЕЙ ОБНОВЛЕНЫ

    Когда Пользователь жмет на кнопку ИЗМЕНИТЬ ИМЯ
    Тогда Открывается страница ИМЯ
    Когда Пользователь заполняет поле НОВОЕ ИМЯ
      | Название                  | Значение                     |
      | имя1                       | test55gtF0                  |
      | имя2                       | test88yh00                  |
    И Затем жмет на кнопку СОХРАНИТЬ ИМЯ
    Тогда Всплывает попап ДАННЫЕ ИМЕНИ ОБНОВЛЕНЫ

    Когда Пользователь жмет на кнопку ИЗМЕНИТЬ EMAIL ИЛИ ПАРОЛЬ
    Тогда Открывается страница EMAIL ИЛИ ПАРОЛЬ
    Когда Пользователь заполняет раздел ИЗМЕНИТЬ EMAIL данными
      | Название                  | Значение                    |
      | новый email               | test@test.ru                |
      | текущий пароль            | qawsderfgt1                 |
    И Затем жмет на кнопку СОХРАНИТЬ EMAIL
    Тогда Всплывает попап ДАННЫЕ EMAIL ОБНОВЛЕНЫ

    Когда Пользователь заполняет раздел ИЗМЕНИТЬ ПАРОЛЬ данными
      | Название                  | Значение                   |
      | новый пароль             | poiuyhjkl0                  |
      | текущий пароль           | qawsderfgt1                 |
    И Затем жмет на кнопку СОХРАНИТЬ ПАРОЛЬ
    Тогда Всплывает попап ДАННЫЕ ПАРОЛЯ ОБНОВЛЕНЫ
    Тогда Закрыть браузер

    Примеры:
    | имя_браузера      |
    | 'google-chrome'   |
    | 'mozilla-firefox' |