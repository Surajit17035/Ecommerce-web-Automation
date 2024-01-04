package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	//PageFactory
	@FindBy(css =".card")  //driver.findElements(By.cssSelector(".card"));
	List<WebElement>  products;
	
	//driver.findElement(By.cssSelector(".ng-animating"))
	@FindBy(css =".ng-animating")  
	WebElement spinner;
	
	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		waitForElementToAppear(toastMessage);
		waitForElementToDisapear(spinner);
		
		
	}

	
}
