package Selenium;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Selenium.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void submitOrder() throws IOException {
		
		
		String productName="ZARA COAT 3";
	    ProductCatalogue productCatalogue=loginPage.loginApplication("nimitkhanna7@gmail.com", "Marriage1234#");
	    Assert.assertEquals("Incorrect email or password.", loginPage.getErrorMessage());
		}
	
	@Test
	public void productErrorValidation() throws IOException {
		
		
		String productName="ZARA COAT 3";
	    ProductCatalogue productCatalogue=loginPage.loginApplication("nimitkhanna7@gmail.com", "Selenium@123");
		
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
		
		boolean match=cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
