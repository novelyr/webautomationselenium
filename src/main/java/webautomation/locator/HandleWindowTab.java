package webautomation.locator;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HandleWindowTab {
  public static void main(String[] args) throws InterruptedException {

    System.setProperty("webdriver.chrome.driver",
        "D:/QA/chromedriver-win64/chromedriver.exe");

    WebDriver driver = new ChromeDriver();
    driver.get("https://rahulshettyacademy.com/loginpagePractise/#");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    driver.findElement(By.className("blinkingText")).click();

    // lebih aman untuk performance
    // Set<String> windows = driver.getWindowHandles();

    // lebih mudah dibaca
    List<String> windows = new ArrayList<>(driver.getWindowHandles());

    /*
     * Bakal ada dua value [parent, child]
     * contoh: [7301AABDA9242CA1CF26BFC1C9D6BF0E, DF220471DCB65795E3EF30531088DA00]
     */
    System.out.println("Ini adalah windows" + windows);

    // iterator less memory usage, lebih aman untuk performance
    // Iterator<String> iterator = windows.iterator();
    // String parentId = iterator.next();
    // String childId = iterator.next();

    //
    String tab1 = windows.get(0);
    String tab2 = windows.get(1);

    Thread.sleep(5000);

    driver.switchTo().window(tab2);

    Thread.sleep(3000);

    driver.switchTo().window(tab1);

    Thread.sleep(3000);

    driver.switchTo().window(tab2);

    Thread.sleep(3000);

    driver.switchTo().window(tab1);

    Thread.sleep(3000);

    driver.switchTo().window(tab2);

    driver.close();

    driver.quit();
  }
}