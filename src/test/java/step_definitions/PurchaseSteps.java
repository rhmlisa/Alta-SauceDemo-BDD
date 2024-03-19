package step_definitions;

import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pageObject.CheckoutFinalPage;
import org.example.pageObject.CheckoutInfoPage;
import org.example.pageObject.InventoryPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class PurchaseSteps {
    private final WebDriver driver = Hooks.driver;
    InventoryPage inventoryPage = new InventoryPage(driver);
    CheckoutInfoPage checkoutInfoPage = new CheckoutInfoPage(driver);
    CheckoutFinalPage checkoutFinalPage = new CheckoutFinalPage(driver);

    @When("User sort product by {string}")
    public void sortProduct(String text){
        inventoryPage.selectSortProduct(text);
    }

    @And("User click add to cart button {string}")
    public void userClickAddToCartButton(String item){
        inventoryPage.clickAddToCartItem(item);
    }

    @And("User click cart icon to see the selected items")
    public void userClickCartLogoToSeeTheSelectedItems() {
        inventoryPage.clickShoppingCart();
    }

    @Then("User should be directed to the shopping cart page")
    public void shouldBeDirectedToTheShoppingCartPage(){
        Assert.assertTrue(inventoryPage.verifyCartLabel());
    }

    @When("User Remove {string} as the last added item")
    public void userRemoveAsTheLastAddedItem(String itemToRemove){
        inventoryPage.clickRemove(itemToRemove);
    }

    @And("User click button checkout")
    public void userClickButtonCheckout() {
        inventoryPage.clickButtonCheckOut();
    }

    @Then("User should be directed to the checkout information page")
    public void userShouldBeDirectedToTheCheckoutInformationPage() {
        Assert.assertTrue(inventoryPage.verifyCheckoutInfo());
    }

    @When("User input {string} as firstName and {string} as lastName and {string} as postalCode")
    public void userInputAsFirstNameAsLastNameAsPostalCode(String firstName, String lastName, String postalCode){
        Assert.assertTrue(checkoutInfoPage.verifyInformationPage());
        checkoutInfoPage.inputFieldFirstName(firstName);
        checkoutInfoPage.inputFieldLastName(lastName);
        checkoutInfoPage.inputFieldPostalCode(postalCode);
    }

    @And("User click button continue")
    public void userClickButtonContinue() {
        checkoutInfoPage.clickButtonContinue();
    }

    @Then("User should be directed to the checkout overview")
    public void userShouldBeDirectedToTheCheckoutOverview(){
        boolean isTotalPriceCorrect = checkoutInfoPage.verifyTotalPrice();
        Assert.assertTrue("Total price is not correct", isTotalPriceCorrect);
    }

    @When("User click finish")
    public void userClickFinish() {
        checkoutFinalPage.clickButtonFinish();
    }

    @Then("User should be directed to the checkout complete page")
    public void userShouldBeDirectedToTheCheckoutCompletePage(){
        Assert.assertTrue(checkoutFinalPage.verifyCheckoutComplete());
    }

}
