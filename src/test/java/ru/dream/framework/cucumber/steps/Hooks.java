package ru.dream.framework.cucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.BeforeStep;

import static ru.dream.framework.cucumber.steps.EditProfileSteps.*;


public class Hooks {

  @BeforeStep
  public void beforeStep() throws InterruptedException {
    Thread.sleep(3000);
  }

  @After
  public void finishingDrivers() {
    if (nameBrowser != null) {
      webDriver.quit();
    } else {
      throw new NullPointerException("Web driver was not found");
    }
  }
}
