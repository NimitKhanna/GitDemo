package Selenium;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Selenium.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class submitOrderTest extends BaseTest {
	String productName="ZARA COAT 3";

	@Test(dataProvider = "getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException {
		
		
		
	    ProductCatalogue productCatalogue=loginPage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage=productCatalogue.goToCartPage();
		
		boolean match=cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage=checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
		
		@Test(dependsOnMethods= {"submitOrder"})
			public void orderHistoryTest()
			{
				ProductCatalogue productCatalogue=loginPage.loginApplication("nimitkhanna8@gmail.com", "Selenium@123");
				orderPage ordersPage = productCatalogue.goToOrdersPage();
				Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
			}
		
		@DataProvider
		public Object[][] getData() throws IOException
		{

			List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Selenium//data//PurchaseOrder.json");
			return new Object[][] {{data.get(0)}};
		}
		
		//@DataProvider
		//public Object[][] getData()
		//{
		//	return new Object[][] {{"nimitkhanna8@gmail.com","Marriage123#","ZARA COAT 3"}};
		//}
		
		//public Object[][] getData(){
//		HashMap<String, String> map= new HashMap<String,String>();
//		map.put("email", "nimitkhanna8@gmail.com");
//		map.put("password", "Marriage123#");
//		map.put("productName", "ZARA COAT 3");
		
		
		// If we want 2nd set of test data
		//HashMap<String, String> map1= new HashMap<String,String>();
		//map.put("email", "nimitkhanna7");
		//map.put("password", "askka#");
		//map.put("productName", "ZARA COAT 3");
		

		
		
		
		
	}


