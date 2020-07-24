package ru.dream.framework.cucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.BeforeStep;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;

import static ru.dream.framework.cucumber.steps.EditProfileSteps.*;


public class Hooks {
  public static final Logger logger = Logger.getLogger("logger");

  @BeforeStep
  public void before() throws InterruptedException {
    Thread.sleep(3000);
  }

  @AfterGroups
  public void clearDrivers() {
    if (webDriver.equalsIgnoreCase("google-chrome")) {
      driverChrome.quit();
    } else if (webDriver.equalsIgnoreCase("mozilla-firefox")) {
      driverFirefox.quit();
    } else {
      throw new IllegalArgumentException("Unsupported webdriver: " + webDriver);
    }
  }
}
