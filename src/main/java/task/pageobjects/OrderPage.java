package task.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webautomation.abstractcomponent.AbstractComponent;

public class OrderPage extends AbstractComponent {
  WebDriver driver;

  public OrderPage(WebDriver driver) {
    super(driver);
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  @FindBy(id = "first-name")
  WebElement firstNameElement;
  @FindBy(id = "last-name")
  WebElement lastNameElement;
  @FindBy(id = "postal-code")
  WebElement postalCodeElement;
  @FindBy(css = ".error-message-container.error")
  WebElement errorContainerElement;

  // @FindBy(xpa= "//span[@class='ng-star-inserted']")
  // List<WebElement> listCountry;

  @FindBy(id = "continue")
  WebElement continueButton;

  By firstNameBy = By.id("first-name");
  By errorContainerBy = By.cssSelector(".error-message-container.error");

  public void inputData(String firstName, String lastName, String postalCode) {
    visibilityOfElementLocated(firstNameBy);
    Actions action = new Actions(driver);
    action.sendKeys(firstNameElement, firstName).sendKeys(lastNameElement, lastName)
        .sendKeys(postalCodeElement, postalCode).build().perform();
  }

  public void continueOrder() {
    continueButton.click();
  }

  public String errorNotificationText() {
    visibilityOfElementLocated(errorContainerBy);
    return errorContainerElement.getText();
  }
}