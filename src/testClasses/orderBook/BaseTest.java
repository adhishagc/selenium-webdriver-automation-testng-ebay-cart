/*
 * Adhisha Gammanpila
 * adhishagc@gmail.com
 * 
 */
package testClasses.orderBook;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseTest {
	public static WebDriver driver;
	
	//Information taken from the search results page Stored as a string with the currency, price and Name
	public static String price; //Expected in String
	public static double priceValue; // Expected in Double
	public static String expectedBook; // Expected
	public static String bookName;  // Actual
	public static String expectedPurchaseQty; //Expected Quantity
	
	 @BeforeTest
	 @Parameters({"browser","expectedBookS", "priceValueString", "expectedPurchaseQtyString","url"})
	 public void setupTestArea(String browser, String expectedBookS, String priceValueString, String expectedPurchaseQtyString, String url) {
		 /*Initialize variables*/ 
		 expectedBook = expectedBookS;
		 priceValue = Double.parseDouble(priceValueString);
		 expectedPurchaseQty = expectedPurchaseQtyString;
		 
		 
		  //Check the Browser type
		  if(browser.equalsIgnoreCase("chrome")) {
			  //Setting up Browser Environment
			  System.setProperty("webdriver.chrome.driver", "D:\\Adhisha\\chromedriver\\chromedriver.exe");
			  
			  //Create Chrome Instance
			  driver = new ChromeDriver();  
		  }
		  else if(browser.equalsIgnoreCase("ie")) {
			  //Setting up Browser Environment
			  System.setProperty("webdriver.ie.driver", "D:\\Adhisha\\IEDriverServer\\IEDriverServer.exe");
			  
			  /*
			   * When using Internet Explorer please note that the Zoom level should be set to 100%.
			   * By default it can be less than or higher than 100% which may cause the browser driver to
			   * throw and exception.
			   * In order to handle this please open the IE Browser and set the Zoom level to 100%
			   * 
			   * Keyboard shortcut Ctrl + 0
			   */
			  
			  //Create IE Instance
			  driver = new InternetExplorerDriver();
			  
		  }
		  
		  //Sync time
		  syncThreadSleep(5000);
		  
		  //Maxmimize Window
		  driver.manage().window().maximize();
		  
		  //Launch the Website
		  driver.get(url);
		  
		  //Implicit wait to poll the DOM to avoid Element not Found Exceptions
		  driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		 
		  
		  //Get current Url
		  String currentUrl = driver.getCurrentUrl();
		  
		  //Verify whether the Browser is in the expected website
		  Assert.assertEquals(currentUrl, url);
		  
		  
	 }
	 
	 //Sync Thread Sleep
	 public static void syncThreadSleep(int milSec) {
		 
		 /*
		  * A custom Thread sleep function is created here.
		  * This is made in order to avoid the repeated duplicates caused in creating the Thread Sleep
		  */
		 
		 try {
			Thread.sleep(milSec);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 
	 @AfterTest
	 public void ClearAll() {
		 
		 System.out.println("Browser about to Close");
		 System.out.println("...");
		 System.out.println("...");
		 System.out.println("...");
		 
		 //Sync time
		 syncThreadSleep(5000);
		 
		 //Close the Browser
		 driver.close();
		 
		 System.out.println("Browser Closed");
	 }
}
