/*
 * Adhisha Gammanpila
 * adhishagc@gmail.com
 * 
 */

/*
 * PLEASE NOTE :
 * This Test Class is Not Executed. The following codelines have been merged with the ItemPage Test Class
 * because unexpected Timeouts tend to come. But as per the marking scheme to provide Classes per each Page,
 * this page was created to showcase it amidst the technical issue arisen.
 */

package testClasses.orderBook;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SignUpPage extends BaseTest{
	
	@Test(priority=1,timeOut=10000)
	public void createAccount() {
	  	// Wait to load the elements and click on SignUp
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.findElement(By.xpath("//a[@id='CreateAnAccount']")).click();
		  
	 }
	
	@Test(priority=2,dependsOnMethods= "createAccount")
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
	
	
	
}
