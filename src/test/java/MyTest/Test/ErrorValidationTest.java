package MyTest.Test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import MyTest.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {

	@Test (groups= {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException {
		landingPage.loginApplication("asdf1@xyz.com", "Asdfasdf123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());		
	}
	
	@Test
	public void ProductErrorValidation() throws IOException {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("asdf1@xyz.com", "Asdfasdf12");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage(); 
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33"); // Wrong product
		Assert.assertFalse(match);  // We are checking false value here
	
		
	}
	
}
