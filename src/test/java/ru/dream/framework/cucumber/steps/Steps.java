package ru.dream.framework.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ru.Затем;
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


    @Given("^инициализировать драйвер для браузера Chrome$")
    public void initChrome(String pathDriver) {
        logger.info("Инициализация драйвера Chrome");

        driverChrome = new WebDriverInstance(WebDrivers.CHROME.getName()).getWebDriver();
        System.out.println("Двайвер Chrome установлен");
        logger.info("Инициализация драйвера Chrome прошла успешно");
    }

    @Given("^инициализировать драйвер для браузера FireFox$")
    public void initFirefox(String pathDriver) {
        logger.info("Инициализация драйвера");

        driverFirefox = new WebDriverInstance(WebDrivers.FIREFOX.getName()).getWebDriver();
        System.out.println("Двайвер FireFox установлен");
        logger.info("Инициализация драйвера FireFox прошла успешно");
    }

    @When("^открыть страницу '(.*)'$")
    public void openPage(String url) {
        logger.info(String.format("Открытие страницы по url: %s", url));
//        driverChrome.manage().window().setSize(new Dimension(900,1000));
//        driverFirefox.manage().window().setPosition(new Point(901,10));
//        driverFirefox.manage().window().setSize(new Dimension(900,1000));
        driverChrome.get(url);
        driverFirefox.get(url);
        screenshot = new Screenshot(driverChrome);
        System.out.println("Страница: "+url+" успешно открыта");
        screenshot.makeScreenshotToAllure("HomePage");
        logger.info("Страница: "+url+" успешно открыта.");
    }


    @Then("^найти навигационный бар '(.*)'$")
    public void checkNavBar(String xpathNavBar){
        logger.info("Поиск навигационного бара");
        driverChrome.findElement(By.xpath(xpathNavBar));
        driverFirefox.findElement(By.xpath(xpathNavBar));
        System.out.println("NavBar присутствует");
        logger.info("Навигационный бар найден");
    }

    @Then("^найти кнопку регистрации '(.*)'$")
    public void checkRegistrationButton(String xpathRegButton){
        logger.info("Поиск кнопки регистрации");
        driverChrome.findElement(By.xpath(xpathRegButton));
        driverFirefox.findElement(By.xpath(xpathRegButton));
        System.out.println("Кнопка регистрации присутствует");
        logger.info("Кнопка регистрации найдена");
    }

    @Then("^найти кнопку входа '(.*)'$")
    public void checkLoginButton(String xpathLoginButton){
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
