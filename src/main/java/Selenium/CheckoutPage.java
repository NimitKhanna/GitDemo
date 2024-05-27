package Selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumframework.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
WebDriver driver;

public CheckoutPage(WebDriver driver) {
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
	
}
@FindBy(css="[placeholder='Select Country']")
WebElement country;

@FindBy(css=".action__submit")
WebElement submit;

@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
WebElement selectCountry;

By results = By.xpath("//*[contains(@class,'ta-results')]");




public void selectCountry(String countryName) {
	country.sendKeys(countryName);
	waitForElementToAppear(results);
	clickThroughJs(selectCountry);;
}

public ConfirmationPage submitOrder() {
	clickThroughJs(submit);
	return new ConfirmationPage(driver);
}
}
