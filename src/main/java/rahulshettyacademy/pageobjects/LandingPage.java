package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	
	//PageFactory
	@FindBy(id ="userEmail")  //driver.findElement(By.id("userEmail"));
	WebElement userEmail;
	
	@FindBy(id ="userPassword")  //driver.findElement(By.id("userPassword"))
	WebElement passwordEle;
	
	@FindBy(id ="login")  //driver.findElement(By.id("login"))
	WebElement submit;
	
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement errorMsg;
	
	
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
	}
	
	
}
