package org.example.pageObject;

import org.example.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutFinalPage extends BasePage {

    public CheckoutFinalPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@id='finish']")
    private WebElement btnFinish;

    @FindBy(xpath = "//span[text()='Checkout: Complete!']")
    private WebElement checkoutComplete;

    @FindBy(xpath = "//h2[text()='Thank you for your order!']")
    private WebElement thanksOrder;

    public void clickButtonFinish(){
        waitForElementClickable(btnFinish);
        click(btnFinish);
    }

    public boolean verifyCheckoutComplete(){
        waitForElementVisible(checkoutComplete);
        waitForElementVisible(thanksOrder);

        boolean a = isDisplayed(checkoutComplete);
        boolean b = isDisplayed(thanksOrder);
        return a && b;
    }
}

