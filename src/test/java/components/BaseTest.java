package components;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {

  public WebDriver driver;

  public WebDriver initializeDriver() throws IOException {
    /*
     * Check global.properties and get brower
     * if (browser == "chrome"){
     * driver = chrome;
     * }else if(browser == "firefox") {
     * driver = firefox;
     * }else{
     * driver = edge
     * }
     */

    Properties properties = new Properties();
    FileInputStream fileInputStream = new FileInputStream(
        "D:\\QA\\webautomationselenium\\src\\main\\resources\\GlobalData.properties");

    properties.load(fileInputStream);
    String browserName = properties.getProperty("browser");

    System.out.println("browserName" + browserName);

    if (browserName.equals("chrome")) {
      // Driver chrome
      System.setProperty("webdriver.chrome.driver", "D:/QA/chromedriver-win64/chromedriver.exe");
      driver = new ChromeDriver();
    } else {
      // Driver firefox
      System.setProperty("webdriver.gecko.driver", "");
      driver = new FirefoxDriver();
    }
    driver.get("https://rahulshettyacademy.com/client");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    return driver;
  }
}