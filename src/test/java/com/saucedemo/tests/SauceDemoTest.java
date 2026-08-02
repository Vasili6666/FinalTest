package com.saucedemo.tests;

import com.saucedemo.pages.*;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Epic("SauceDemo E2E Flows")
public class SauceDemoTest extends BaseTest {


    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "edge"})
    @Feature("UC-1: Checkout Flow (One Item)")
    @Description("Проверка оформления заказа с одним товаром")
    public void testCheckoutFlowOneItem(String browser) {
        initDriver(browser);

        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        CheckoutStepOnePage stepOne = new CheckoutStepOnePage(getDriver());
        CheckoutStepTwoPage stepTwo = new CheckoutStepTwoPage(getDriver());
        CheckoutCompletePage completePage = new CheckoutCompletePage(getDriver());

        String targetProduct = "Sauce Labs Backpack";


        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");


        inventoryPage.addProductToCart(targetProduct);
        inventoryPage.goToCart();


        Assertions.assertTrue(cartPage.isProductDisplayed(targetProduct),
                "Товар не найден в корзине!");


        cartPage.clickCheckout();


        stepOne.fillInformation("John", "Doe", "12345");
        stepOne.clickContinue();


        stepTwo.clickFinish();
        Assertions.assertEquals("Thank you for your order!", completePage.getSuccessMessage(),
                "Текст сообщения об успехе не совпадает!");
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "edge"})
    @Feature("UC-2: Checkout Flow (Several Items)")
    @Description("Проверка оформления заказа с несколькими товарами и сверкой итоговой суммы")
    public void testCheckoutFlowSeveralItems(String browser) {
        initDriver(browser);

        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        CheckoutStepOnePage stepOne = new CheckoutStepOnePage(getDriver());
        CheckoutStepTwoPage stepTwo = new CheckoutStepTwoPage(getDriver());
        CheckoutCompletePage completePage = new CheckoutCompletePage(getDriver());

        String product1 = "Sauce Labs Backpack";
        String product2 = "Sauce Labs Bike Light";


        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");


        inventoryPage.addProductToCart(product1);
        inventoryPage.addProductToCart(product2);
        inventoryPage.goToCart();


        Assertions.assertTrue(cartPage.isProductDisplayed(product1), "Товар 1 отсутствует!");
        Assertions.assertTrue(cartPage.isProductDisplayed(product2), "Товар 2 отсутствует!");


        cartPage.clickCheckout();
        stepOne.fillInformation("Jane", "Doe", "54321");
        stepOne.clickContinue();


        double price1 = stepTwo.getProductPrice(product1);
        double price2 = stepTwo.getProductPrice(product2);
        double expectedTotal = price1 + price2;
        double actualTotal = stepTwo.getItemTotal();

        Assertions.assertEquals(expectedTotal, actualTotal, 0.01,
                "Итоговая сумма товаров рассчитана некорректно!");


        stepTwo.clickFinish();
        Assertions.assertEquals("Thank you for your order!", completePage.getSuccessMessage(),
                "Текст сообщения об успехе не совпадает!");
    }
}