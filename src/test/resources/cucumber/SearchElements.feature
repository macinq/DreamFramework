# language: ru

@Feature
Функционал: Проверка наличия кнопки входа,регистрации и навигационного бара на странице


  Сценарий: Успешное нахождение элемента
    Дано указать путь драйвера для браузера Chrome 'src\\main\\resources\\chromedriver.exe'
    Дано указать путь драйвера для браузера FireFox 'src\main\resources\geckodriver.exe'
    Когда открыть страницу 'https://misago.rnd.lanit.ru:58443/'
    Тогда найти кнопку входа '//button[@class='btn navbar-btn btn-default btn-sign-in']'
    Тогда найти навигационный бар '//nav[@role='navigation']'
    Тогда найти кнопку регистрации '//button[@class='btn navbar-btn btn-primary btn-register']'