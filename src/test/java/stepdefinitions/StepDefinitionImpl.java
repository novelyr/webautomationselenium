package stepdefinitions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import hook.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import webautomation.pageobjects.CartPage;
import webautomation.pageobjects.ConfirmationPage;
import webautomation.pageobjects.LandingPage;
import webautomation.pageobjects.OrderPage;
import webautomation.pageobjects.ProductListPage;

public class StepDefinitionImpl {

  public WebDriver driver;
  // String productName = "ZARA COAT 3";
  // String destination = "Indonesia";

  @Given("Buyer landing to ecommerce")
  public void landingPage() {
    driver = Hooks.initializeDriver();
    driver.get("https://rahulshettyacademy.com/client");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
  }

  @Given("^Buyer logged to website email (.+) and password (.+)$")
  public void buyerLogin(String email, String password) {
    LandingPage landingPage = new LandingPage(driver);
    landingPage.loginApplication(email, password);
  }

  @When("^Buyer add product (.+) to Cart$")
  public void buyerAddProduct(String productName) throws InterruptedException {
    ProductListPage productListPage = new ProductListPage(driver);
    productListPage.addToCart(productName);
    productListPage.goToCart();
    ;
  }

  @And("^Buyer checkout product (.+)$")
  public void buyerCheckoutProduct(String productName) {
    CartPage cartPage = new CartPage(driver);
    Assert.assertTrue(cartPage.verifyCheckoutProduct(productName));
    cartPage.goToCheckoutPage();
  }

  @And("^Buyer place order (.+)$")
  public void buyerPlaceOrder(String destination) {
    OrderPage orderPage = new OrderPage(driver);
    orderPage.selectCountry(destination);
    orderPage.submitOrder();
  }

  @Then("^Buyer will see message is displayed on confirmation page (.+)$")
  public void confirmationPage(String successCheckout) {
    ConfirmationPage confirmationPage = new ConfirmationPage(driver);
    String confirmationText = confirmationPage.getConfirmationPage();
    Assert.assertEquals(confirmationText, successCheckout);
  }
}