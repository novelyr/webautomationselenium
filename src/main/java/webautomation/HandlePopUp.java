package webautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HandlePopUp {
  public static void main(String[] args) throws InterruptedException {
    // handleAlert();
    handleConfirm();
  }

  public static void handleAlert() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver",
        "D:/QA/chromedriver-win64/chromedriver.exe");
    WebDriver driver = new ChromeDriver();

    // driver.manage().window().setSize(new Dimension(390, 844));

    // setup link website
    driver.get("https://rahulshettyacademy.com/AutomationPractice/");

    driver.findElement(By.id("name")).sendKeys("Novel YR");

    driver.findElement(By.id("alertbtn")).click();

    System.out.println(driver.switchTo().alert().getText());
    Thread.sleep(2000);

    driver.switchTo().alert().accept();

    Thread.sleep(2000);
    driver.quit();
  }

  public static void handleConfirm() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver",
        "D:/QA/chromedriver-win64/chromedriver.exe");
    WebDriver driver = new ChromeDriver();

    // driver.manage().window().setSize(new Dimension(390, 844));

    // setup link website
    driver.get("https://rahulshettyacademy.com/AutomationPractice/");

    driver.findElement(By.id("name")).sendKeys("Novel YR");

    driver.findElement(By.id("confirmbtn")).click();

    System.out.println(driver.switchTo().alert().getText());
    Thread.sleep(2000);

    // driver.switchTo().alert().accept();
    driver.switchTo().alert().dismiss();

    Thread.sleep(2000);
    driver.quit();
  }
}