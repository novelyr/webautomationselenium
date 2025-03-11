package task.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webautomation.abstractcomponent.AbstractComponent;

public class OrderPage2 extends AbstractComponent {
  WebDriver driver;

  public OrderPage2(WebDriver driver) {
    super(driver);
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  @FindBy(id = "finish")
  WebElement finishButton;

  By finishBy = By.id("finish");

  @FindBy(css = ".inventory_item_name")
  List<WebElement> listProducts;

  public void finishOrder() {
    visibilityOfElementLocated(finishBy);
    finishButton.click();
  }

  public Boolean verifyCheckoutProduct(String productName) {
    visibilityOfElementLocated(finishBy);
    Boolean match = listProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

    return match;
  }
}