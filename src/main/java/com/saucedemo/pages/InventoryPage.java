package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    private final By cartIcon = By.cssSelector(".shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    // Метод позволяет добавить ЛЮБОЙ товар по его названию (параметризация)
    public void addProductToCart(String productName) {
        String xpathLocator = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button", productName);
        driver.findElement(By.xpath(xpathLocator)).click();
    }

    public void goToCart() {
        driver.findElement(cartIcon).click();
    }
}