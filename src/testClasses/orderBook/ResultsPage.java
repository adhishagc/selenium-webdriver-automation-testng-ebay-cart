/*
 * Adhisha Gammanpila
 * adhishagc@gmail.com
 * 
 */
package testClasses.orderBook;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ResultsPage extends BaseTest{
	
	  @Test(priority=1, timeOut=20000) 
	  public void fetchBook() {
		  //Sync time to avoid any element not found exceptions
		  //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		  
		  //Book name fetched
		  bookName = driver.findElement(By.xpath("//a[@title='Click this link to access Harry Potter : The Complete Collection 8 Books in PDF and EPUB Format Ebooκ New']")).getText();
		  //Sync time to avoid any element not found exceptions
		  //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		  
		  //Book Price fetched
		  price = driver.findElement(By.xpath("//li[@id='item46860708cc']//span[@class='bold'][contains(text(),'$1.00')]")).getText();
		  //Sync time to avoid any element not found exceptions
		  //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		  
		  //Book price converted to double
		  priceValue = getPrice(); 
		  
		  //Printing the fetched details
		  System.out.println("\n====== Book Information Fetched ==========\n");
		  System.out.println("Name of the Book : " + bookName);
		  System.out.println("Price of the Book " + price);
		  System.out.println("\n================\n");
		  
		  //Verify the Received Book
		  //assertTrue(expectedBook.equalsIgnoreCase(bookName));
  
	}
	
	private double getPrice() {
		  //The $ mark is removed from the $1.00 price and returned as a double
		  double value = Double.parseDouble(price.substring(1));
		  return value;
	 }
	
	@Test(priority=2,dependsOnMethods="fetchBook")
	public void clickBook() {
		//Sync
		//syncThreadSleep(2000);
		driver.findElement(By.xpath("//a[@title='Click this link to access Harry Potter : The Complete Collection 8 Books in PDF and EPUB Format Ebooκ New']")).click();
		
	}
	
	
}
