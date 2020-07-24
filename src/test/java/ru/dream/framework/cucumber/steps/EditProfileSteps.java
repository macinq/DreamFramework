package ru.dream.framework.cucumber.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import ru.dream.framework.cucumber.enums.WebDrivers;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class EditProfileSteps {
  public static final Logger logger = Logger.getLogger(EditProfileSteps.class);

  static WebDriver driverChrome;
  static WebDriver driverFirefox;
  static String webDriver;

  @Дано("Открыт браузер {string}")
  public void openBrowser(String browser) {
    webDriver = browser;
    if (webDriver.equalsIgnoreCase("google-chrome")) {
      System.setProperty(WebDrivers.CHROME.type, WebDrivers.CHROME.path);
      driverChrome = new ChromeDriver();
      driverChrome.manage().window().maximize();
      driverChrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      System.out.println("Двайвер Chrome установлен");
    } else if (webDriver.equalsIgnoreCase("mozilla-firefox")) {
      System.setProperty(WebDrivers.FIREFOX.type, WebDrivers.FIREFOX.path);
      driverFirefox = new FirefoxDriver();
      driverFirefox.manage().window().maximize();
      driverFirefox.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      System.out.println("Двайвер FireFox установлен");
    } else {
      throw new IllegalArgumentException("Unsupported webdriver: " + webDriver);
    }
  }

  @Дано("Открыта веб-страница по адресу: {string}")
  public void openPage(String url) {
    if (webDriver.equalsIgnoreCase("google-chrome")) {
      driverChrome.get(url);
    } else if (webDriver.equalsIgnoreCase("mozilla-firefox")) {
      driverFirefox.get(url);
    } else {
      throw new IllegalArgumentException("Unsupported webdriver: " + webDriver);
    }
  }

  @Дано("Выполнен вход в аккаунт")
  public void signIn(DataTable signIn) {
    List<Map<String,String>> table = signIn.asMaps(String.class, String.class);

    String xPathSignIn = "//button[@class='btn navbar-btn btn-default btn-sign-in']";
    String xPathLogin = "//input[@id='id_username']";
    String xPathPassword = "//input[@id='id_password']";
    String xPathSignInButton = "//button[@class='btn btn-primary btn-block']";
    String xPathPage = "//div[@class='container navbar-full navbar-desktop-nav']";
    if (webDriver.equalsIgnoreCase("google-chrome")) {
      driverChrome.findElement(By.xpath(xPathSignIn)).click();
      driverChrome.findElement(By.xpath(xPathLogin)).clear();
      driverChrome.findElement(By.xpath(xPathLogin)).sendKeys(table.get(0).get("Значение"));
      driverChrome.findElement(By.xpath(xPathPassword)).clear();
      driverChrome.findElement(By.xpath(xPathPassword)).sendKeys(table.get(1).get("Значение"));
      driverChrome.findElement(By.xpath(xPathSignInButton)).click();
    } else if (webDriver.equalsIgnoreCase("mozilla-firefox")) {
      driverFirefox.findElement(By.xpath(xPathSignIn)).click();
      driverFirefox.findElement(By.xpath(xPathLogin)).clear();
      driverFirefox.findElement(By.xpath(xPathLogin)).sendKeys(table.get(0).get("Значение"));
      driverFirefox.findElement(By.xpath(xPathPassword)).clear();
      driverFirefox.findElement(By.xpath(xPathPassword)).sendKeys(table.get(1).get("Значение"));
      driverFirefox.findElement(By.xpath(xPathSignInButton)).click();
    } else {
      throw new IllegalArgumentException("Unsupported webdriver: " + webDriver);
    }
  }

  @Когда("Пользователь жмет на кнопку Профиль")
  public void pressButton() {
    String xPath = "//a/img[@class='user-avatar' and 1]";
    if (webDriver.equalsIgnoreCase("google-chrome")) {
      WebElement button = driverChrome.findElement(By.xpath(xPath));
      button.click();
    } else if (webDriver.equalsIgnoreCase("mozilla-firefox")) {
      WebElement button = driverFirefox.findElement(By.xpath(xPath));
      button.click();
    } else {
      throw new IllegalArgumentException("Unsupported webdriver: " + webDriver);
    }
  }

  @Тогда("Появляется окно Информация")
  public void showMenu() {
    String xPath = "//ul[@class='dropdown-menu user-dropdown dropdown-menu-right']";
    boolean flag = false;
    String xPathButton = "//button[@class='btn btn-default btn-block']";
    String data = "Выход";
    if (webDriver.equalsIgnoreCase("google-chrome")) {
      WebElement menu = driverChrome.findElement(By.xpath(xPath));
      if (menu.findElement(By.xpath(xPathButton)).getText().contains(data)) {
        flag = true;
      }
    } else if (webDriver.equalsIgnoreCase("mozilla-firefox")) {
      WebElement menu = driverFirefox.findElement(By.xpath(xPath));
      if (menu.findElement(By.xpath(xPathButton)).getText().contains(data)) {
        flag = true;
      }
    } else {
      throw new IllegalArgumentException("Unsupported webdriver: " + webDriver);
    }

    Assert.assertTrue(flag, "Страница не открылась");
  }

  @Когда("Пользователь жмет на кнопку Изменить настройки")
  public void changeSettings() {
    String xPath = "//ul[@class='dropdown-menu user-dropdown dropdown-menu-right']/li[4]/a[1]";
    if (webDriver.equalsIgnoreCase("google-chrome")) {
      WebElement button = driverChrome.findElement(By.xpath(xPath));
      button.click();
    } else if (webDriver.equalsIgnoreCase("mozilla-firefox")) {
      WebElement button = driverFirefox.findElement(By.xpath(xPath));
      button.click();
    } else {
      throw new IllegalArgumentException("Unsupported webdriver: " + webDriver);
    }
  }

  @Тогда("Открывается страница Настройки")
  public void openSettingsPage() {
    String xPath = "//div[@class='container']/h1[1]";
    boolean flag = false;
    String data = "Измените ваши настройки";
    if (webDriver.equalsIgnoreCase("google-chrome")) {
      WebElement page = driverChrome.findElement(By.xpath(xPath));
      if (page.getText().contains(data)) {
        flag = true;
      }
    } else if (webDriver.equalsIgnoreCase("mozilla-firefox")) {
      WebElement page = driverFirefox.findElement(By.xpath(xPath));
      if (page.getText().contains(data)) {
        flag = true;
      }
    } else {
      throw new IllegalArgumentException("Unsupported webdriver: " + webDriver);
    }

    Assert.assertTrue(flag, "Страница не открылась");
  }

  @Когда("Пользователь жмет на кнопку Изменить")
  public void pressChangeButton() {
    String xPath = "//a[@class='list-group-item' and 2]";
    if (webDriver.equalsIgnoreCase("google-chrome")) {
      WebElement button = driverChrome.findElement(By.xpath(xPath));
      button.click();
    } else if (webDriver.equalsIgnoreCase("mozilla-firefox")) {
      WebElement button = driverFirefox.findElement(By.xpath(xPath));
      button.click();
    } else {
      throw new IllegalArgumentException("Unsupported webdriver: " + webDriver);
    }
  }

  @Тогда("Открывается страница Редактирование")
  public void openEditingPage() {
    String xPath = "//fieldset[1]/legend[1]";
    boolean flag = false;
    String data = "Personal";
    if (webDriver.equalsIgnoreCase("google-chrome")) {
      WebElement page = driverChrome.findElement(By.xpath(xPath));
      if (page.getText().contains(data)) {
        flag = true;
      }
    } else if (webDriver.equalsIgnoreCase("mozilla-firefox")) {
      WebElement page = driverFirefox.findElement(By.xpath(xPath));
      if (page.getText().contains(data)) {
        flag = true;
      }
    } else {
      throw new IllegalArgumentException("Unsupported webdriver: " + webDriver);
    }

    Assert.assertTrue(flag, "Страница не открылась");
  }

  @Когда("Пользователь заполняет страницу данными")
  public void dataFilling(DataTable fields) {
    List<Map<String,String>> table = fields.asMaps(String.class, String.class);

    String xPathGenderButton = "//button[@id='id_gender']";
    String xPathGender = "//li[1]/button[@class='btn-link' and 1]";
    String xPathName = "//input[@id='id_real_name']";
    String xPathBio = "//textarea[@id='id_bio']";
    String xPathLocation = "//input[@id='id_location']";
    String xPathTwitter = "//input[@id='id_twitter']";
    String xPathScype = "//input[@id='id_skype']";
    String xPathWebsite = "//input[@id='id_website']";
    if (webDriver.equalsIgnoreCase("google-chrome")) {
      driverChrome.findElement(By.xpath(xPathName)).clear();
      driverChrome.findElement(By.xpath(xPathName)).sendKeys(table.get(0).get("Значение"));
      driverChrome.findElement(By.xpath(xPathGenderButton)).click();
      driverChrome.findElement(By.xpath(xPathGender)).click();
      driverChrome.findElement(By.xpath(xPathBio)).clear();
      driverChrome.findElement(By.xpath(xPathBio)).sendKeys(table.get(1).get("Значение"));
      driverChrome.findElement(By.xpath(xPathLocation)).clear();
      driverChrome.findElement(By.xpath(xPathLocation)).sendKeys(table.get(2).get("Значение"));
      driverChrome.findElement(By.xpath(xPathTwitter)).clear();
      driverChrome.findElement(By.xpath(xPathTwitter)).sendKeys(table.get(3).get("Значение"));
      driverChrome.findElement(By.xpath(xPathScype)).clear();
      driverChrome.findElement(By.xpath(xPathScype)).sendKeys(table.get(4).get("Значение"));
      driverChrome.findElement(By.xpath(xPathWebsite)).clear();
      driverChrome.findElement(By.xpath(xPathWebsite)).sendKeys(table.get(5).get("Значение"));
    } else if (webDriver.equalsIgnoreCase("mozilla-firefox")) {
      driverFirefox.findElement(By.xpath(xPathName)).clear();
      driverFirefox.findElement(By.xpath(xPathName)).sendKeys(table.get(0).get("Значение"));
      driverFirefox.findElement(By.xpath(xPathGenderButton)).click();
      driverFirefox.findElement(By.xpath(xPathGender)).click();
      driverFirefox.findElement(By.xpath(xPathBio)).clear();
      driverFirefox.findElement(By.xpath(xPathBio)).sendKeys(table.get(1).get("Значение"));
      driverFirefox.findElement(By.xpath(xPathLocation)).clear();
      driverFirefox.findElement(By.xpath(xPathLocation)).sendKeys(table.get(2).get("Значение"));
      driverFirefox.findElement(By.xpath(xPathTwitter)).clear();
      driverFirefox.findElement(By.xpath(xPathTwitter)).sendKeys(table.get(3).get("Значение"));
      driverFirefox.findElement(By.xpath(xPathScype)).clear();
      driverFirefox.findElement(By.xpath(xPathScype)).sendKeys(table.get(4).get("Значение"));
      driverFirefox.findElement(By.xpath(xPathWebsite)).clear();
      driverFirefox.findElement(By.xpath(xPathWebsite)).sendKeys(table.get(5).get("Значение"));
    } else {
      throw new IllegalArgumentException("Unsupported webdriver: " + webDriver);
    }
  }


  @И("Затем жмет на кнопку Сохранить")
  public void pressSaveButton() {
    String xPath = "//button[@class='btn btn-primary']";
    if (webDriver.equalsIgnoreCase("google-chrome")) {
      WebElement button = driverChrome.findElement(By.xpath(xPath));
      button.click();
    } else if (webDriver.equalsIgnoreCase("mozilla-firefox")) {
      WebElement button = driverFirefox.findElement(By.xpath(xPath));
      button.click();
    } else {
      throw new IllegalArgumentException("Unsupported webdriver: " + webDriver);
    }
  }

  @Тогда("Всплывает попап Данные обновлены")
  public void popUpSaved() {
    String xPath = "//*[@id='snackbar-mount']/div";
    boolean flag = false;
    String data = "Личные данные обновлены.";
    if (webDriver.equalsIgnoreCase("google-chrome")) {
      WebElement page = driverChrome.findElement(By.xpath(xPath));
      if (page.getText().contains(data)) {
        flag = true;
      }
    } else if (webDriver.equalsIgnoreCase("mozilla-firefox")) {
      WebElement page = driverFirefox.findElement(By.xpath(xPath));
      if (page.getText().contains(data)) {
        flag = true;
      }
    } else {
      throw new IllegalArgumentException("Unsupported webdriver: " + webDriver);
    }

    Assert.assertTrue(flag, "Страница не открылась");
  }
}
