package ru.dream.framework.cucumber.steps;

import io.cucumber.java.*;

import static ru.dream.framework.cucumber.steps.EditProfileSteps.*;


public class Hooks {

  @BeforeStep
  public void before() throws InterruptedException {
    Thread.sleep(3000);
  }

  @After
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
