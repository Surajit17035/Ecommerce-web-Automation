package MyTest.Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class Standalonetest {

	@Test
	public void test() {
		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", "/Users/maity/eclipse-workspace/Selenium/driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		LandingPage landingPage = new LandingPage(driver);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		String item = "ZARA COAT 3";
		//Login
		driver.findElement(By.id("userEmail")).sendKeys("asdf1@xyz.com");
		driver.findElement(By.id("userPassword")).sendKeys("Asdfasdf12");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card")));
		// find product "Zara Coat 3"
		List<WebElement> products = driver.findElements(By.cssSelector(".card"));
		for(int i=0; i<=products.size(); i++) {
			String productName = driver.findElements(By.cssSelector(".col-md-6 .card .card-body h5 b")).get(i).getText();
			if(productName.equalsIgnoreCase(item)) {
				System.out.println(productName);
				driver.findElement(By.xpath("//button[text()=' Add To Cart']")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
				wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
			}
			break;
			//System.out.println(productName);
		}

		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();   //clicking on the cart button after invisible of animating ui.
		
		// My Cart Page
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(item));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click(); // clicking on the checkout btn
		
		//Payment Method page
		
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-results button:last-child")).click();
		driver.findElement(By.cssSelector(".action__submit")).click(); // clicking on the Place Order btn
		
		
	// Order confirmation page
		String expectedOrderMsg = "THANKYOU FOR THE ORDER.";
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText().trim();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(expectedOrderMsg));
		//System.out.println(confirmMessage);
		driver.close();
	}
}
