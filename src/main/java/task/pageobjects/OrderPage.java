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

  // @FindBy(xpath = "//span[@class='ng-star-inserted']")
  // List<WebElement> listCountry;

  @FindBy(id = "continue")
  WebElement continueButton;

  By firstNameBy = By.id("first-name");

  public void inputData(String firstName, String lastName, String postalCode) {
    visibilityOfElementLocated(firstNameBy);
    Actions action = new Actions(driver);
    action.sendKeys(firstNameElement, firstName).sendKeys(lastNameElement, lastName)
        .sendKeys(postalCodeElement, postalCode).build().perform();
  }

  // By elementSelectCountry = By.cssSelector("[placeholder = 'Select Country']");
  // By elementListCountry = By.xpath("//span[@class='ng-star-inserted']");

  // public void selectCountry(String destination) {
  // visibilityOfElementLocated(elementSelectCountry);
  // Actions action = new Actions(driver);
  // action.sendKeys(selectCountry, destination).build().perform();

  // visibilityOfElementLocated(elementListCountry);
  // WebElement cont = listCountry.stream().filter(cont2 ->
  // cont2.getText().equalsIgnoreCase(destination)).findFirst()
  // .orElse(null);
  // cont.click();
  // }

  public void continueOrder() {
    continueButton.click();
  }
}