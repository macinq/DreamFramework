package ru.dream.framework.cucumber.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import ru.dream.framework.cucumber.WebDriverInstance;
import ru.dream.framework.cucumber.enums.WebDrivers;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author Sergey Igoshin
 */
public class EditProfileSteps {
  public static final Logger logger = Logger.getLogger(EditProfileSteps.class);

  static WebDriver webDriver;
  static String nameBrowser;

  /**
   * ЗАПУСК БРАУЗЕРА
   *
   * @param browser
   */
  @Дано("Открыт браузер {string}")
  public void openBrowser(String browser) {
    try {
      logger.info("Открытие сеанса WebDriver");
      nameBrowser = browser;
      webDriver = new WebDriverInstance(browser).getWebDriver();
      logger.info("Сеанс драйвера " + nameBrowser + " запущен");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  /**
   * ОТКРЫТИЕ СТРАНИЦЫ
   *
   * @param url
   */
  @Дано("Открыта веб-страница по адресу: {string}")
  public void openPage(String url) {
    try {
      logger.info(String.format("Открытие страницы %s", url));
      webDriver.get(url);
      logger.info(String.format("Страница %s открыта", url));
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  /**
   * АВТОРИЗАЦИЯ
   *
   * @param signIn
   */
  @Дано("Выполнен вход в аккаунт")
  public void signIn(DataTable signIn) {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Выполнение входа в аккаунт");
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
      logger.info("Вход в аккаунт выполнен");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  /**
   * ВХОД В НАСТРОЙКИ
   */
  @Когда("Пользователь жмет на кнопку ПРОФИЛЬ")
  public void pressButton() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Выполнение входа в настройки");
      String xPath = "//a/img[@class='user-avatar' and 1]";
      WebElement button = webDriver.findElement(By.xpath(xPath));
      button.click();
      logger.info("Вход выполнен");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Тогда("Появляется окно ИНФОРМАЦИЯ")
  public void showMenu() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Подтверждение открытия страницы");
      String xPath = "//ul[@class='dropdown-menu user-dropdown dropdown-menu-right']";
      boolean flag = false;
      String xPathButton = "//button[@class='btn btn-default btn-block']";
      String data = "Выход";

      WebElement menu = webDriver.findElement(By.xpath(xPath));
      if (menu.findElement(By.xpath(xPathButton)).getText().contains(data)) {
        flag = true;
        logger.info("Страница открылась");
      }

      Assert.assertTrue(flag, "Страница не открылась");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Когда("Пользователь жмет на кнопку ИЗМЕНИТЬ НАСТРОЙКИ")
  public void changeSettings() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Выполнение входа");
      String xPath = "//ul[@class='dropdown-menu user-dropdown dropdown-menu-right']/li[4]/a[1]";
      WebElement button = webDriver.findElement(By.xpath(xPath));
      button.click();
      logger.info("Вход выполнен");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Тогда("Открывается страница НАСТРОЙКИ")
  public void openSettingsPage() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Подтверждение");
      String xPath = "//div[@class='container']/h1[1]";
      boolean flag = false;
      String data = "Измените ваши настройки";
      WebElement page = webDriver.findElement(By.xpath(xPath));

      if (page.getText().contains(data)) {
        logger.info("Страница открылась");
        flag = true;
      }

      Assert.assertTrue(flag, "Страница не открылась");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  /**
   * ОПЦИИ ФОРУМА
   */
  @Когда("Пользователь настраивает ОПЦИИ")
  public void changeOptions() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Выполнение ввода");
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
      logger.info("Ввод выполнен");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @И("Затем жмет на кнопку СОХРАНИТЬ ОПЦИИ")
  public void pressSaveOptionsButton() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Сохранение");
      String xPath = "//button[@class='btn btn-primary']";
      WebElement button = webDriver.findElement(By.xpath(xPath));
      button.click();
      logger.info("Сохранение выполнено");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Тогда("Всплывает попап ДАННЫЕ ОПЦИЙ ОБНОВЛЕНЫ")
  public void popUpSavedOptions() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Подтверждение");
      String xPathSuccess = "//*[@id='snackbar-mount']//p[contains(@class, 'alert')]";
      boolean flag = false;

      if (webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-success")) {
        logger.info("Опции обновлены: " + webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-success"));
        flag = true;
      }

      Assert.assertTrue(flag, "Страница не открылась");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  /**
   * РЕДАКТИРОВАТЬ ДЕТАЛИ
   */
  @Когда("Пользователь жмет на кнопку ИЗМЕНИТЬ ДЕТАЛИ")
  public void pressChangeButton() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Выполнение входа");
      String xPath = "//a[@class='list-group-item' and @href='/options/edit-details/']";
      WebElement button = webDriver.findElement(By.xpath(xPath));
      button.click();
      logger.info("Вход выполнен");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Тогда("Открывается страница ДЕТАЛИ")
  public void openEditingPage() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Подтверждение");
      String xPath = "//div[@class='panel-heading']";
      boolean flag = false;
      String data = "Редактировать детали";

      WebElement page = webDriver.findElement(By.xpath(xPath));
      if (page.getText().contains(data)) {
        logger.info("Страница открылась");
        flag = true;
      }

      Assert.assertTrue(flag, "Страница не открылась");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Когда("Пользователь заполняет страницу данными")
  public void dataFilling(DataTable fields) {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Выполнение ввода");
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
      logger.info("Ввод выполнен");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }


  @И("Затем жмет на кнопку СОХРАНИТЬ ДЕТАЛИ")
  public void pressSaveButton() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Сохранение");
      String xPath = "//button[@class='btn btn-primary']";

      WebElement button = webDriver.findElement(By.xpath(xPath));
      button.click();
      logger.info("Сохранение выполнено");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Тогда("Всплывает попап ДАННЫЕ ДЕТАЛЕЙ ОБНОВЛЕНЫ")
  public void popUpSaved() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Подтверждение");
      String xPathSuccess = "//*[@id='snackbar-mount']//p[contains(@class, 'alert')]";
      boolean flag = false;

      if (webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-info")) {
        logger.info("Детали обновлены: " + webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-info"));
        flag = true;
      }

      Assert.assertTrue(flag, "Страница не открылась");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  /**
   * ИЗМЕНИТЬ ИМЯ ПОЛЬЗОВАТЕЛЯ
   */
  @Когда("Пользователь жмет на кнопку ИЗМЕНИТЬ ИМЯ")
  public void pressChangeNameButton() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Выполнение входа");
      String xPath = "//a[@class='list-group-item' and @href='/options/change-username/']";

      WebElement button = webDriver.findElement(By.xpath(xPath));
      button.click();
      logger.info("Вход выполнен");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Тогда("Открывается страница ИМЯ")
  public void openEditingNamePage() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Подтверждение");
      String xPath = "//div[@class='panel-heading']";
      boolean flag = false;
      String data = "Изменить имя";

      WebElement page = webDriver.findElement(By.xpath(xPath));
      if (page.getText().contains(data)) {
        logger.info("Страница открылась");
        flag = true;
      }

      Assert.assertTrue(flag, "Страница не открылась");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Когда("Пользователь заполняет поле НОВОЕ ИМЯ")
  public void changeName(DataTable names) {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Выполнение ввода");
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
      logger.info("Ввод выполнен");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @И("Затем жмет на кнопку СОХРАНИТЬ ИМЯ")
  public void pressSaveNameButton() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Сохранение");
      String xPath = "//button[@class='btn btn-primary']";

      WebElement button = webDriver.findElement(By.xpath(xPath));
      button.click();
      logger.info("Сохранение выполнено");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Тогда("Всплывает попап ДАННЫЕ ИМЕНИ ОБНОВЛЕНЫ")
  public void popUpSavedName() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Подтверждение");
      String xPathSuccess = "//*[@id='snackbar-mount']//p[contains(@class, 'alert')]";
      boolean flag = false;

      if (webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-success")) {
        logger.info("Логин обновлен: " + webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-success"));
        flag = true;
      }

      Assert.assertTrue(flag, "Страница не открылась");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  /**
   * ИЗМЕНИТЬ EMAIL ИЛИ ПАРОЛЬ
   */
  @Когда("Пользователь жмет на кнопку ИЗМЕНИТЬ EMAIL ИЛИ ПАРОЛЬ")
  public void pressChangeEmailAndPasswordButton() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Выполнение входа");
      String xPath = "//a[@class='list-group-item' and @href='/options/sign-in-credentials/']";

      WebElement button = webDriver.findElement(By.xpath(xPath));
      button.click();
      logger.info("Вход выполнен");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Тогда("Открывается страница EMAIL ИЛИ ПАРОЛЬ")
  public void openEditingEmailAndPasswordPage() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Подтверждение");
      String xPath = "//form[1]/div[@class='panel panel-default panel-form' and 1]/div[@class='panel-heading' and 1]";
      boolean flag = false;
      String data = "Изменить адрес e-mail";

      WebElement page = webDriver.findElement(By.xpath(xPath));
      if (page.getText().contains(data)) {
        logger.info("Страница открылась");
        flag = true;
      }

      Assert.assertTrue(flag, "Страница не открылась");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Когда("Пользователь заполняет раздел ИЗМЕНИТЬ EMAIL данными")
  public void changeEmail(DataTable emails) {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Выполнение ввода");
      List<Map<String, String>> table = emails.asMaps(String.class, String.class);
      String xPathEmail = "//input[@id='id_new_email']";
      String xPathPassword = "//input[@id='id_confirm_email']";

      webDriver.findElement(By.xpath(xPathEmail)).clear();
      webDriver.findElement(By.xpath(xPathEmail)).sendKeys(table.get(0).get("Значение"));

      webDriver.findElement(By.xpath(xPathPassword)).clear();
      webDriver.findElement(By.xpath(xPathPassword)).sendKeys(table.get(1).get("Значение"));
      logger.info("Ввод выполнен");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @И("Затем жмет на кнопку СОХРАНИТЬ EMAIL")
  public void pressSaveEmailButton() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Сохранение");
      String xPath = "//form[1]//button[contains(@type, 'submit')]";

      WebElement button = webDriver.findElement(By.xpath(xPath));
      button.click();
      logger.info("Сохранение выполнено");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Тогда("Всплывает попап ДАННЫЕ EMAIL ОБНОВЛЕНЫ")
  public void popUpSavedEmail() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Подтверждение");
      String xPathSuccess = "//*[@id='snackbar-mount']//p[contains(@class, 'alert')]";
      boolean flag = false;

      if (webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-success")) {
        logger.info("Email обновлен: " + webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-success"));
        flag = true;
      }

      Assert.assertTrue(flag, "Страница не открылась");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Когда("Пользователь заполняет раздел ИЗМЕНИТЬ ПАРОЛЬ данными")
  public void changePassword(DataTable passwords) {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Выполнение ввода");
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
      logger.info("Ввод выполнен");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @И("Затем жмет на кнопку СОХРАНИТЬ ПАРОЛЬ")
  public void pressSavePasswordButton() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Сохранение");
      String xPath = "//form[2]//button[contains(@type, 'submit')]";

      WebElement button = webDriver.findElement(By.xpath(xPath));
      button.click();
      logger.info("Сохранение выполнено");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Тогда("Всплывает попап ДАННЫЕ ПАРОЛЯ ОБНОВЛЕНЫ")
  public void popUpSavedPassword() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Подтверждение");
      String xPathSuccess = "//*[@id='snackbar-mount']//p[contains(@class, 'alert')]";
      boolean flag = false;

      if (webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-success")) {
        logger.info("Пароль обновлен: " + webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-success"));
        flag = true;
      }

      Assert.assertTrue(flag, "Страница не открылась");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Тогда("Закрыть браузер")
  public void closeBrowser() {
    logger.info("Закрытие браузера");
    Hooks.finishingDrivers();
  }

  @Когда("Пользователь жмет на кнопку ИЗМЕНИТЬ АВАТАР")
  public void pressEditAvatarButton() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Выполнение входа");
      String xPath = "//ul[@class='dropdown-menu user-dropdown dropdown-menu-right']/li/button[@class='btn-link']";
      WebElement button = webDriver.findElement(By.xpath(xPath));
      button.click();
      logger.info("Вход выполнен");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Тогда("Открывается страница АВАТАР")
  public void openAvatarPage() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Подтверждение");
      String xPath = "//h4";
      boolean flag = false;
      String data = "Изменить аватарку";
      WebElement page = webDriver.findElement(By.xpath(xPath));

      if (page.getText().contains(data)) {
        logger.info("Страница открылась");
        flag = true;
      }

      Assert.assertTrue(flag, "Страница не открылась");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Когда("Пользователь жмет на кнопку ЗАГРУЗИТЬ GRAVATAR")
  public void loadGravatar() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Выполнение ввода");
      String xPath = "//button[@class='btn btn-default btn-block btn-avatar-gravatar']";
      WebElement button = webDriver.findElement(By.xpath(xPath));
      button.click();
      logger.info("Ввод выполнен");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Тогда("Всплывает попап НЕТ АССОЦИИРОВАННОГО GRAVATAR")
  public void noAssociatedGravatar() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Подтверждение");
      String xPathSuccess = "//*[@id='snackbar-mount']//p[contains(@class, 'alert')]";
      boolean flag = false;

      if (webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-danger")) {
        logger.info("Нет граватара: " + webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-danger"));
        flag = true;
      }

      Assert.assertTrue(flag, "Страница не открылась");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Когда("Пользователь жмет на кнопку СГЕНЕРИРОВАТЬ АВАТАР")
  public void generateAvatar() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Выполнение ввода");
      String xPath = "//button[@class='btn btn-default btn-block btn-avatar-generate']";
      WebElement button = webDriver.findElement(By.xpath(xPath));
      button.click();
      logger.info("Ввод выполнен");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Тогда("Всплывает попап АВАТАР СГЕНЕРИРОВАН")
  public void avatarGenerated() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Подтверждение");
      String xPathSuccess = "//*[@id='snackbar-mount']//p[contains(@class, 'alert')]";
      boolean flag = false;

      if (webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-success")) {
        logger.info("Аватар сгенерирован: " + webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-success"));
        flag = true;
      }

      Assert.assertTrue(flag, "Страница не открылась");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Когда("Пользователь жмет на кнопку ЗАГРУЗИТЬ ИЗОБРАЖЕНИЕ")
  public void uploadAvatar() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Выполнение ввода");
      String xPath = "//button[@class='btn btn-default btn-block btn-avatar-upload']";
      WebElement button = webDriver.findElement(By.xpath(xPath));
      button.click();
      logger.info("Ввод выполнен");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Тогда("Открывается страница ВЫБРАТЬ ФАЙЛ")
  public void uploadAvatarPage() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Подтверждение");
      String xPath = "//div/button[@class='btn btn-default btn-block' and 1]";
      boolean flag = false;
      String data = "Отмена";
      WebElement page = webDriver.findElement(By.xpath(xPath));

      if (page.getText().contains(data)) {
        logger.info("Страница открылась");
        flag = true;
      }

      Assert.assertTrue(flag, "Страница не открылась");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Когда("Пользователь выбирает файл")
  public void selectAvatarFile(DataTable images) {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Выполнение ввода");
      List<Map<String, String>> table = images.asMaps(String.class, String.class);

      String xPathInput = "//*[@id='avatar-hidden-upload']";
      String image;

      if (nameBrowser.equalsIgnoreCase(WebDrivers.CHROME.name)) {
        image = table.get(0).get("Значение");
      } else {
        image = table.get(1).get("Значение");
      }
      logger.info("Путь к файлу найден: " + new File(image).getAbsolutePath());

      WebElement hiddenInput = webDriver.findElement(By.xpath(xPathInput));
      hiddenInput.sendKeys(new File(image).getAbsolutePath());
      logger.info("Ввод выполнен");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Тогда("Открывается страница ОБРЕЗАТЬ ИЗОБРАЖЕНИЕ")
  public void cropAvatar() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Подтверждение");
      String xPath = "//h4";
      boolean flag = false;
      String data = "Изменить аватарку";
      WebElement page = webDriver.findElement(By.xpath(xPath));

      WebElement back = webDriver.findElement(By.xpath(xPath));
      new Actions(webDriver).moveToElement(back);

      if (page.getText().contains(data)) {
        logger.info("Страница открылась");
        flag = true;
      }

      Assert.assertTrue(flag, "Страница не открылась");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @И("Затем пользователь жмет на кнопку УСТАНОВИТЬ АВАТАР")
  public void pressSetAvatar() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Выполнение ввода");
      String xPath = "//*[@id='modal-mount']//button[contains(@class, 'btn-primary')]";
      WebElement button = webDriver.findElement(By.xpath(xPath));
      button.click();
      logger.info("Ввод выполнен");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }

  @Тогда("Всплывает попап АВАТАР УСТАНОВЛЕН")
  public void popUpSetAvatar() {
    try {
      Hooks.sleep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      logger.info("Подтверждение");
      String xPathSuccess = "//*[@id='snackbar-mount']//p[contains(@class, 'alert')]";
      boolean flag = false;

      if (webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-success")) {
        logger.info("Аватар установлен: " + webDriver.findElement(By.xpath(xPathSuccess)).getAttribute("class").contains("alert-success"));
        flag = true;
      }

      Assert.assertTrue(flag, "Страница не открылась");
    } catch (WebDriverException e) {
      e.printStackTrace();
      Hooks.finishingDrivers();
    }
  }
}