package ru.dream.framework.cucumber.enums;

public enum WebDrivers {
  CHROME("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe"),
  FIREFOX("webdriver.gecko.driver", "C:\\webdriver\\geckodriver.exe");

  public String type;
  public String path;

  WebDrivers(String type, String path) {
    this.type = type;
    this.path = path;
  }

  public String getType() {
    return type;
  }

  public String getPath() {
    return path;
  }
}
