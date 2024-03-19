package org.example.pageObject;

import org.example.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends BasePage {
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//select[@class='product_sort_container']")
    private WebElement sortProduct;

    @FindBy(xpath = "//a[.='2']")
    private WebElement shoppingCart;

    @FindBy(xpath = "//span[text()='Your Cart']")
    private WebElement cartLabel;

    @FindBy(xpath = "//button[@id='checkout']")
    private WebElement btnCheckOut;

    @FindBy(xpath = "//div[@class='checkout_info']")
    private WebElement checkoutInfo;

    public void selectSortProduct(String text) {
        Select select = new Select(sortProduct);
        select.selectByVisibleText(text);
    }

    public void clickAddToCartItem(String item) {
        String button = "//div[text()='"+ item +"']/ancestor::div[@class='inventory_item_description']//button[text()='Add to cart']";
        driver.findElement(By.xpath(button)).click();
    }

    public void clickShoppingCart() {
        waitForElementClickable(shoppingCart);
        click(shoppingCart);
    }

    public boolean verifyCartLabel() {
        waitForElementVisible(cartLabel);
        return isDisplayed(cartLabel);
    }

    public void clickRemove(String itemToRemove) {
        String button = "//div[text()='" + itemToRemove + "']/ancestor::div[1]/div[@class='item_pricebar']//button[text()='Remove']";
        driver.findElement(By.xpath(button)).click();
    }

    public void clickButtonCheckOut() {
        waitForElementClickable(btnCheckOut);
        click(btnCheckOut);
    }

    public boolean verifyCheckoutInfo() {
        waitForElementVisible(checkoutInfo);
        return isDisplayed(checkoutInfo);
    }

}