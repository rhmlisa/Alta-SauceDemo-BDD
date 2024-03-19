package org.example.pageObject;

import org.example.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutInfoPage extends BasePage {
    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='first-name']")
    private WebElement fieldFirstName;

    @FindBy(xpath = "//input[@id='last-name']")
    private WebElement fieldLastName;

    @FindBy(xpath = "//input[@id='postal-code']")
    private WebElement fieldPostalCode;

    @FindBy(xpath = "//input[@id='continue']")
    private WebElement btnContinue;

    @FindBy(xpath = "//div[@class='summary_subtotal_label']")
    private WebElement itemTotal;

    @FindBy(xpath = "//div[@class='summary_tax_label']")
    private WebElement tax;

    @FindBy(xpath = "//div[@class='summary_info_label summary_total_label']")
    private WebElement priceTotal;

    public boolean verifyInformationPage() {
        waitForElementVisible(fieldFirstName);
        waitForElementVisible(fieldLastName);
        waitForElementVisible(fieldPostalCode);

        boolean a = isDisplayed(fieldFirstName);
        boolean b = isDisplayed(fieldLastName);
        boolean c = isDisplayed(fieldPostalCode);

        return a && b && c;
    }

    public void inputFieldFirstName(String firstName) {
        sendKeys(fieldFirstName, firstName);
    }

    public void inputFieldLastName(String lastName) {
        sendKeys(fieldLastName, lastName);
    }

    public void inputFieldPostalCode(String postalCode) {
        sendKeys(fieldPostalCode, postalCode);
    }

    public void clickButtonContinue() {
        waitForElementClickable(btnContinue);
        click(btnContinue);
    }

    public boolean verifyTotalPrice() {
        String itemTotalText = cleanNonNumericChars(itemTotal.getText());
        String taxText = cleanNonNumericChars(tax.getText());
        String priceTotalText = cleanNonNumericChars(priceTotal.getText());

        try {
            double itemTotalValue = Double.parseDouble(itemTotalText);
            double taxValue = Double.parseDouble(taxText);
            double priceTotalValue = Double.parseDouble(priceTotalText);

            double calculatedTotal = itemTotalValue + taxValue;

            return Double.compare(calculatedTotal, priceTotalValue) == 0;
        } catch (NumberFormatException e) {
            System.err.println("Error parsing numeric values: " + e.getMessage());
            return false; // Return false jika terjadi kesalahan saat parsing nilai angka
        }
    }

    private String cleanNonNumericChars(String text) {
        return text.replaceAll("[^0-9.]", "");
    }
}
