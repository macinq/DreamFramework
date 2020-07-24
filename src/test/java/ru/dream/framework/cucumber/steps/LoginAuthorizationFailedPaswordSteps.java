package ru.dream.framework.cucumber.steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.dream.framework.cucumber.WebDriverInstance;

/**
 * @author Nikita Vologzhanin
 */
public class LoginAuthorizationFailedPaswordSteps {
    public static final Logger logger = Logger.getLogger(Steps.class);

    static WebDriver webDriver;
    static String nameBrowser;

    /**
     * ЗАПУСК БРАУЗЕРА
     *
     * @param browser
     */
    @Дано("Открыт браузер {string}")
    public void openBrowser(String browser) {
        nameBrowser = browser;
        webDriver = new WebDriverInstance(browser).getWebDriver();
    }

    /**
     * ОТКРЫТИЕ СТРАНИЦЫ
     *
     * @param url - url
     */
    @Дано("Открыта веб-страница по адресу: {string}")
    public void openPage(String url) {
        webDriver.get(url);
    }

    @Когда("Пользователь жмет на кнопку 'Войти'")
    public void openButton() {
        String xPathNav = "//nav[@class='navbar navbar-misago navbar-default navbar-static-top']";
        String xPathContainer = "//div[@class='container navbar-full navbar-desktop-nav']";
        String xPathGuest = "//div[@class='nav nav-guest']";
        String xPathButton = "//div[@class='btn navbar-btn btn-primary btn-register']";
        //Кнопка "Войти"
        WebElement buttonIn = webDriver.findElement(By.xpath(xPathNav)).findElement(By.xpath(xPathContainer)).findElement(By.xpath(xPathGuest)).findElement(By.xpath(xPathButton));
        buttonIn.click();
    }

    @Тогда("Открывается окно 'Войти'")
    public void modalContent() {
        String xPathContent = "//div[@id ='modal-mount']/div[@class = 'modal-content']";
        //Проверяем существует ли xPathContent
        Boolean isExist = webDriver.findElements(By.xpath(xPathContent)).size() > 0;
        if (isExist) {
            logger.info("Модальное окно существует");
        } else {
            logger.warn("Ошибка открытия");
        }
    }

    @Когда("Пользователь вводит в первое поле {string}")
    /**
     * Проверка авторизации на сайте
     *
     * @param email    - Почта
     * @param password - пароль
     */
    public void tryAuthorize(String login) {
        String xPathControlInput = "//div[@id ='modal-mount']/div[@class = 'modal-content']/form/div[@class= 'modal-body']/div[@class= 'form-group']/div[@class= 'control-input']";
        //Login path
        String xPathLogin = "//input[@id ='id_username']";
        WebElement authorize = webDriver.findElement(By.xpath(xPathControlInput));
        authorize.findElement(By.xpath(xPathLogin)).sendKeys(login);
    }

    @Когда("Пользователь кликает на кнопку 'Войти'")
    public void clickButtonIn() {
        String xPathButton = "//div[@id ='modal-mount']/div[@class = 'modal-content']/form/div[@class = 'modal-footer']/button[@class = 'btn btn-primary btn-block']";

        webDriver.findElement(By.xpath(xPathButton)).click();
    }

    @Тогда("Появляется сообщение 'Логин или пароль неправильны.'")
    public void DataIsNotCorrect() {
        Boolean flug = false;
        String xPathDataIsNotCorrectAlert = "//div[@id = 'snackbar-mount']/div[@class = 'alerts-snackbar out']/p[@class = 'alert alert-danger']";
        if (webDriver.findElement(By.xpath(xPathDataIsNotCorrectAlert)).getText() == "Логин или пароль неправильны.")
            flug = true;
        Assert.assertTrue(flug, "Ошибка");
    }

    @Тогда("Закрыть браузер")
    public void closeBrowser() {
        webDriver.quit();
    }
}