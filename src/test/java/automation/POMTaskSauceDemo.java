package automation;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import task.pageobjects.CartPage;
import task.pageobjects.ConfirmationPage;
import task.pageobjects.LandingPage;
import task.pageobjects.OrderPage;
import task.pageobjects.OrderPage2;
import task.pageobjects.ProductListPage;

public class POMTaskSauceDemo {

  public WebDriver driver;

  @BeforeMethod
  public void setUp() {
    // Setup Driver
    System.setProperty("webdriver.chrome.driver", "D:/QA/chromedriver-win64/chromedriver.exe");

    driver = new ChromeDriver();
    driver.get("https://saucedemo.com/");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
  }

  @Test(dataProvider = "dataTestMapping")
  public void createOrder(HashMap<String, String> input) throws InterruptedException {
    LandingPage landingPage = new LandingPage(driver);
    landingPage.loginApplication(input.get("userEmail"), input.get("password"));

    String productName = input.get("productName");
    ProductListPage productListPage = new ProductListPage(driver);
    productListPage.addToCart(productName);
    productListPage.goToCart();

    CartPage cartPage = new CartPage(driver);
    Assert.assertTrue(cartPage.verifyCheckoutProduct(productName));
    cartPage.goToCheckoutPage();

    // String destination = "Indonesia";
    String firsName = "Novel";
    String lastName = "Rehandhika";
    String postalCode = "63413";
    OrderPage orderPage = new OrderPage(driver);
    // orderPage.selectCountry(destination);
    orderPage.inputData(firsName, lastName, postalCode);
    orderPage.continueOrder();

    OrderPage2 orderPage2 = new OrderPage2(driver);
    Assert.assertTrue(orderPage2.verifyCheckoutProduct(productName));
    orderPage2.finishOrder();

    ConfirmationPage confirmationPage = new ConfirmationPage(driver);
    String confirmationText = confirmationPage.getConfirmationPageText();
    Assert.assertEquals(confirmationText, "Thank you for your order!");

  }

  @AfterMethod
  public void tearDown() {
    driver.quit();
  }

  // Mapping
  @DataProvider
  public Object[][] dataTestMapping() {
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("userEmail", "standard_user");
    map.put("password", "secret_sauce");
    map.put("productName", "Sauce Labs Bolt T-Shirt");

    return new Object[][] { { map } };
  }
}