package Selenium.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Selenium.CartPage;
import Selenium.CheckoutPage;
import Selenium.ConfirmationPage;
import Selenium.LoginPage;
import Selenium.ProductCatalogue;
import Selenium.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	
	public LoginPage loginPage; 
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		loginPage = launchApplication();
	}
	
	@Given("Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password)
	{
		productCatalogue = loginPage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to the cart$")
	public void i_add_product_to_cart(String productname )
	{
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productname);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productname) {
        CartPage cartPage=productCatalogue.goToCartPage();
		
		boolean match=cartPage.verifyProductDisplay(productname);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message displays on ConfirmationPage")
	public void message_displayed_confirmation_message(String message)
	{
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(message));
		driver.quit();
	}
	
	@Then("{string} message is displayed")
	public void error_message_is_displayed(String errorMessage)
	{
		Assert.assertEquals(errorMessage, loginPage.getErrorMessage());
		driver.quit();;
	}
	

}
