package com.saucedemo.tests;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    public void initDriver(String browser) {
        // Завершаем старую сессию, если она вдруг осталась
        if (driver != null) {
            driver.quit();
            driver = null;
        }

        if (browser == null) {
            browser = "chrome";
        }

        switch (browser.toLowerCase()) {
            case "edge":
                System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
                driver = new EdgeDriver();
                break;
            case "chrome":
            default:
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null; // Важно обнулять ссылку!
        }
    }
}