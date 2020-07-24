# DreamFramework
--------------------------------
## Изменение записи:
Для того, что бы изменить запись нужно прописать в файле "my.properties" следующие значения:
1. Добавить url сайта - Url
2. Добавить Cookie - Cookie
3. добавить токен (*если нужно*) - X-CSRFToken

Потом нужно указать 
1. Op
2. Path
3. Value

И сформировать запрос типа patch

Должен быть ответ "200"
## Проверка Логина
EmailAuthorizationFailedPasswordSteps - Проверка на некоректность данных(Вход не выполнен)

EmailАuthorizationSuccessSteps - Проверка на авторизацию через почту(Вход выполнен)

LoginАuthorizationSuccessSteps - Проверка на авторизацию через логин(Вход выполнен)

EmpyLoginFailedSteps - поле Логина пустое

EmpyPasswordAuthorizationFalledSteps - поле Пароля пустое

RecoverPasswordAuthorization - Поле восстановления пароля
