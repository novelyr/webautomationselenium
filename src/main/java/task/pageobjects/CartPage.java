package task.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webautomation.abstractcomponent.AbstractComponent;

public class CartPage extends AbstractComponent {
  WebDriver driver;

  public CartPage(WebDriver driver) {
    super(driver);
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  @FindBy(id = "checkout")
  WebElement checkoutButtonElement;

  @FindBy(css = ".inventory_item_name")
  List<WebElement> listProducts;

  By checkoutButton = By.id("checkout");

  public void goToCheckoutPage() {
    visibilityOfElementLocated(checkoutButton);
    checkoutButtonElement.click();
  }

  public Boolean verifyCheckoutProduct(String productName) {
    visibilityOfElementLocated(checkoutButton);
    Boolean match = listProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

    return match;
  }
}