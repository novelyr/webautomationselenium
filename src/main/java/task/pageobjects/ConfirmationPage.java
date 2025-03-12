package task.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webautomation.abstractcomponent.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
  WebDriver driver;

  public ConfirmationPage(WebDriver driver) {
    super(driver);
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  @FindBy(css = "h2.complete-header")
  WebElement confirmationPage;

  By elementHeroText = By.cssSelector("h2.complete-header");

  public String getConfirmationPageText() {
    visibilityOfElementLocated(elementHeroText);
    return confirmationPage.getText();
  }
}