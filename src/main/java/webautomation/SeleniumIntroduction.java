package webautomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumIntroduction {
  public static void main(String[] args) throws InterruptedException {
    // loginScenario();
    // incorrectPasswordScenario();
    forgotPasswordScenario();

  }

  public static void loginScenario() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver",
        "D:/QA/chromedriver-win64/chromedriver.exe");

    WebDriver driver = new ChromeDriver();

    driver.get("https://rahulshettyacademy.com/locatorspractice/");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    Thread.sleep(2000);

    // driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("novel@gmail.com");
    driver.findElement(By.cssSelector("input#inputUsername")).sendKeys("novel@gmail.com");
    driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("rahulshettyacademy");
    // driver.findElement(By.className("signInBtn")).click();
    driver.findElement(By.cssSelector("button.submit.signInBtn")).click();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));

    String greetingsMessage = driver.findElement(By.cssSelector("h2")).getText();
    System.out.println(greetingsMessage);

    String email = greetingsMessage.split(" ")[1].replace(",", "");
    System.out.println("Extracted email: " + email);

    driver.quit();

  }

  public static void incorrectPasswordScenario() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "D:/QA/chromedriver-win64/chromedriver.exe");

    WebDriver driver = new ChromeDriver();
    driver.get("https://rahulshettyacademy.com/locatorspractice/");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    Thread.sleep(5000);

    driver.findElement(By.cssSelector("input#inputUsername")).sendKeys("novel@gmail.com");
    driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("afteroffice");

    driver.findElement(By.className("signInBtn")).click();

    // String errorMessage =
    // driver.findElement(By.xpath("//p[@class='error']")).getText();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.error")));

    String errorMessage = driver.findElement(By.cssSelector("p.error")).getText();

    System.out.println("Ini error message " + errorMessage);

    Thread.sleep(2000);

    String username = driver.findElement(By.cssSelector("input#inputUsername")).getText();
    String password = driver.findElement(By.xpath("//input[@placeholder='Password']")).getText();

    System.out.println("username " + username + "password " + password);

    if (username == "" || password == "") {
      System.out.println("credentialnya gagal bozzzz");
    }

    driver.quit();
  }

  public static void forgotPasswordScenario() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "D:/QA/chromedriver-win64/chromedriver.exe");

    WebDriver driver = new ChromeDriver();
    driver.get("https://rahulshettyacademy.com/locatorspractice/");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    Thread.sleep(2000);

    driver.findElement(By.linkText("Forgot your password?")).click();

    Thread.sleep(2000);

    // Mengisi data
    driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("Novel Rehandhika");
    driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("novelrehandhika@gmail.com");

    driver.findElement(By.cssSelector("button.reset-pwd-btn")).click();

    Thread.sleep(5000);

    String password = driver.findElement(By.cssSelector("p.infoMsg")).getText();
    System.out.println(password);

    String[] splitParts = password.split("'");

    String extractedPassword = splitParts[1];

    driver.findElement(By.cssSelector("button.go-to-login-btn")).click();

    Thread.sleep(5000);

    driver.findElement(By.cssSelector("input#inputUsername")).sendKeys("novelrehandhika@gmail.com");
    driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(extractedPassword);

    driver.findElement(By.id("chkboxOne")).click();
    driver.findElement(By.id("chkboxTwo")).click();
    Thread.sleep(5000);

    driver.findElement(By.className("signInBtn")).click();

    Thread.sleep(5000);

    driver.quit();
  }

  // coba tambah ini

}
