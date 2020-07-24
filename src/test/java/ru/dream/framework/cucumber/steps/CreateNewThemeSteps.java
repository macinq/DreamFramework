package ru.dream.framework.cucumber.steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.dream.framework.cucumber.WebDriverInstance;
import ru.dream.framework.cucumber.steps.EditProfileSteps;

public class CreateNewThemeSteps {

    public static final Logger logger = Logger.getLogger(EditProfileSteps.class);

    static WebDriver webDriver;
    static String nameBrowser;

    @Дано("открыт браузер {string}")
    public void openBrowser(String browser) {
        nameBrowser = browser;
        webDriver = new WebDriverInstance(browser).getWebDriver();
    }

    @Дано("Открыта страница сайта {string}")
    public void openPageSite(String url) {
        webDriver.get(url);
    }

    @Дано("Пользователь вошел под логином {string} и паролем {string}")
    public void userEnterWithLoginAndPass(String login, String password) {
        webDriver.findElement(By.xpath("//button[@class='btn navbar-btn btn-default btn-sign-in']")).click();
        webDriver.findElement(By.id("id_username")).sendKeys(login);
        webDriver.findElement(By.id("id_password")).sendKeys(password);
        webDriver.findElement(By.xpath("//button[@class='btn btn-primary btn-block']")).click();
    }

    @Когда("Пользователь жмет кнопку {string}")
    public void userPressButton(String text) {
        webDriver.findElement(By.xpath("//div[@class='col-sm-3 col-md-2 xs-margin-top']")).click();
        String newTheme = webDriver.findElement(By.xpath("//button[@class='btn btn-primary btn-block btn-outline']")).getText();
        String publishTheme = webDriver.findElement(By.xpath("//button[@class='btn btn-primary btn-sm pull-right']")).getText();
        if (text.equals(newTheme)){
            webDriver.findElement(By.xpath("//button[@class='btn btn-primary btn-block btn-outline']")).click();
        } else if (text.equals(publishTheme)){
            webDriver.findElement(By.xpath("//button[@class='btn btn-primary btn-sm pull-right']")).click();
        }
    }

    @Когда("Пользователь вводит {string} в заголовок темы и {string} в поле тела темы")
    public void userInputInFieldTheme(String title ,String body) {
        webDriver.findElement(By.xpath("//div[@class='col-sm-8 col-md-9']/input")).sendKeys(title);
        webDriver.findElement(By.xpath("//textarea[@class='form-control']")).sendKeys(body);
    }

    @Тогда("Открывается страница с названием только что созданной темы: {string} и текстом темы {string}")
    public void openPageWithNameCreatedTheme(String title ,String body) {
        String nameTheme = webDriver.findElement(By.xpath("//div[@class='col-sm-10 col-md-10']")).getText();
        String nameBody = webDriver.findElement(By.xpath("//div[@class='post-body']")).getText();
        if (nameTheme.equals(title) && nameBody.equals(body)){
            logger.info("Тема: "+nameTheme+"с телом: "+nameBody);
            System.out.println("Тема: "+nameTheme+"с телом: "+nameBody);
        }
    }

    @Когда("Пользователь вводит {string} в поле тела темы")
    public void userInputInFieldTheme(String body) {
        String titleTheme = webDriver.findElement(By.xpath("//input[@class='form-control']")).getText();
        if (titleTheme.isEmpty()){
            logger.info("Нету тела темы");
            System.out.println("Нету тела темы");
        }
    }

    @Тогда("Появляется сообщение с текстом {string}")
    public void showMessageWithText(String textAlert) {
        String text = webDriver.findElement(By.xpath("//p[@class='alert alert-danger']")).getText();
        String titleTheme = webDriver.findElement(By.xpath("//input[@class='form-control']")).getText();
        if (text.equals(textAlert)){
            logger.info(text);
            System.out.println(text);
        } else  if (titleTheme.length()>90){
            logger.info(text);
            System.out.println(text);
        }
    }

    @Когда("Пользователь вводит {string} в загаловок темы и Когда {string} в поле тела темы")
    public void userInputInTitleThemeAndFieldTheme(String title,String body) {
        webDriver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(title);
        webDriver.findElement(By.xpath("//textarea[@class='form-control']")).sendKeys(body);

    }

    @Когда("Пользователь вводит {string} в поле {string} и {string} в поле тела темы")
    public void userInputTitleAndBody(String text, String titleField, String body) {
        webDriver.findElement(By.xpath("//div[@class='col-sm-8 col-md-9']/input")).sendKeys(text);
        webDriver.findElement(By.xpath("//textarea[@class='form-control']")).sendKeys(body);

    }

    @Когда("Пользователь вводит {string} в заголовок темы")
    public void userInputTitle(String title) {
        webDriver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(title);
    }

    @И("Браузер закрывается")
    public void closeBrowser() {
        webDriver.quit();
    }
}
