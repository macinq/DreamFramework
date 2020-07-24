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
public class RecoverPasswordAuthorization {
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

    @Когда("Пользователь кликает на кнопку 'Забыли пароль?'")
    public void clickForgotPassword() {
        String xPathButton = "//div[@id ='modal-mount']/div[@class = 'modal-content']/form/div[@class = 'modal-footer']/ф[@class = 'btn btn-primary btn-block']";

        webDriver.findElement(By.xpath(xPathButton)).click();
    }

    @Тогда("Открывается страница 'https://misago.rnd.lanit.ru:58443/forgotten-password/'")
    public void detectRedirect() {
        if (webDriver.getCurrentUrl().equals("https://misago.rnd.lanit.ru:58443/forgotten-password/"))
            logger.info("Redirect is coming");
        else logger.warn("Redirect is not coming");
    }

    @Тогда("На странице есть поле 'Ваш e-mail адрес' и есть кнопка 'Отправить ссылку'")
    public void locatorsExists() {
        Boolean flug = false;
        String xPathEmail = "//div[@class = 'container']//div[@class = 'row']/div[@id = 'request-password-reset-mount']/div[class = 'well well-form well-form-request-password-reset']/form/div[not @class = 'hidden']";
        if (webDriver.findElements(By.xpath(xPathEmail)).size() > 0) flug = true;
        Assert.assertTrue(flug);
    }
    @Тогда("Закрыть браузер")
    public void closeBrowser()  {
        webDriver.quit();
    }
}
