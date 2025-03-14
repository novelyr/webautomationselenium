package hook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;

public class Hooks {

  public static WebDriver driver;

  @Before
  public void setUpAutomation() throws IOException {
    Properties properties = new Properties();
    FileInputStream fileInputStream = new FileInputStream(
        "D:\\QA\\webautomationselenium\\src\\main\\resources\\GlobalData.properties");

    properties.load(fileInputStream);
    String browserName = properties.getProperty("browser");

    // System.out.println("browserName" + browserName);

    if (browserName.equals("chrome")) {
      // Driver chrome
      System.setProperty("webdriver.chrome.driver", "D:/QA/chromedriver-win64/chromedriver.exe");
      driver = new ChromeDriver();
    } else {
      // Driver firefox
      System.setProperty("webdriver.gecko.driver", "");
      driver = new FirefoxDriver();
    }

  }

  @After
  public void tearDownScenario() {
    if (driver != null) {
      driver.close();
    }
  }

  public static WebDriver initializeDriver() {
    return driver;
  }

  // @BeforeAll
  // public void setUp() {

  // }

  @AfterAll
  public static void tearDownEnd() {
    if (driver != null) {
      driver.quit();
    }
  }
}
