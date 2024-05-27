package Selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumframework.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
WebDriver driver;

public ProductCatalogue(WebDriver driver) {
	super(driver);// To send driver from child to parent class(AbstractComponent)
	this.driver=driver;
	PageFactory.initElements(driver, this);//this only works on driver.findElements and not on By, that's why we need to create By productsBy so that we can pass it as arguments in waitForElementToAppear
	
	
}

@FindBy(css=".mb-3")
List<WebElement> products;

@FindBy(css=".ng-animating")
WebElement spinner;

By productsBy = By.cssSelector(".mb-3");
By addToCart=By.cssSelector(".card-body button:last-of-type");
By toastMessage=By.cssSelector("#toast-container");

//Get the list of products from PDP page
public List<WebElement> getProductList() {
	waitForElementToAppear(productsBy);//wait for Elements to appear
	return products;// return the list of products on PDP page
}

//Find Element Zara- Coat 3 on PDP page
public WebElement getProductByName(String productName) {
	WebElement prod=getProductList().stream().filter(product ->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	return prod;
}

//Click on add to cart
public void addProductToCart(String productName)
{
	WebElement prod = getProductByName(productName);
	prod.findElement(addToCart).click();
	waitForElementToAppear(toastMessage);//Wait for product added to cart which is toast element which suddenly disappears
	//wait for invisibility of loading icon
	// In CSS with class we use . and with id we use #
	waitForElementToDisappear(spinner);
}
}
