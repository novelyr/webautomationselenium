package webautomation.locator;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class LocatorPractice {
  public static void main(String[] args) throws InterruptedException {
    // cekDropdown();
    // dinamicDropdown();
    selectCountryFromTo();
  }

  public static void cekDropdown() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver",
        "D:/QA/chromedriver-win64/chromedriver.exe");

    WebDriver driver = new ChromeDriver();
    driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    Thread.sleep(2000);

    /*
     * select dropdown
     * 
     */

    WebElement staticDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));

    Select dropdown = new Select(staticDropdown);
    System.out.println("All selected option: " + dropdown.getAllSelectedOptions().size());
    System.out.println("1st selected option: " + dropdown.getFirstSelectedOption().getText());

    dropdown.selectByVisibleText("AED");
    System.out.println("apakah dapat AED?" + dropdown.getFirstSelectedOption().getText());

    dropdown.selectByIndex(3);
    System.out.println("apakah dapat index 3?" + dropdown.getFirstSelectedOption().getText());

    dropdown.selectByValue("INR");
    System.out.println("apakah dapat value INR?" + dropdown.getFirstSelectedOption().getText());

    driver.quit();
  }

  public static void dinamicDropdown() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver",
        "D:/QA/chromedriver-win64/chromedriver.exe");

    WebDriver driver = new ChromeDriver();
    driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    Thread.sleep(2000);

    driver.findElement(By.id("divpaxinfo")).click();

    // increase adult from 1 to n
    for (int i = 0; i < 3; i++) {
      driver.findElement(By.id("hrefIncAdt")).click();
    }
    // children infant caranya sama

    driver.findElement(By.id("btnclosepaxoption")).click();

  }

  public static void selectDiv() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver",
        "D:/QA/chromedriver-win64/chromedriver.exe");

    WebDriver driver = new ChromeDriver();
    driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    Thread.sleep(2000);

    driver.findElement(By.id("divpaxinfo")).click();

    // increase adult from 1 to n
    for (int i = 0; i < 3; i++) {
      driver.findElement(By.id("hrefIncAdt")).click();
    }
    // children infant caranya sama

    driver.findElement(By.id("btnclosepaxoption")).click();

  }

  public static void selectCountryFromTo() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver",
        "D:/QA/chromedriver-win64/chromedriver.exe");

    String countryFrom = "Durgapur (RDP)"; // change this to the country you wanna choose from

    WebDriver driver = new ChromeDriver();
    driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

    Thread.sleep(1000);

    driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();

    List<WebElement> options = driver.findElements(
        By.xpath("//div[@id='dropdownGroup1']//child::div[@class='dropdownDiv']//child::ul[1]//child::li"));
    // System.out.println("ini adalah options" + options);

    for (WebElement element : options) {
      System.out.println("list origin country: " + element.getText());
      if (element.getText().equals(countryFrom)) {
        element.click();
        break;
      }
    }

    Thread.sleep(2000);

    // driver.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")).click();

    Thread.sleep(2000);

    List<WebElement> arrivalCity = driver.findElements(
        By.xpath("(//div[@id='dropdownGroup1']//child::div[@class='dropdownDiv']//child::ul[1])[2]//child::li"));

    for (WebElement element : arrivalCity) {
      System.out.println("list destination country" + element.getText());
      if (element.getText().equals("Chennai (MAA)")) {
        element.click();
        break;
      }
    }
    Thread.sleep(5000);

    // driver.findElement(By.xpath("(//*[@id='autosuggest'])[1]")).sendKeys("ind");
    driver.findElement(By.cssSelector("#autosuggest")).sendKeys("ind");

    Thread.sleep(3000);

    // driver.findElement(By.xpath("//a[@id='ui-id-5']")).click();

    // Thread.sleep(3000);

    List<WebElement> country = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));

    for (WebElement webElement : country) {
      System.out.println("Ini adalah negara " + webElement.getText());
      if (webElement.getText().equals("Indonesia")) {
        webElement.click();
        break;
      }
    }

    Thread.sleep(3000);

    // Hande radio button
    driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();

    // Handle checkbox
    driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).click();

    Thread.sleep(3000);

    driver.close();

    driver.quit();
  }
}