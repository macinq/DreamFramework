package ru.dream.framework.cucumber;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Для создания скриншота, необходимо у объекта класса
 * вызывать метод makeScreenshotToAllure в шаге, где он необходим.
 * Скриншоты сохраняются в target и отобразятся в Allure отчете.
 * Для создания инстанса необходим драйвер.
 */

public class Screenshot {

    private final WebDriver driver;

    public Screenshot(WebDriver driver) {
        this.driver = driver;
    }

    private void getScreenshot(String name) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("target\\" + name + ".png"));
    }


    @Attachment(value = "Вложение", type = "image/png", fileExtension = ".png")
    private byte[] getBytes(String name) throws IOException {
        try {
            getScreenshot(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File root = new File(".");
        Path path = root.toPath().resolve(Paths.get("target\\" + name + ".png"));
        return Files.readAllBytes(path);

    }

    public void makeScreenshotToAllure(String screenshotName) {
        try {
            Allure.addAttachment(screenshotName, "image/png", new ByteArrayInputStream(getBytes(screenshotName)), ".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
