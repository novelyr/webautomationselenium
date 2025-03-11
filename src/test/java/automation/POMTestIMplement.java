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

import webautomation.pageobjects.CartPage;
import webautomation.pageobjects.ConfirmationPage;
import webautomation.pageobjects.LandingPage;
import webautomation.pageobjects.OrderPage;
import webautomation.pageobjects.ProductListPage;

public class POMTestIMplement {
  /*
   * Annotasi
   * dataprovider
   * Running testng
   */

  public WebDriver driver;

  @BeforeMethod
  public void setUp() {
    // Setup Driver
    System.setProperty("webdriver.chrome.driver", "D:/QA/chromedriver-win64/chromedriver.exe");

    driver = new ChromeDriver();
    driver.get("https://rahulshettyacademy.com/client");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
  }

  @Test(dataProvider = "dataTestMapping")
  public void createOrder(HashMap<String, String> input) throws InterruptedException {
    LandingPage landingPage = new LandingPage(driver);
    landingPage.loginApplication(input.get("userEmail"), input.get("password"));

    String productName = "ZARA COAT 3";
    ProductListPage productListPage = new ProductListPage(driver);
    productListPage.addToCart(productName);
    productListPage.goToCart();

    CartPage cartPage = new CartPage(driver);
    Assert.assertTrue(cartPage.verifyCheckoutProduct(productName));
    cartPage.goToCheckoutPage();

    String destination = "Indonesia";

    OrderPage orderPage = new OrderPage(driver);
    orderPage.selectCountry(destination);
    orderPage.submitOrder();

    ConfirmationPage confirmationPage = new ConfirmationPage(driver);

    String confirmationText = confirmationPage.getConfirmationPage();

    Assert.assertEquals(confirmationText, "THANKYOU FOR THE ORDER.");

  }

  @AfterMethod
  public void tearDown() {
    driver.close();
  }

  @DataProvider
  public Object[][] dataTest() {
    return new Object[][] {
        { "simanjuntakalbert57@gmail.com", "XBf@rWNvByn!#K8", "ZARA COAT 3" },
    };
  }

  // Mapping
  @DataProvider
  public Object[][] dataTestMapping() {
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("userEmail", "simanjuntakalbert57@gmail.com");
    map.put("password", "XBf@rWNvByn!#K8");
    map.put("productName", "ZARA COAT 3");

    return new Object[][] { { map } };
  }
}