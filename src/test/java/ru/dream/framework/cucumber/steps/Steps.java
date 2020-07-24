package ru.dream.framework.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Затем;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.dream.framework.cucumber.Screenshot;
import ru.dream.framework.cucumber.WebDriverInstance;
import ru.dream.framework.cucumber.enums.WebDrivers;


public class Steps {
    public static final Logger logger = Logger.getLogger(Steps.class);

    WebDriver driverChrome;
    WebDriver driverFirefox;
    Screenshot screenshot;


    @Дано("^инициализировать драйвер для браузера Chrome$")
    public void initChrome() {
        logger.info("Инициализация драйвера Chrome");

        driverChrome = new WebDriverInstance(WebDrivers.CHROME.getName()).getWebDriver();
        System.out.println("Двайвер Chrome установлен");
        logger.info("Инициализация драйвера Chrome прошла успешно");
    }

    @Дано("^инициализировать драйвер для браузера FireFox$")
    public void initFirefox() {
        logger.info("Инициализация драйвера");

        driverFirefox = new WebDriverInstance(WebDrivers.FIREFOX.getName()).getWebDriver();
        System.out.println("Двайвер FireFox установлен");
        logger.info("Инициализация драйвера FireFox прошла успешно");
    }

    @Когда("^открыть страницу '(.*)'$")
    public void openPage(String url) {
        logger.info(String.format("Открытие страницы по url: %s", url));
        driverChrome.get(url);
        driverFirefox.get(url);
        screenshot = new Screenshot(driverChrome);
        System.out.println("Страница: " + url + " успешно открыта");
        screenshot.makeScreenshotToAllure("HomePage");
        logger.info("Страница: " + url + " успешно открыта.");
    }


    @Тогда("^найти навигационный бар '(.*)'$")
    public void checkNavBar(String xpathNavBar) {
        logger.info("Поиск навигационного бара");
        driverChrome.findElement(By.xpath(xpathNavBar));
        driverFirefox.findElement(By.xpath(xpathNavBar));
        System.out.println("NavBar присутствует");
        logger.info("Навигационный бар найден");
    }

    @Тогда("^найти кнопку регистрации '(.*)'$")
    public void checkRegistrationButton(String xpathRegButton) {
        logger.info("Поиск кнопки регистрации");
        driverChrome.findElement(By.xpath(xpathRegButton));
        driverFirefox.findElement(By.xpath(xpathRegButton));
        System.out.println("Кнопка регистрации присутствует");
        logger.info("Кнопка регистрации найдена");
    }

    @Тогда("^найти кнопку входа '(.*)'$")
    public void checkLoginButton(String xpathLoginButton) {
        logger.info("Поиск кнопки входа");
        driverChrome.findElement(By.xpath(xpathLoginButton));
        driverFirefox.findElement(By.xpath(xpathLoginButton));
        System.out.println("Кнопка входа присутствует");
        logger.info("Кнопка входа найдена");
    }

    @Затем("закрыть драйвера")
    public void quitDrivers() {
        if (driverChrome != null) {
            driverChrome.quit();
            logger.info("Драйвер Chrome закрыт");
        }
        if (driverFirefox != null) {
            driverFirefox.quit();
            logger.info("Драйвер Firefox закрыт");
        }
    }
}
