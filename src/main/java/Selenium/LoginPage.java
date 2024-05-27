package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumframework.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent{
WebDriver driver;

public LoginPage(WebDriver driver) {
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
	
}
@FindBy(id="userEmail")
WebElement userEmail;

@FindBy(id="userPassword")
WebElement passwordele;

@FindBy(id="login")
WebElement submit;

@FindBy(css="[class*='flyInOut'] ")
WebElement errorMessage;

public ProductCatalogue loginApplication(String email, String password) {
	userEmail.sendKeys(email);
	passwordele.sendKeys(password);
	submit.click();
	ProductCatalogue productCatalogue = new ProductCatalogue(driver);
	return productCatalogue; 
}

public void goTo()
{
	driver.get("https://rahulshettyacademy.com/client/");
}

public String getErrorMessage() {
	waitForWebElementToAppear(errorMessage);
	return errorMessage.getText();
	
}
}
