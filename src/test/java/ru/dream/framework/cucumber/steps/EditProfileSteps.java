package ru.dream.framework.cucumber.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.dream.framework.cucumber.WebDriverInstance;
import ru.dream.framework.cucumber.enums.WebDrivers;

import java.util.List;
import java.util.Map;

/**
 * @author Sergey Igoshin
 */
public class EditProfileSteps {

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
   * @param url
   */
  @Дано("Открыта веб-страница по адресу: {string}")
  public void openPage(String url) {
    webDriver.get(url);
  }

  /**
   * АВТОРИЗАЦИЯ
   *
   * @param signIn
   */
  @Дано("Выполнен вход в аккаунт")
  public void signIn(DataTable signIn) {
    List<Map<String, String>> table = signIn.asMaps(String.class, String.class);

    String xPathSignIn = "//button[@class='btn navbar-btn btn-default btn-sign-in']";
    String xPathLogin = "//input[@id='id_username']";
    String xPathPassword = "//input[@id='id_password']";
    String xPathSignInButton = "//button[@class='btn btn-primary btn-block']";
    String login;

    if (nameBrowser.equalsIgnoreCase(WebDrivers.CHROME.name)) {
      login = table.get(0).get("Значение");
    } else {
      login = table.get(1).get("Значение");
    }

    webDriver.findElement(By.xpath(xPathSignIn)).click();
    webDriver.findElement(By.xpath(xPathLogin)).clear();
    webDriver.findElement(By.xpath(xPathLogin)).sendKeys(login);
    webDriver.findElement(By.xpath(xPathPassword)).clear();
    webDriver.findElement(By.xpath(xPathPassword)).sendKeys(table.get(2).get("Значение"));
    webDriver.findElement(By.xpath(xPathSignInButton)).click();
  }

  /**
   * ВХОД В НАСТРОЙКИ
   */
  @Когда("Пользователь жмет на кнопку ПРОФИЛЬ")
  public void pressButton() {
    String xPath = "//a/img[@class='user-avatar' and 1]";
    WebElement button = webDriver.findElement(By.xpath(xPath));
    button.click();
  }

  @Тогда("Появляется окно ИНФОРМАЦИЯ")
  public void showMenu() {
    String xPath = "//ul[@class='dropdown-menu user-dropdown dropdown-menu-right']";
    boolean flag = false;
    String xPathButton = "//button[@class='btn btn-default btn-block']";
    String data = "Выход";

    WebElement menu = webDriver.findElement(By.xpath(xPath));
    if (menu.findElement(By.xpath(xPathButton)).getText().contains(data)) {
      flag = true;
    }

    Assert.assertTrue(flag, "Страница не открылась");
  }

  @Когда("Пользователь жмет на кнопку ИЗМЕНИТЬ НАСТРОЙКИ")
  public void changeSettings() {
    String xPath = "//ul[@class='dropdown-menu user-dropdown dropdown-menu-right']/li[4]/a[1]";
    WebElement button = webDriver.findElement(By.xpath(xPath));
    button.click();
  }

  @Тогда("Открывается страница НАСТРОЙКИ")
  public void openSettingsPage() {
    String xPath = "//div[@class='container']/h1[1]";
    boolean flag = false;
    String data = "Измените ваши настройки";
    WebElement page = webDriver.findElement(By.xpath(xPath));

    if (page.getText().contains(data)) {
      flag = true;
    }

    Assert.assertTrue(flag, "Страница не открылась");
  }

  /**
   * ОПЦИИ ФОРУМА
   */
  @Когда("Пользователь настраивает ОПЦИИ")
  public void changeOptions() {
    String xPathPrivate = "//button[@id='id_is_hiding_presence']";
    String xPathInvite = "//button[@id='id_limits_private_thread_invites_to']";
    String xPathInviteValue = "//div[@class='btn-group btn-select-group open']/ul[@class='dropdown-menu' and 1]/li[2]/button[@class='btn-link' and 1]";
    String xPathSubscribeMyTopics = "//button[@id='id_subscribe_to_started_threads']";
    String xPathSubscribeMyTopicsValue = "//div[@class='btn-group btn-select-group open']/ul[@class='dropdown-menu' and 1]/li[3]/button[@class='btn-link' and 1 and @type='button']";
    String xPathSubscribeMyPosts = "//button[@id='id_subscribe_to_replied_threads']";
    String xPathSubscribeMyPostsValue = "//div[@class='btn-group btn-select-group open']/ul[@class='dropdown-menu' and 1]/li[2]/button[@class='btn-link' and 1 and @type='button']";

    webDriver.findElement(By.xpath(xPathPrivate)).click();

    webDriver.findElement(By.xpath(xPathInvite)).click();
    webDriver.findElement(By.xpath(xPathInviteValue)).click();

    webDriver.findElement(By.xpath(xPathSubscribeMyTopics)).click();
    webDriver.findElement(By.xpath(xPathSubscribeMyTopicsValue)).click();

    webDriver.findElement(By.xpath(xPathSubscribeMyPosts)).click();
    webDriver.findElement(By.xpath(xPathSubscribeMyPostsValue)).click();
  }

  @И("Затем жмет на кнопку СОХРАНИТЬ ОПЦИИ")
  public void pressSaveOptionsButton() {
    String xPath = "//button[@class='btn btn-primary']";

    WebElement button = webDriver.findElement(By.xpath(xPath));
    button.click();
  }

  @Тогда("Всплывает попап ДАННЫЕ ОПЦИЙ ОБНОВЛЕНЫ")
  public void popUpSavedOptions() {
    String xPathSuccess = "//*[@id='snackbar-mount']//p[contains(@class, 'alert')]";
    boolean flag = false;

    if (webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-success")) {
      System.out.println("Опции обновлены: " + webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-success"));
      flag = true;
    }

    Assert.assertTrue(flag, "Страница не открылась");
  }

  /**
   * РЕДАКТИРОВАТЬ ДЕТАЛИ
   */
  @Когда("Пользователь жмет на кнопку ИЗМЕНИТЬ ДЕТАЛИ")
  public void pressChangeButton() {
    String xPath = "//a[@class='list-group-item' and @href='/options/edit-details/']";

    WebElement button = webDriver.findElement(By.xpath(xPath));
    button.click();
  }

  @Тогда("Открывается страница ДЕТАЛИ")
  public void openEditingPage() {
    String xPath = "//div[@class='panel-heading']";
    boolean flag = false;
    String data = "Редактировать детали";

    WebElement page = webDriver.findElement(By.xpath(xPath));
    if (page.getText().contains(data)) {
      flag = true;
    }

    Assert.assertTrue(flag, "Страница не открылась");
  }

  @Когда("Пользователь заполняет страницу данными")
  public void dataFilling(DataTable fields) {
    List<Map<String, String>> table = fields.asMaps(String.class, String.class);

    String xPathGenderButton = "//button[@id='id_gender']";
    String xPathGender = "//li[1]/button[@class='btn-link' and 1]";
    String xPathName = "//input[@id='id_real_name']";
    String xPathBio = "//textarea[@id='id_bio']";
    String xPathLocation = "//input[@id='id_location']";
    String xPathTwitter = "//input[@id='id_twitter']";
    String xPathScype = "//input[@id='id_skype']";
    String xPathWebsite = "//input[@id='id_website']";

    webDriver.findElement(By.xpath(xPathName)).clear();
    webDriver.findElement(By.xpath(xPathName)).sendKeys(table.get(0).get("Значение"));
    webDriver.findElement(By.xpath(xPathGenderButton)).click();
    webDriver.findElement(By.xpath(xPathGender)).click();
    webDriver.findElement(By.xpath(xPathBio)).clear();
    webDriver.findElement(By.xpath(xPathBio)).sendKeys(table.get(1).get("Значение"));
    webDriver.findElement(By.xpath(xPathLocation)).clear();
    webDriver.findElement(By.xpath(xPathLocation)).sendKeys(table.get(2).get("Значение"));
    webDriver.findElement(By.xpath(xPathTwitter)).clear();
    webDriver.findElement(By.xpath(xPathTwitter)).sendKeys(table.get(3).get("Значение"));
    webDriver.findElement(By.xpath(xPathScype)).clear();
    webDriver.findElement(By.xpath(xPathScype)).sendKeys(table.get(4).get("Значение"));
    webDriver.findElement(By.xpath(xPathWebsite)).clear();
    webDriver.findElement(By.xpath(xPathWebsite)).sendKeys(table.get(5).get("Значение"));
  }


  @И("Затем жмет на кнопку СОХРАНИТЬ ДЕТАЛИ")
  public void pressSaveButton() {
    String xPath = "//button[@class='btn btn-primary']";

    WebElement button = webDriver.findElement(By.xpath(xPath));
    button.click();
  }

  @Тогда("Всплывает попап ДАННЫЕ ДЕТАЛЕЙ ОБНОВЛЕНЫ")
  public void popUpSaved() {
    String xPathSuccess = "//*[@id='snackbar-mount']//p[contains(@class, 'alert')]";
    boolean flag = false;

    if (webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-info")) {
      System.out.println("Детали обновлены: " + webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-info"));
      flag = true;
    }

    Assert.assertTrue(flag, "Страница не открылась");
  }

  /**
   * ИЗМЕНИТЬ ИМЯ ПОЛЬЗОВАТЕЛЯ
   */
  @Когда("Пользователь жмет на кнопку ИЗМЕНИТЬ ИМЯ")
  public void pressChangeNameButton() {
    String xPath = "//a[@class='list-group-item' and @href='/options/change-username/']";

    WebElement button = webDriver.findElement(By.xpath(xPath));
    button.click();
  }

  @Тогда("Открывается страница ИМЯ")
  public void openEditingNamePage() {
    String xPath = "//div[@class='panel-heading']";
    boolean flag = false;
    String data = "Изменить имя";

    WebElement page = webDriver.findElement(By.xpath(xPath));
    if (page.getText().contains(data)) {
      flag = true;
    }

    Assert.assertTrue(flag, "Страница не открылась");
  }

  @Когда("Пользователь заполняет поле НОВОЕ ИМЯ")
  public void changeName(DataTable names) {
    List<Map<String, String>> table = names.asMaps(String.class, String.class);
    String xPath = "//input[@id='id_username']";

    String name;
    if (nameBrowser.equalsIgnoreCase(WebDrivers.CHROME.name)) {
      name = table.get(0).get("Значение");
    } else {
      name = table.get(1).get("Значение");
    }
    webDriver.findElement(By.xpath(xPath)).clear();
    webDriver.findElement(By.xpath(xPath)).sendKeys(name);
  }

  @И("Затем жмет на кнопку СОХРАНИТЬ ИМЯ")
  public void pressSaveNameButton() {
    String xPath = "//button[@class='btn btn-primary']";

    WebElement button = webDriver.findElement(By.xpath(xPath));
    button.click();
  }

  @Тогда("Всплывает попап ДАННЫЕ ИМЕНИ ОБНОВЛЕНЫ")
  public void popUpSavedName() {
    String xPathSuccess = "//*[@id='snackbar-mount']//p[contains(@class, 'alert')]";
    boolean flag = false;

    if (webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-success")) {
      System.out.println("Логин обновлен: " + webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-success"));
      flag = true;
    }

    Assert.assertTrue(flag, "Страница не открылась");
  }

  /**
   * ИЗМЕНИТЬ EMAIL ИЛИ ПАРОЛЬ
   */
  @Когда("Пользователь жмет на кнопку ИЗМЕНИТЬ EMAIL ИЛИ ПАРОЛЬ")
  public void pressChangeEmailAndPasswordButton() {
    String xPath = "//a[@class='list-group-item' and @href='/options/sign-in-credentials/']";

    WebElement button = webDriver.findElement(By.xpath(xPath));
    button.click();
  }

  @Тогда("Открывается страница EMAIL ИЛИ ПАРОЛЬ")
  public void openEditingEmailAndPasswordPage() {
    String xPath = "//form[1]/div[@class='panel panel-default panel-form' and 1]/div[@class='panel-heading' and 1]";
    boolean flag = false;
    String data = "Изменить адрес e-mail";

    WebElement page = webDriver.findElement(By.xpath(xPath));
    if (page.getText().contains(data)) {
      flag = true;
    }

    Assert.assertTrue(flag, "Страница не открылась");
  }

  @Когда("Пользователь заполняет раздел ИЗМЕНИТЬ EMAIL данными")
  public void changeEmail(DataTable emails) {
    List<Map<String, String>> table = emails.asMaps(String.class, String.class);
    String xPathEmail = "//input[@id='id_new_email']";
    String xPathPassword = "//input[@id='id_confirm_email']";

    webDriver.findElement(By.xpath(xPathEmail)).clear();
    webDriver.findElement(By.xpath(xPathEmail)).sendKeys(table.get(0).get("Значение"));

    webDriver.findElement(By.xpath(xPathPassword)).clear();
    webDriver.findElement(By.xpath(xPathPassword)).sendKeys(table.get(1).get("Значение"));
  }

  @И("Затем жмет на кнопку СОХРАНИТЬ EMAIL")
  public void pressSaveEmailButton() {
    String xPath = "//form[1]//button[contains(@type, 'submit')]";

    WebElement button = webDriver.findElement(By.xpath(xPath));
    button.click();
  }

  @Тогда("Всплывает попап ДАННЫЕ EMAIL ОБНОВЛЕНЫ")
  public void popUpSavedEmail() {
    String xPathSuccess = "//*[@id='snackbar-mount']//p[contains(@class, 'alert')]";
    boolean flag = false;

    if (webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-success")) {
      System.out.println("Email обновлен: " + webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-success"));
      flag = true;
    }

    Assert.assertTrue(flag, "Страница не открылась");
  }

  @Когда("Пользователь заполняет раздел ИЗМЕНИТЬ ПАРОЛЬ данными")
  public void changePassword(DataTable passwords) {
    List<Map<String, String>> table = passwords.asMaps(String.class, String.class);

    String xPathNewPassword = "//input[@id='id_new_password']";
    String xPathRepeatPassword = "//input[@id='id_repeat_password']";
    String xPathOldPassword = "//input[@id='id_confirm_password']";

    webDriver.findElement(By.xpath(xPathNewPassword)).clear();
    webDriver.findElement(By.xpath(xPathNewPassword)).sendKeys(table.get(0).get("Значение"));

    webDriver.findElement(By.xpath(xPathRepeatPassword)).clear();
    webDriver.findElement(By.xpath(xPathRepeatPassword)).sendKeys(table.get(0).get("Значение"));

    webDriver.findElement(By.xpath(xPathOldPassword)).clear();
    webDriver.findElement(By.xpath(xPathOldPassword)).sendKeys(table.get(1).get("Значение"));
  }

  @И("Затем жмет на кнопку СОХРАНИТЬ ПАРОЛЬ")
  public void pressSavePasswordButton() {
    String xPath = "//form[2]//button[contains(@type, 'submit')]";

    WebElement button = webDriver.findElement(By.xpath(xPath));
    button.click();
  }

  @Тогда("Всплывает попап ДАННЫЕ ПАРОЛЯ ОБНОВЛЕНЫ")
  public void popUpSavedPassword() {
    String xPathSuccess = "//*[@id='snackbar-mount']//p[contains(@class, 'alert')]";
    boolean flag = false;

    if (webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-success")) {
      System.out.println("Пароль обновлен: " + webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-success"));
      flag = true;
    }

    Assert.assertTrue(flag, "Страница не открылась");
  }
}