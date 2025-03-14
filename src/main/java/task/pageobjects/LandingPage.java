package task.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webautomation.abstractcomponent.AbstractComponent;

//"https://rahulshettyacademy.com/client"
public class LandingPage extends AbstractComponent {
  WebDriver driver;

  public LandingPage(WebDriver driver) {
    super(driver);
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  @FindBy(id = "user-name")
  WebElement userEmailElement;

  @FindBy(id = "password")
  WebElement userPasswordElement;

  @FindBy(id = "login-button")
  WebElement loginBtnElement;

  @FindBy(css = "h3")
  WebElement errorNotification;

  By userName = By.id("user-name");
  By errorNotifBy = By.cssSelector("h3");

  public void loginApplication(String userEmail, String userPassword) {
    visibilityOfElementLocated(userName);
    userEmailElement.sendKeys(userEmail);
    userPasswordElement.sendKeys(userPassword);

    loginBtnElement.click();
  }

  public String errorNotificationText() {
    visibilityOfElementLocated(errorNotifBy);
    return errorNotification.getText();
  }
}
