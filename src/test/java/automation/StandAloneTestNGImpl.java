package automation;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import webautomation.pageobjects.LandingPage;

public class StandAloneTestNGImpl {

  /*
   * anotasi
   * data provider
   * running testng
   */
  WebDriver driver;

  @BeforeMethod
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "D:/QA/chromedriver-win64/chromedriver.exe");
    driver = new ChromeDriver();
    driver.get("https://rahulshettyacademy.com/client");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
  }

  @Test(dataProvider = "dataTestMapping2")
  public void createOrder(Map<String, String> data) throws InterruptedException {
    String email = data.get("userEmail");
    String password = data.get("userPassword");
    String productNameString = data.get("userProduct");

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userEmail")));

    // Thread.sleep(100000);
    // Scenario Login
    // driver.findElement(By.id("userEmail")).sendKeys(email);
    // driver.findElement(By.id("userPassword")).sendKeys(password);

    // driver.findElement(By.className("login-btn")).click();

    LandingPage landingPage = new LandingPage(driver);
    landingPage.loginApplication(email, password);

    // Thread.sleep(5000);

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

    // List Product
    List<WebElement> listProduct = driver.findElements(By.cssSelector(".mb-3"));

    String productName = productNameString;

    WebElement product = listProduct.stream()
        .filter(prod -> prod.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

    product.findElement(By.xpath("//div[@class='card-body']//child::button//child::i[@class='fa fa-shopping-cart']"))
        .click();

    System.out.println("list product" + product);

    // Thread.sleep(3000);

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));

    Thread.sleep(2000);
    // wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//ngx-spinner[@class
    // = 'ng-star-inserted']"))));

    driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));

    driver.findElement(By.cssSelector(".totalRow button")).click();

    // Thread.sleep(5000);

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[placeholder = 'Select Country']")));

    // driver.findElement(By.cssSelector("[placeholder = 'Select
    // Country']")).sendKeys("Indonesia");

    Actions action = new Actions(driver);

    action.sendKeys(driver.findElement(By.cssSelector("[placeholder = 'Select Country']")), "ind").build().perform();

    // Thread.sleep(5000);

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ng-star-inserted']")));

    String destination = "Indonesia";

    List<WebElement> country = driver.findElements(By.xpath("//span[@class='ng-star-inserted']"));

    // System.out.println(country.get(0).getText());

    // for (WebElement cont : country){
    // if (cont.getText().equalsIgnoreCase(destination)) {
    // cont.click();
    // }
    // }

    WebElement cont = country.stream().filter(cont2 -> cont2.getText().equalsIgnoreCase(destination)).findFirst()
        .orElse(null);

    cont.click();

    driver.findElement(By.cssSelector(".action__submit")).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("hero-primary")));

    String confirmationPage = driver.findElement(By.className("hero-primary")).getText();

    System.out.println("buyer berhasil checkout " + confirmationPage);
  }

  @AfterMethod
  public void closeDriver() {
    driver.close();

  }

  @AfterSuite
  public void quitDriver() {
    driver.quit();
  }

  @DataProvider
  public Object[][] dataTest() {
    return new Object[][] {
        { "simanjuntakalbert57@gmail.com", "XBf@rWNvByn!#K8", "ZARA COAT 3" }
    };
  }

  @DataProvider
  public Object[][] dataTestMapping() {
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("userEmail", "simanjuntakalbert57@gmail.com");
    map.put("userPassword", "XBf@rWNvByn!#K8");
    map.put("userProduct", "ZARA COAT 3");

    return new Object[][] { { map } };
  }
  // ini SALAH
  // @DataProvider(name = "dataTestMapping2")
  // private Map<String, String> getTestData() {
  // Map<String, String> map = new HashMap<>();
  // map.put("userEmail", "simanjuntakalbert57@gmail.com");
  // map.put("userPassword", "XBf@rWNvByn!#K8");
  // map.put("userProduct", "ZARA COAT 3");
  // return map;
  // }

  @DataProvider(name = "dataTestMapping2")
  public static Object[][] getTestData() { // ✅ CORRECT RETURN TYPE
    Map<String, String> map = new HashMap<>();
    map.put("userEmail", "simanjuntakalbert57@gmail.com");
    map.put("userPassword", "XBf@rWNvByn!#K8");
    map.put("userProduct", "ZARA COAT 3");

    return new Object[][] { { map } };
  }
}
