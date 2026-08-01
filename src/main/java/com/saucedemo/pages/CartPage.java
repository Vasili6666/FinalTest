package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final By checkoutButton = By.cssSelector("#checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductDisplayed(String productName) {
        String xpathLocator = String.format("//div[@class='inventory_item_name' and text()='%s']", productName);
        return !driver.findElements(By.xpath(xpathLocator)).isEmpty();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}