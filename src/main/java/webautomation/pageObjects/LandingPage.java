package webautomation.pageobjects;

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

  @FindBy(id = "userEmail")
  WebElement userEmailElement;

  @FindBy(id = "userPassword")
  WebElement userPasswordElement;

  @FindBy(className = "login-btn")
  WebElement loginBtnElement;

  By cartButton = By.id("userEmail");

  public void loginApplication(String userEmail, String userPassword) {
    visibilityOfElementLocated(cartButton);
    userEmailElement.sendKeys(userEmail);
    userPasswordElement.sendKeys(userPassword);

    loginBtnElement.click();
  }
}
