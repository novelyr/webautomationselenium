package task.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webautomation.abstractcomponent.AbstractComponent;

public class ProductListPage extends AbstractComponent {
  WebDriver driver;
  WebElement product;

  public ProductListPage(WebDriver driver) {
    super(driver);
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  @FindBy(css = ".inventory_item")
  List<WebElement> listProducts;

  @FindBy(id = "shopping_cart_container")
  WebElement cartOrderButton;

  By cartButton = By.cssSelector(".btn_inventory");
  By titleProduct = By.cssSelector(".inventory_item_label a");
  By listElement = By.cssSelector(".inventory_item");
  By cartOrderBy = By.id("shopping_cart_container");

  public List<WebElement> getProductList() {
    return listProducts;
  }

  public WebElement getProductByName(String productName) {
    WebElement product = getProductList().stream()
        .filter(prod -> prod.findElement(titleProduct).getText().equals(productName)).findFirst().orElse(null);

    return product;
  }

  public void addToCart(String productName) throws InterruptedException {
    visibilityOfElementLocated(listElement);
    product = getProductByName(productName);
    product.findElement(cartButton).click();
    Thread.sleep(500);
  }

  public void goToCart() throws InterruptedException {
    visibilityOfElementLocated(cartOrderBy);
    cartOrderButton.click();
    Thread.sleep(500);
  }
}