package task;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class checkoutSauceDemo {

  public static void main(String[] args) throws InterruptedException {
    // Setup Driver
    System.setProperty("webdriver.chrome.driver", "D:/QA/chromedriver-win64/chromedriver.exe");

    WebDriver driver = new ChromeDriver();
    driver.get("https://saucedemo.com/");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));

    // Thread.sleep(100000);
    // Scenario Login
    driver.findElement(By.id("user-name")).sendKeys("standard_user");
    driver.findElement(By.id("password")).sendKeys("secret_sauce");

    driver.findElement(By.id("login-button")).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inventory_item")));
    // Thread.sleep(3000);

    // List Product
    List<WebElement> listProduct = driver.findElements(By.cssSelector(".inventory_item"));

    // System.out.println(listProduct.toString());

    String productName = "Sauce Labs Backpack";

    WebElement product = listProduct.stream()
        .filter(prod -> prod.findElement(By.cssSelector(".inventory_item_label a")).getText().equals(productName))
        .findFirst().orElse(null);

    product.findElement(By.id("add-to-cart-sauce-labs-backpack"))
        .click();

    // System.out.println("list product" + product);

    // chekcout
    driver.findElement(By.id("shopping_cart_container")).click();
    // Thread.sleep(3000);

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout")));
    driver.findElement(By.id("checkout")).click();
    // Thread.sleep(2000);

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));

    Actions action = new Actions(driver);

    // Use .build() only if you have multiple chained actions, e.g.
    // action.moveToElement(element).click().sendKeys("Hello").build().perform();

    // action.sendKeys(driver.findElement(By.id("first-name")),
    // "Novel").build().perform();
    action.sendKeys(driver.findElement(By.id("first-name")), "Novel").perform();
    action.sendKeys(driver.findElement(By.id("last-name")), "Rehandhika").perform();
    action.sendKeys(driver.findElement(By.id("postal-code")), "63413").perform();

    // Thread.sleep(2000);
    driver.findElement(By.id("continue")).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
    driver.findElement(By.id("finish")).click();

    String confirmationPage = driver.findElement(By.cssSelector("h2.complete-header")).getText();

    System.out.println("buyer berhasil checkout: " + confirmationPage);

    Thread.sleep(2000);
    driver.quit();
  }
}