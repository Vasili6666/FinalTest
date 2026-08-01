package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final String url = "https://www.saucedemo.com/";

    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Переход по адресу сайта
    public void open() {
        driver.get(url);
    }

    public void login(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Ожидаем, что URL содержит saucedemo.com
        wait.until(ExpectedConditions.urlContains("saucedemo.com"));

        // Ждем появления поля логина
        WebElement userInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(usernameInput)
        );

        userInput.clear();
        userInput.sendKeys(username);

        WebElement passInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordInput)
        );
        passInput.clear();
        passInput.sendKeys(password);

        driver.findElement(loginButton).click();
    }
}