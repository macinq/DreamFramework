package ru.dream.framework.cucumber.enums;

public enum WebDrivers {
  CHROME("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe", "google-chrome"),
  FIREFOX("webdriver.gecko.driver", "C:\\webdriver\\geckodriver.exe", "mozilla-firefox");

  public String type;
  public String path;
  public String name;

  WebDrivers(String type, String path, String name) {
    this.type = type;
    this.path = path;
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public String getPath() {
    return path;
  }

  public String getName() {
    return path;
  }
}
