package task;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class PracticeAll {
  public static void main(String[] args) throws InterruptedException {
    WebDriver driver = setUp(); // change chromedriver address in setUp() if you want to try in your own laptop

    // STARTS from here, you can call any function you want to do

    // printTitle(driver);
    // radioButton(driver);
    // suggession(driver, "ara", "United Arab Emirates");
    // dropdown(driver);
    // checkbox(driver);
    // switchWindowHandler(driver);
    switchTabHandler(driver);
    handleAlert(driver);
    handleConfirm(driver);
    hideShowTextBox(driver);
    webTable(driver, 5);
    webTableFixHead(driver, 3);
    mouseHover(driver);

    // END off function
    // driverQuit must be called last
    driverQuit(driver);
  }

  public static WebDriver setUp() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver",
        "D:/QA/chromedriver-win64/chromedriver.exe"); // change the value to your chromedriver address

    WebDriver driver = new ChromeDriver();

    driver.get("https://rahulshettyacademy.com/AutomationPractice/");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    Thread.sleep(2000);

    return driver;
  }

  public static WebDriver printTitle(WebDriver driver) throws InterruptedException {
    String title = driver.findElement(By.cssSelector("body > h1")).getText();
    System.out.println("The title is: " + title);

    Thread.sleep(3000);
    return driver;
  }

  public static WebDriver radioButton(WebDriver driver) throws InterruptedException {
    driver.findElement(By.xpath("//div[@id='radio-btn-example']//input[@value='radio1']")).click();
    Thread.sleep(500);

    driver.findElement(By.xpath("//div[@id='radio-btn-example']//input[@value='radio2']")).click();
    Thread.sleep(500);

    driver.findElement(By.xpath("//div[@id='radio-btn-example']//input[@value='radio3']")).click();
    Thread.sleep(500);

    driver.findElement(By.xpath("//div[@id='radio-btn-example']//input[@value='radio2']")).click();
    Thread.sleep(500);

    driver.findElement(By.xpath("//div[@id='radio-btn-example']//input[@value='radio1']")).click();
    Thread.sleep(3000);

    return driver;
  }

  public static void driverQuit(WebDriver driver) throws InterruptedException {
    System.out.println("Thanks for using me, driver out!");
    Thread.sleep(2000);
    driver.quit();
  }

  public static WebDriver suggession(WebDriver driver, String keys, String country) throws InterruptedException {
    driver.findElement(By.cssSelector("#autocomplete")).sendKeys(keys);

    List<WebElement> countries = driver.findElements(By.cssSelector("li.ui-menu-item div"));

    // System.out.println("Country list: " + countries);

    for (WebElement webElement : countries) {
      System.out.println("Ini adalah negara: " + webElement.getText());
      if (webElement.getText().equals(country)) {
        webElement.click();
        System.out.println(country + " ditemukan. Stop looping.");
        break;
      } else {
        System.out.println("Negara tidak sesuai, lanjut looping.");
      }
    }

    Thread.sleep(3000);
    return driver;
  }

  public static WebDriver dropdown(WebDriver driver) throws InterruptedException {

    WebElement dropdownElement = driver.findElement(By.id("dropdown-class-example"));
    Select dropdown = new Select(dropdownElement);

    // Select by visible text
    dropdown.selectByVisibleText("Option1");
    System.out.println("memilih Option1: yang terpilih adalah " + dropdown.getFirstSelectedOption().getText());

    Thread.sleep(2000);

    // Select by value attribute
    dropdown.selectByValue("option2");
    System.out.println("memilih Option2: yang terpilih adalah " + dropdown.getFirstSelectedOption().getText());
    Thread.sleep(2000);

    // Select by index
    dropdown.selectByIndex(3); // select, Option1, Option2, Option3
    System.out.println("memilih Option3: yang terpilih adalah " + dropdown.getFirstSelectedOption().getText());

    Thread.sleep(3000);
    return driver;
  }

  public static WebDriver checkbox(WebDriver driver) throws InterruptedException {
    driver.findElement(By.xpath("//div[@id='checkbox-example']//input[@value='option1']")).click();
    Thread.sleep(500);

    driver.findElement(By.xpath("//div[@id='checkbox-example']//input[@value='option2']")).click();
    Thread.sleep(500);

    driver.findElement(By.xpath("//div[@id='checkbox-example']//input[@value='option3']")).click();
    Thread.sleep(500);

    driver.findElement(By.xpath("//div[@id='checkbox-example']//input[@value='option2']")).click();
    Thread.sleep(500);

    driver.findElement(By.xpath("//div[@id='checkbox-example']//input[@value='option1']")).click();
    Thread.sleep(500);

    driver.findElement(By.xpath("//div[@id='checkbox-example']//input[@value='option3']")).click();
    Thread.sleep(500);

    driver.findElement(By.xpath("//div[@id='checkbox-example']//input[@value='option3']")).click();
    Thread.sleep(500);

    driver.findElement(By.xpath("//div[@id='checkbox-example']//input[@value='option2']")).click();
    Thread.sleep(500);

    driver.findElement(By.xpath("//div[@id='checkbox-example']//input[@value='option1']")).click();
    Thread.sleep(500);

    Thread.sleep(2000);
    return driver;
  }

  public static WebDriver switchWindowHandler(WebDriver driver) throws InterruptedException {
    driver.findElement(By.id("openwindow")).click();

    // lebih aman untuk performance
    // Set<String> windows = driver.getWindowHandles();
    // iterator less memory usage, lebih aman untuk performance
    // Iterator<String> iterator = windows.iterator();
    // String window1 = iterator.next();
    // String window2 = iterator.next();

    List<String> windows = new ArrayList<>(driver.getWindowHandles());
    System.out.println("Ini adalah windows" + windows);

    String window1 = windows.get(0);
    String window2 = windows.get(1);
    Thread.sleep(3000);

    driver.switchTo().window(window2);
    Thread.sleep(1000);

    driver.switchTo().window(window1);
    Thread.sleep(1000);

    driver.switchTo().window(window2);
    Thread.sleep(1000);

    driver.switchTo().window(window1);
    Thread.sleep(1000);

    driver.switchTo().window(window2);
    Thread.sleep(1000);

    driver.close();

    // after close window2, must be returned to window1 so the automation can keep
    // going
    driver.switchTo().window(window1);

    Thread.sleep(2000);

    return driver;
  }

  public static WebDriver switchTabHandler(WebDriver driver) throws InterruptedException {
    driver.findElement(By.id("opentab")).click();

    // lebih aman untuk performance
    // Set<String> windows = driver.getWindowHandles();
    // iterator less memory usage, lebih aman untuk performance
    // Iterator<String> iterator = windows.iterator();
    // String tab1 = iterator.next();
    // String tab2 = iterator.next();

    List<String> windows = new ArrayList<>(driver.getWindowHandles());
    System.out.println("Ini adalah windows" + windows);

    String tab1 = windows.get(0);
    String tab2 = windows.get(1);
    Thread.sleep(3000);

    driver.switchTo().window(tab2);
    Thread.sleep(1000);

    driver.switchTo().window(tab1);
    Thread.sleep(1000);

    driver.switchTo().window(tab2);
    Thread.sleep(1000);

    driver.switchTo().window(tab1);
    Thread.sleep(1000);

    driver.switchTo().window(tab2);
    Thread.sleep(1000);

    driver.close();

    // after close tab2, must be returned to tab1 so the automation can keep going
    driver.switchTo().window(tab1);
    Thread.sleep(2000);

    return driver;
  }

  public static WebDriver handleAlert(WebDriver driver) throws InterruptedException {
    driver.findElement(By.id("name")).sendKeys("Novel ALERT");

    driver.findElement(By.id("alertbtn")).click();

    System.out.println(driver.switchTo().alert().getText());
    Thread.sleep(2000);

    driver.switchTo().alert().accept();

    return driver;
  }

  public static WebDriver handleConfirm(WebDriver driver) throws InterruptedException {
    driver.findElement(By.id("name")).sendKeys("Novel CONFIRM");

    driver.findElement(By.id("confirmbtn")).click();

    System.out.println(driver.switchTo().alert().getText());
    Thread.sleep(2000);

    driver.switchTo().alert().accept();

    driver.findElement(By.id("name")).sendKeys("Novel CANCEL");

    driver.findElement(By.id("confirmbtn")).click();

    System.out.println(driver.switchTo().alert().getText());
    Thread.sleep(2000);
    driver.switchTo().alert().dismiss();

    return driver;
  }

  public static WebDriver hideShowTextBox(WebDriver driver) throws InterruptedException {
    WebElement showTextElement = driver.findElement(By.id("show-textbox"));
    WebElement hideTextElement = driver.findElement(By.id("hide-textbox"));
    WebElement displayedTextElement = driver.findElement(By.id("displayed-text"));

    // Click Show and Handle Text Box
    toggleAndHandleTextBox(showTextElement, displayedTextElement);

    // Click Hide and Handle Text Box
    toggleAndHandleTextBox(hideTextElement, displayedTextElement);

    toggleAndHandleTextBox(showTextElement, displayedTextElement);
    toggleAndHandleTextBox(hideTextElement, displayedTextElement);

    return driver;
  }

  private static void toggleAndHandleTextBox(WebElement toggleButton, WebElement textBox) throws InterruptedException {
    toggleButton.click();
    Thread.sleep(500);

    if (textBox.isDisplayed()) {
      System.out.println("Text bos is now DISPLAYED");
      textBox.sendKeys("You can see me now");
      System.out.println("The default value is: " + textBox.getDomAttribute("value"));
      System.out.println("The value sent is: " + textBox.getDomProperty("value"));
    } else {
      System.out.println("Text bos is now HIDDEN");
      System.out.println("Cannot send keys! Element is not visible.");
    }
    Thread.sleep(2000);
  }

  public static WebDriver webTable(WebDriver driver, int rowNumber) {
    List<WebElement> rows = driver.findElements(By.xpath("//table[@id='product']/tbody/tr"));
    // System.out.println(rows.size());
    // found 20 size

    // Check if rowNumber is valid
    if (rowNumber <= 0 || rowNumber > rows.size()) {
      System.out.println("Invalid row number!");
      return driver;
    }

    // Get the row at the given number (index 0 is header, continue to index 1)
    WebElement row = rows.get(rowNumber);

    // Extract the data from the row
    String instructor = row.findElement(By.xpath("(./td)[1]")).getText();
    String course = row.findElement(By.xpath("(./td)[2]")).getText();
    String price = row.findElement(By.xpath("(./td)[3]")).getText();

    // Print the extracted data
    System.out.println("The data you requested for row number-" + rowNumber + ", is as follows:");
    System.out.println("Row number-" + rowNumber);
    System.out.println("Instructor: " + instructor);
    System.out.println("Course: " + course);
    System.out.println("Price: " + price);

    return driver;
  }

  public static WebDriver webTableFixHead(WebDriver driver, int rowNumber) {
    List<WebElement> rows = driver.findElements(By.xpath("//div[@class='tableFixHead']/table[@id='product']/tbody/tr"));

    // Check if rowNumber is valid
    if (rowNumber <= 0 || rowNumber > rows.size()) {
      System.out.println("Invalid row number!");
      return driver;
    }

    // Get the row at the given number (index 0 is not header, it is data)
    WebElement row = rows.get(rowNumber - 1);

    // Extract the data from the row
    String name = row.findElement(By.xpath("(./td)[1]")).getText();
    String position = row.findElement(By.xpath("(./td)[2]")).getText();
    String city = row.findElement(By.xpath("(./td)[3]")).getText();
    String amount = row.findElement(By.xpath("(./td)[4]")).getText();

    // Print the extracted data
    System.out.println(
        "This is the fixed head table. The data you requested for row number-" + rowNumber + ", is as follows:");
    System.out.println("Fix head table row number-" + rowNumber);
    System.out.println("Name: " + name);
    System.out.println("Position: " + position);
    System.out.println("City: " + city);
    System.out.println("Amount: " + amount);

    String totalAmount = driver.findElement(By.xpath("//div[@class='right-align']//div[@class='totalAmount']"))
        .getText();
    int extractedNumber = Integer.parseInt(totalAmount.split(": ")[1]);
    System.out.println("Total amount collected from the fixed table is: "
        + extractedNumber);

    return driver;
  }

  public static WebDriver mouseHover(WebDriver driver) throws InterruptedException {
    WebElement mouseHover = driver.findElement(By.id("mousehover"));
    WebElement topButton = mouseHover.findElement(By.xpath("./parent::div/div/a[text()='Top']"));
    WebElement reloadButton = mouseHover.findElement(By.xpath("./parent::div/div/a[text()='Reload']"));
    // anchoring the view
    WebElement anchorElement = driver.findElement(By.xpath("//legend[text()='iFrame Example']"));

    Actions actions = new Actions(driver); // declare new instance to make selenium do some actions

    // hover to mousehover then click top
    actions.moveToElement(anchorElement).perform(); // anchor purpose, to see better
    Thread.sleep(1000);
    actions.moveToElement(mouseHover).perform();
    Thread.sleep(1000);
    actions.click(topButton).perform();
    Thread.sleep(1000);

    // hover to mousehover then click top
    actions.moveToElement(anchorElement).perform(); // anchor purpose, to see better
    Thread.sleep(1000);
    actions.moveToElement(mouseHover).perform();
    Thread.sleep(1000);
    actions.click(topButton).perform();
    Thread.sleep(1000);

    // hover to mousehover then click reload
    actions.moveToElement(anchorElement).perform(); // anchor purpose, to see better
    Thread.sleep(1000);
    actions.moveToElement(mouseHover).perform();
    Thread.sleep(1000);
    actions.click(reloadButton).perform();
    Thread.sleep(2000);

    return driver;
  }

}
