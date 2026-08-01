package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage extends BasePage {

    private final By itemTotalLabel = By.cssSelector(".summary_subtotal_label");
    private final By finishButton = By.cssSelector("#finish");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    public double getProductPrice(String productName) {
        String xpathLocator = String.format("//div[text()='%s']/ancestor::div[@class='cart_item']//div[@class='inventory_item_price']", productName);
        String priceText = driver.findElement(By.xpath(xpathLocator)).getText().replace("$", "").trim();
        return Double.parseDouble(priceText);
    }

    public double getItemTotal() {
        String totalText = driver.findElement(itemTotalLabel).getText().replace("Item total: $", "").trim();
        return Double.parseDouble(totalText);
    }

    public void clickFinish() {
        driver.findElement(finishButton).click();
    }
}