import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class NewTest {
	WebDriver driver ;
	
	//Run test of selected browser
	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) throws Exception{
		if(browser.equalsIgnoreCase("firefox")){
	        System.setProperty("webdriver.gecko.driver","G:\\drivers\\geckodriver.exe");
	        driver = new FirefoxDriver();
		} else if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "G:\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if(browser.equalsIgnoreCase("opera")){
		    System.setProperty("webdriver.opera.driver", "G:\\drivers\\operadriver_win32\\operadriver.exe");
		    driver = new OperaDriver(); 
		} else if(browser.equalsIgnoreCase("IE")){
			System.setProperty("webdriver.ie.driver", "G:\\drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else if(browser.equalsIgnoreCase("msedge")){    
			System.setProperty("webdriver.edge.driver", "G:\\drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		} else {
			throw new Exception("Incorrect Browser");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	//make recharge
	@Test
	public void f() throws InterruptedException {
	  
	    String baseUrl = "https://sanbox.undostres.com.mx/";    
	    
	    //get url
	    driver.get(baseUrl);
	    driver.manage().window().maximize();
	    
	    //select operator and recharge amount
		driver.findElement(By.name("operator")).click();
		driver.findElement(By.xpath("//li[@data-name='telcel']")).click();
		driver.findElement(By.name("mobile")).sendKeys("8465433546");
		driver.findElement(By.name("amount")).click();
		driver.findElement(By.xpath("//li[@data-name='10']")).click();
		driver.findElement(By.xpath("//button[@perform=\"payment\"]")).click();
		Thread.sleep(5000);
		
		//go to payment page
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://sanbox.undostres.com.mx/payment.php");
		
		//select card for payment
		driver.findElement(By.xpath("//div[@id='panel-title-card']//p[text()='Tarjeta']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Usar nueva tarjeta']")).click();
		Thread.sleep(5000);
		
		//Enter details of card
		driver.findElement(By.id("cardnumberunique")).sendKeys("4111111111111111");
		driver.findElement(By.xpath("//input[@placeholder='Mes']")).sendKeys("11");
		driver.findElement(By.xpath("//input[@placeholder='Año']")).sendKeys("25");
		driver.findElement(By.xpath("//input[@placeholder='CVV']")).sendKeys("111");
		driver.findElement(By.name("txtEmail")).sendKeys("test@test.com");
		driver.findElement(By.xpath("//button[@id='paylimit']//span[text()='Pagar con Tarjeta']")).click();
		Thread.sleep(5000);
		
		//login to account
		driver.findElement(By.id("usrname")).sendKeys("automationexcersise@test.com");
		driver.findElement(By.id("psw")).sendKeys("123456");
		driver.findElement(By.id("loginBtn")).click();
		Thread.sleep(5000);
		
	}
	
	//Starting automation test
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Starting cross browser automation test");
	}
	
	//End of Test
	@AfterMethod
	public void afterMethod() {
		 driver.close();
		System.out.println("Finished Test");
	}
}