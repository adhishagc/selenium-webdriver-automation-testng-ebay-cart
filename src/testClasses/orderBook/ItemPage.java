/*
 * Adhisha Gammanpila
 * adhishagc@gmail.com
 * 
 */
package testClasses.orderBook;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ItemPage extends BaseTest {
	String book;
	String priceItem;
	
	   @Test(priority=1,timeOut=20000)
	   public void verifyBook() {
		   //Implicit wait to load elements
		   driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		   
		   //Fetch Book Details
		   book = driver.findElement(By.id("itemTitle")).getText();
		   priceItem = driver.findElement(By.id("prcIsum")).getText();
		   
		   //Implicit wait to load elements
		   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		   
		   //Verify Book Name
		   assertTrue(expectedBook.equalsIgnoreCase(book));
		   assertTrue(priceItem.contains(price));
		   
		   //Sync time
		   syncThreadSleep(5000);
		   
		   //Verified Items
		   System.out.println("\n====== Book Information Verified & Printed ==========\n");
		   System.out.println("Actual Book " + book);
		   System.out.println("Expected Book " + bookName);
		   System.out.println("Actual Price " + priceItem);
		   System.out.println("Expected Price " + price);
			  
		   System.out.println("\n================\n");
		   
	   }
	  
	  
	
	@Test(priority=2, dependsOnMethods="verifyBook")
	  public void verifyCondition() {
		  
		  //Book condition is retrieved
		  String condition = driver.findElement(By.id("vi-itm-cond")).getText();
		  
		  //Condition is verified
		  assertTrue(condition.equalsIgnoreCase("Brand New"));
		  
		  //Condition printed if Assertion is true
		  System.out.println("Condition : " + condition);
		  
		  //Sync time
		  syncThreadSleep(5000);
		 
	  }
	  
	@Test(priority=3,dependsOnMethods="verifyCondition")
	  public void verifyShippingFee() {
		  
		  //Shipping Fee and Rates fetched
		  String shippingFee = driver.findElement(By.xpath("//span[@id='fshippingCost']")).getText();
		  String rates  = driver.findElement(By.xpath("//span[@id='fShippingSvc']")).getText();
		  
		  //Sync time
		  syncThreadSleep(5000);
		  
		  //Shipping Fee is verified Only
		  assertTrue(shippingFee.equalsIgnoreCase("Free"));
		  
		  System.out.println("Shipping Fee : " + shippingFee + " | " + rates +"\n\n");

	  }
	  
	  @Test(priority=4,dependsOnMethods="verifyShippingFee")
	  public void addToCart() {
		  //Sync
		  syncThreadSleep(3000);
		  
		  //Click on Add to Cart
		  driver.findElement(By.id("isCartBtn_btn")).click();

	  }
	  
	  /* -----------CARTPAGE CODE STARTS HERE-------------- */
	  
	  @Test(priority=5)
	  public void addQty(){
		  
		  //Sync time
		  syncThreadSleep(5000);
		  
		  Select Qty = new Select(driver.findElement(By.id("s1-8-annpeppa-0-1871014894-302896318668-qty[]-1-dropdown")));
		  
		  //Quantity selected by Value
		  Qty.selectByValue(expectedPurchaseQty);
		
		  //Sync time
		  syncThreadSleep(5000);

		  
	  }
	  
	 @Test(priority=6,dependsOnMethods= "addQty")
	  public void verifyQty() {
		 /*
		  * Updated Quantity is fetched back again and asserted
		  */
		  Select Qty = new Select(driver.findElement(By.id("s1-8-annpeppa-0-1871014894-302896318668-qty[]-1-dropdown")));
		  
		  //Fetch the Selected / updated Quantity
		  WebElement item = Qty.getFirstSelectedOption();
		  String itemText = item.getText();

		  Assert.assertEquals(itemText, expectedPurchaseQty);
	  }
	  
	  @Test(priority=7,dependsOnMethods= "verifyQty")
	  public void verifyUpdatedPrice() {
		  
		  //Updated price fetched
		  String price = driver.findElement(By.xpath("//div[@class='item-price']")).getText();
		  
		  //The price is mentioned in the format of US $2.00, therefore US $ is removed and converted to double
		  double actualUpdatedPrice = Double.parseDouble(price.substring(4));
		  
		  //Calculation for the expected based on the hardcoded details
		  double expectedTotalPrice = priceValue*Double.parseDouble(expectedPurchaseQty);
		  
		  //Verification
		  assertTrue(expectedTotalPrice == actualUpdatedPrice);
		 
	  }
	  
	  @Test(priority=8,dependsOnMethods= "verifyUpdatedPrice",timeOut=10000)
	  public void signIn() {
		 //Sync
		  syncThreadSleep(3000);
		  
		 //Click on Sign In
		 driver.findElement(By.xpath("//a[contains(text(),'Sign in')]")).click();
		  
	  }
	  
	  /* -----------CARTPAGE CODE ENDS HERE-------------- */
	  
	  
	  /* -----------CREATE ACCOUNT CODE STARTS HERE-------------- */
	  
	  @Test(priority=9,timeOut=10000)
		public void createAccount() {
		  	// Wait to load the elements and click on SignUp
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 driver.findElement(By.xpath("//a[@id='CreateAnAccount']")).click();
			  
		 }
		
		@Test(priority=10,dependsOnMethods= "createAccount")
		@Parameters({"firstname","lastname","email","password"})
		  public void fillForm(String firstname, String lastname, String email, String password) {
			  // Form filling to Create the Account
			  driver.findElement(By.id("firstname")).sendKeys(firstname);
			  driver.findElement(By.id("lastname")).sendKeys(lastname);
			  driver.findElement(By.id("email")).sendKeys(email);
			  driver.findElement(By.id("PASSWORD")).sendKeys(password);
			  
			  WebElement checkBox = driver.findElement(By.xpath("//li[@role='presentation']"));
			  
			  //Check box is clicked only if its unchecked at first
			  if(!checkBox.isSelected()) {
				  checkBox.click();
			  }
			  
			  
		  }
		
		
		/* -----------CREATE ACCOUNT CODE ENDS HERE-------------- */
	  
	 
}
