package seleniumframework.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Selenium.CartPage;
import Selenium.orderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
	}
    
    @FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
    
    @FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated((findBy)));
		
	}
	
	public void waitForWebElementToAppear(WebElement ele) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
		
	}
	
	//Click on cart icon on header
	public CartPage goToCartPage() {
		cartHeader.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	
	public orderPage goToOrdersPage() {
		orderHeader.click();
		orderPage orderPage=new orderPage(driver);
		return orderPage;
	}
	
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void clickThroughJs(WebElement element) {
		JavascriptExecutor executor  = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();",element);
	}

}
