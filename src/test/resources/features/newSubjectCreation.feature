# language: ru

@Feature
Функционал: Провекра функционала создания новой темы.

  Структура сценария: Успешное создание новой темы.
    Дано открыт браузер <имя_браузера>
    Дано Открыта страница сайта 'https://misago.rnd.lanit.ru:58443'
    Дано Пользователь вошел под логином <логин> и паролем <пароль>
    Когда Пользователь жмет кнопку 'Новая тема'
    Когда Пользователь вводит `test1' в заголовок темы и 'text1' в поле тела темы
    Когда Пользователь жмет кнопку 'Опубликовать тему'
    Тогда Открывается страница с названием только что созданной темы: 'test1' и текстом темы 'test text'

    Примеры:
      | имя_браузера      | логин           | пароль        |
      | 'google-chrome'   | 'Test190720_g1' | 'Test$190720' |
      | 'mozilla-firefox' | 'Test190720_f1' | 'Test$190720' |

  Структура сценария: Успешное создание новой темы зарегистрированным пользователем.
    Дано открыт браузер <имя_браузера>
    Дано Открыта страница сайта 'https://misago.rnd.lanit.ru:58443'
    Дано Пользователь вошел под логином <логин> и паролем <пароль>
    Когда Пользователь жмет кнопку 'Новая тема'
    Когда Пользователь вводит 'test_test_test_test_test_test_test_test_test_test_test_test_test_test_test_test_test_test_'в поле 'Заголовок темы' и 'test text' в поле тела темы
    Когда Пользователь жмет кнопку 'Опубликовать тему'
    Тогда Открывается страница с названием только что созданной темы: 'test_test_test_test_test_test_test_test_test_test_test_test_test_test_test_test_test_test_' и текстом темы 'test text'

    Примеры:
      | имя_браузера      | логин           | пароль        |
      | 'google-chrome'   | 'Test190720_g1' | 'Test$190720' |
      | 'mozilla-firefox' | 'Test190720_f1' | 'Test$190720' |

  Структура сценария: : Провал создания новой темы зарегистрированным пользователем.
  Нет заголовка темы
    Дано открыт браузер <имя_браузера>
    Дано Открыта страница сайта 'https://misago.rnd.lanit.ru:58443'
    Дано Пользователь вошел под логином <логин> и паролем <пароль>
    Когда Пользователь жмет кнопку 'Новая тема'
    Когда Пользователь вводит 'test text' в поле тела темы
    Когда Пользователь жмет кнопку 'Опубликовать тему'
    Тогда Появляется сообщение с текстом 'Вы должны ввести заголовок темы.'

    Примеры:
      | имя_браузера      | логин           | пароль        |
      | 'google-chrome'   | 'Test190720_g1' | 'Test$190720' |
      | 'mozilla-firefox' | 'Test190720_f1' | 'Test$190720' |

  Структура сценария: : Провал создания новой темы зарегистрированным пользователем.
  Короткий заголовок темы
    Дано открыт браузер <имя_браузера>
    Дано Открыта страница сайта 'https://misago.rnd.lanit.ru:58443'
    Дано Пользователь вошел под логином <логин> и паролем <пароль>
    Когда Пользователь жмет кнопку 'Новая тема'
    Когда Пользователь вводит `test' в поле 'Заголовок темы' и 'test text' в поле тела темы
    Когда Пользователь жмет кнопку 'Опубликовать тему'
    Тогда Появляется сообщение с текстом 'Название темы должно быть не короче 5 символов (сейчас 4).'

    Примеры:
      | имя_браузера      | логин           | пароль        |
      | 'google-chrome'   | 'Test190720_g1' | 'Test$190720' |
      | 'mozilla-firefox' | 'Test190720_f1' | 'Test$190720' |

  Структура сценария: Провал создания новой темы зарегистрированным пользователем.
  Слишком длинный заголовок темы
    Дано открыт браузер <имя_браузера>
    Дано Открыта страница сайта 'https://misago.rnd.lanit.ru:58443'
    Дано Пользователь вошел под логином <логин> и паролем <пароль>
    Когда Пользователь жмет кнопку 'Новая тема'
    Когда Пользователь вводит 'test_test_test_test_test_test_test_test_test_test_test_test_test_test_test_test_test_test_t' в загаловок темы и Когда 'test text' в поле тела темы
    Когда Пользователь жмет кнопку 'Опубликовать тему'
    Тогда Появляется сообщение с текстом 'Название темы не должно быть длиннее 90 символов (сейчас 91).'

    Примеры:
      | имя_браузера      | логин           | пароль        |
      | 'google-chrome'   | 'Test190720_g1' | 'Test$190720' |
      | 'mozilla-firefox' | 'Test190720_f1' | 'Test$190720' |

  Структура сценария: Провал создания новой темы зарегистрированным пользователем.
  Короткое тело темы
    Дано открыт браузер <имя_браузера>
    Дано Открыта страница сайта 'https://misago.rnd.lanit.ru:58443'
    Дано Пользователь вошел под логином <логин> и паролем <пароль>
    Когда Пользователь жмет кнопку 'Новая тема'
    Когда Пользователь вводит `test1' в поле 'Заголовок темы' и 'test' в поле тела темы
    Когда Пользователь жмет кнопку 'Опубликовать тему'
    Тогда Появляется сообщение с текстом 'Сообщение должно быть не короче 5 символов (сейчас 4).'

    Примеры:
      | имя_браузера      | логин           | пароль        |
      | 'google-chrome'   | 'Test190720_g1' | 'Test$190720' |
      | 'mozilla-firefox' | 'Test190720_f1' | 'Test$190720' |

  Структура сценария: Провал создания новой темы зарегистрированным пользователем.
  Нет тела темы
    Дано открыт браузер <имя_браузера>
    Дано Открыта страница сайта 'https://misago.rnd.lanit.ru:58443'
    Дано Пользователь вошел под логином <логин> и паролем <пароль>
    Когда Пользователь жмет кнопку 'Новая тема'
    Когда Пользователь вводит `test1' в заголовок темы
    Когда Пользователь жмет кнопку 'Опубликовать тему'
    Тогда Появляется сообщение с текстом 'Необходимо ввести сообщение.'

    Примеры:
      | имя_браузера      | логин           | пароль        |
      | 'google-chrome'   | 'Test190720_g1' | 'Test$190720' |
      | 'mozilla-firefox' | 'Test190720_f1' | 'Test$190720' |