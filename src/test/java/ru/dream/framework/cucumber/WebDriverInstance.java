package ru.dream.framework.cucumber;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.dream.framework.cucumber.enums.WebDrivers;

import java.util.concurrent.TimeUnit;

public class WebDriverInstance {

    private WebDriver webDriver;
    private String browser;

    public WebDriverInstance(String browser) {
        this.browser = browser;

        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            initForWindowsOS(browser);
        } else initForLinuxOS(browser);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void quit() {
        webDriver.quit();
    }

    private void initForWindowsOS(String browser) {
        if (browser.equalsIgnoreCase(WebDrivers.CHROME.getName())) {
            System.setProperty(WebDrivers.CHROME.type, WebDrivers.CHROME.path);
            initChromeDriver();

        } else if (browser.equalsIgnoreCase(WebDrivers.FIREFOX.getName())) {
            System.setProperty(WebDrivers.FIREFOX.type, WebDrivers.FIREFOX.path);
            initFirefoxDriver();

        } else {
            throw new IllegalArgumentException("Unsupported webdriver: " + browser);
        }

     }

    private void initForLinuxOS(String browser) {
        if (browser.equalsIgnoreCase(WebDrivers.CHROME.getName())) {
            initChromeDriver();

        } else if (browser.equalsIgnoreCase(WebDrivers.FIREFOX.getName())) {
           initFirefoxDriver();

        } else {
            throw new IllegalArgumentException("Unsupported webdriver: " + browser);
        }


    }

    private void initChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        webDriver = new ChromeDriver(options);
        setManageParams();
    }

    private void initFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-headless");
        webDriver = new FirefoxDriver(options);
        setManageParams();
    }

    private void setManageParams(){
        webDriver.manage().window().setSize(new Dimension(1920, 1080));
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        System.out.printf("Двайвер %s установлен\n", browser);
    }
}
