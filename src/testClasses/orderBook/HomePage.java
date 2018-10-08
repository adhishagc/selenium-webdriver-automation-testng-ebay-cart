/*
 * Adhisha Gammanpila
 * adhishagc@gmail.com
 * 
 */
package testClasses.orderBook;

import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePage extends BaseTest {


	 @Test (timeOut=15000)
	 public void selectCategory() {
		  
		 //Category selection
		  Select allCategory = new Select(driver.findElement(By.name("_sacat")));
		  
		  //In the inspect the values can change if changed. Therefore visible text is taken
		  allCategory.selectByVisibleText("Books");
		  
		  //Sync
		  syncThreadSleep(5000);
		  
		  //Search Button Click
		  driver.findElement(By.id("gh-btn")).click();
		  
		  //Sync
		  syncThreadSleep(5000);
		  
	 }
	
}
