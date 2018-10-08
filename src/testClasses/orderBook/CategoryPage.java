/*
 * Adhisha Gammanpila
 * adhishagc@gmail.com
 * 
 */
package testClasses.orderBook;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CategoryPage extends BaseTest {
	
	@BeforeMethod
	public void verifyPage() {
		//Put a Wait to load the Title ToDo
		
		String expectedTitle = "Books for sale | eBay";
		String actualTitle = driver.getTitle();
		
		assertTrue(expectedTitle.equalsIgnoreCase(actualTitle));
		
		//Print Title Length
		printTitleLength();
	}
	
	public void printTitleLength() {
		int length = driver.getTitle().length();
		System.out.println("Title Length is : " + length);
	}
	
	@Test(timeOut=10000)
	@Parameters({"searchBook"})
	  public void searchBook(String searchBook) {
		  driver.findElement(By.id("gh-ac")).sendKeys(searchBook);
		  driver.findElement(By.id("gh-btn")).click();
		  
	  }
}
