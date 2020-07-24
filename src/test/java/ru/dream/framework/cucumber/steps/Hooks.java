package ru.dream.framework.cucumber.steps;

import io.cucumber.java.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterGroups;


import static ru.dream.framework.cucumber.steps.EditProfileSteps.*;


public class Hooks {
  private static final Logger logger = Logger.getLogger(Hooks.class);

  public void sleep() throws InterruptedException {
    Thread.sleep(3000);
  }

  public void finishingDrivers() {
    logger.info("Завершение сеанса WebDriver");
    if (nameBrowser != null) {
      webDriver.quit();
      logger.info("Сеанс драйвера " + nameBrowser + " завершен");
    } else {
      logger.error("Web driver was not found");
    }
  }
}