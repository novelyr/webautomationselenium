package webautomation.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
  WebDriver driver;

  public LandingPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);

  }

  @FindBy(id = "userEmail")
  WebElement userEmailElement;

  @FindBy(id = "userPassword")
  WebElement userPasswordElement;

  @FindBy(className = "login-btn")
  WebElement loginBtnElement;

  public void loginApplication(String userEmail, String userPassword) {
    userEmailElement.sendKeys(userEmail);
    userPasswordElement.sendKeys(userPassword);

    loginBtnElement.click();
  }
}
