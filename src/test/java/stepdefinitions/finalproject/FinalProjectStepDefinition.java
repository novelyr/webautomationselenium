package stepdefinitions.finalproject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import hook.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import task.pageobjects.CartPage;
import task.pageobjects.ConfirmationPage;
import task.pageobjects.FinishOrder;
import task.pageobjects.LandingPage;
import task.pageobjects.OrderPage;
import task.pageobjects.ProductListPage;

public class FinalProjectStepDefinition {

  public WebDriver driver;

  @Given("Buyer landing to ecommerce")
  public void landingPage() {
    driver = Hooks.initializeDriver();
    driver.get("https://saucedemo.com/");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
  }

  @Given("^Buyer logged to website username (.+) and password (.+)$")
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

  @And("^Buyer input first name (.+), last name (.+), and postal code (.+)$")
  public void buyerInputDetails(String firsName, String lastName, String postalCode) {
    OrderPage orderPage = new OrderPage(driver);
    orderPage.inputData(firsName, lastName, postalCode);
    orderPage.continueOrder();
  }

  @And("^Buyer finish order product (.+)$")
  public void buyerFinishOrder(String productName) {
    FinishOrder finishOrder = new FinishOrder(driver);
    Assert.assertTrue(finishOrder.verifyCheckoutProduct(productName));
    finishOrder.finishOrder();
  }

  @Then("^Buyer will see message is displayed on confirmation page (.+)$")
  public void confirmationPage(String successCheckout) {
    ConfirmationPage confirmationPage = new ConfirmationPage(driver);
    String confirmationText = confirmationPage.getConfirmationPageText();
    Assert.assertEquals(confirmationText, successCheckout);
  }

  @Then("^Buyer will see notification (.+)$")
  public void logedOutUser(String errorNotifString) {
    LandingPage landingPage = new LandingPage(driver);
    Assert.assertEquals(landingPage.errorNotificationText(), errorNotifString);
  }

  @Then("^Buyer will see error input notification (.+)$")
  public void errorInputNotif(String errorNotifString) {
    OrderPage orderPage = new OrderPage(driver);
    Assert.assertEquals(orderPage.errorNotificationText(), errorNotifString);
  }

  @Then("^Buyer still in the finish page with title (.+)$")
  public void errorStuckFinish(String titleTextString) {
    FinishOrder finishOrder = new FinishOrder(driver);
    finishOrder.finishOrder(); // klik again 1
    finishOrder.finishOrder(); // klik again 2
    finishOrder.finishOrder(); // klik again 3
    Assert.assertEquals(finishOrder.getTitleText(), titleTextString);
  }

}