package ru.dream.framework.cucumber.steps;

import io.cucumber.java.BeforeStep;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterGroups;

import static ru.dream.framework.cucumber.steps.EditProfileSteps.*;


public class Hooks {
  private static final Logger logger = Logger.getLogger(Hooks.class);

  @BeforeStep
  public void before() throws InterruptedException {
    Thread.sleep(3000);
  }

  @AfterGroups
  public void clearDrivers() {
    logger.info("Завершение сеанса WebDriver");
    if (webDriver.equalsIgnoreCase("google-chrome")) {
      driverChrome.quit();
      logger.info("Сеанс драйвера Chrome завершен");
    } else if (webDriver.equalsIgnoreCase("mozilla-firefox")) {
      driverFirefox.quit();
      logger.info("Сеанс драйвера Firefox завершен");
    } else {
      throw new IllegalArgumentException("Unsupported web driver: " + webDriver);
    }
  }
}
