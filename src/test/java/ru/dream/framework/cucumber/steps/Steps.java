package ru.dream.framework.cucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Steps {
    public static final Logger logger = Logger.getLogger("logger");

    WebDriver driverChrome;
    WebDriver driverFirefox;


    @Given("^указать путь драйвера для браузера Chrome '(.*)'$")
    public void initChrome(String pathDriver){

        System.setProperty("webdriver.chrome.driver", pathDriver);
        driverChrome = new ChromeDriver();
        System.out.println("Двайвер Chrome установлен");
    }

    @Given("^указать путь драйвера для браузера FireFox '(.*)'$")
    public void initFirefox(String pathDriver){

        System.setProperty("webdriver.gecko.driver",pathDriver);
        driverFirefox= new FirefoxDriver();
        System.out.println("Двайвер FireFox установлен");
    }

    @When("^открыть страницу '(.*)'$")
    public void openPage(String url){
        driverChrome.manage().window().setSize(new Dimension(900,1000));
        driverFirefox.manage().window().setPosition(new Point(901,10));
        driverFirefox.manage().window().setSize(new Dimension(900,1000));
        driverChrome.get(url);
        driverFirefox.get(url);
        System.out.println("Страница: "+url+" успешно открыта");
    }

    @Then("^найти навигационный бар '(.*)'$")
    public void checkNavBar(String xpathNavBar){
        driverChrome.findElement(By.xpath(xpathNavBar));
        driverFirefox.findElement(By.xpath(xpathNavBar));
        System.out.println("NavBar присутствует");
    }

    @Then("^найти кнопку регистрации '(.*)'$")
    public void checkRegistrationButton(String xpathRegButton){
        driverChrome.findElement(By.xpath(xpathRegButton));
        driverFirefox.findElement(By.xpath(xpathRegButton));
        System.out.println("Кнопка регистрации присутствует");
    }

    @Then("^найти кнопку входа '(.*)'$")
    public void checkLoginButton(String xpathLoginButton){
        driverChrome.findElement(By.xpath(xpathLoginButton));
        driverFirefox.findElement(By.xpath(xpathLoginButton));
        System.out.println("Кнопка входа присутствует");
    }

    @After
    public void clearDrivers() {
        driverChrome.quit();
        driverFirefox.quit();
    }
}
