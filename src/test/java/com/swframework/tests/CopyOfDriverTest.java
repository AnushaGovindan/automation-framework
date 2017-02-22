package com.swframework.tests;

import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import com.automation.actions.DriverBuilder;
import com.swframework.config.WebConstants;


public class CopyOfDriverTest extends BaseTest {
	// http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=08c1691d32c1dba2f5bfa6c3a2f2c79f
	// http://functionaltestautomation.blogspot.com/2009/10/dataprovider-data-driven-testing-with.html

	private static Logger log = Logger.getLogger(CopyOfDriverTest.class);
	//WebElementActions webElemActions = new WebElementActions();

	@Test(priority = 1)
	public void doVerifyPageTitle() {
		log.info("****** verify if url opened  ******");
		String pageTitle = DriverBuilder.Instance.getTitle();
		log.info("Page Title -->>>>>>>>>>>   " + pageTitle);

		Assert.assertEquals(pageTitle.toLowerCase().contains("kayak"), true);
	}

	@Test(priority = 2)
	public void clickSignUp() {
		log.info("*****  Click on SignUp *****");
		try {
			drActions.clickElementByLocator(WebConstants.CSS_LOCATOR, "span.private-deals-label:nth-child(3) > b:nth-child(1)");

			Thread.sleep(5000);
			// DriverBuilder.Instance.switchTo().activeElement().click();
			// DriverBuilder.Instance.switchTo().frame(1);
			
			new Actions(DriverBuilder.Instance).moveToElement(drActions.getElementByLocator(WebConstants.CSS_LOCATOR, "input[id$='username']")).click();
			// DriverBuilder.Instance.findElement(By.cssSelector("input[id$='username']")).click();
			DriverBuilder.Instance.findElement(By.cssSelector("input[id$='username']")).sendKeys("anusha.govindan1@gmail.com");
			DriverBuilder.Instance.findElement(By.cssSelector("input[id$='password']")).sendKeys("Sametime@1");
			DriverBuilder.Instance.findElement(By.cssSelector("button.submit")).click();

			Thread.sleep(5000);
			new Actions(DriverBuilder.Instance).moveToElement(DriverBuilder.Instance.findElement(By.cssSelector("div[id$='dialog-body']"))).sendKeys(Keys.ENTER);
			DriverBuilder.Instance.findElement(By.cssSelector("a[id$='dialog-close'] > svg:nth-child(1) > path:nth-child(2)")).click();
			DriverBuilder.Instance.findElement(By.cssSelector(".profile-icon")).click();

			String strLoggedInUser = DriverBuilder.Instance.findElement(By.cssSelector("div.subtitle:nth-child(1)")).getText();
			log.info("user logged in is >>>>>   " + strLoggedInUser);
			Assert.assertEquals(strLoggedInUser.toLowerCase().contains("anusha"), true);

		} catch (Exception e) {
			log.error("Exception occurred during sign in ::  " + e.getMessage());
		}
	}

	@Test(priority = 3)
	public void searchDestination() {
		log.info("Search for the given destination ");
		try {
			DriverBuilder.Instance.findElement(By.cssSelector("span[id*='traveler-leisure']")).click();
			/*Thread.sleep(3000);
			WebElement bookingCB = DriverBuilder.Instance.findElement(By.cssSelector("span.input.checked"));
		    //booking checkbox is selected by default
		    if(bookingCB.isSelected()){
		    	bookingCB.click();
		    }*/
			
			DriverBuilder.Instance.findElement(By.cssSelector("input[id$='location']")).click();
			DriverBuilder.Instance.findElement(By.cssSelector("input[id$='location']")).sendKeys("Bengaluru, India");
			DriverBuilder.Instance.findElement(By.cssSelector("input[id*='checkIn']")).click();
			DriverBuilder.Instance.findElement(
					By.cssSelector("div[id*='201703'] > div > div.weeks > div:nth-child(2) > div:nth-child(5) > div"))
					.click();
			DriverBuilder.Instance.findElement(By.cssSelector("input[id*='checkOut']")).click(); // sendKeys("03/12/2017");
			DriverBuilder.Instance.findElement(
					By.cssSelector("div[id*='201703'] > div > div.weeks > div:nth-child(4) > div:nth-child(6) > div"))
					.click();		
		
		    Thread.sleep(2000);		    
			DriverBuilder.Instance.findElement(By.cssSelector("button[id*='submit']")).click();
			Thread.sleep(2000);
			String getDisplayLocation = DriverBuilder.Instance.findElement(By.cssSelector("div[id*='display-location']")).getText();
			log.info("Searched for location :::   " + getDisplayLocation);
			Assert.assertEquals(getDisplayLocation.toLowerCase().contains("bengaluru"), true);
			Thread.sleep(3000);

		} catch (Exception e) {
			log.error("Exception occurred while searching :::  " + e.getMessage());
		}
	}

	@Test(priority = 4)
	public void appSignOut() {
		try {
			log.info("sign out from application ");
			DriverBuilder.Instance.findElement(By.cssSelector(".profile-icon")).click();
			// DriverBuilder.Instance.findElement(By.xpath("//label[.='My
			// Account']")).click();
			DriverBuilder.Instance.findElement(By.xpath("//span[.='Sign out']")).click();

			Assert.assertEquals(true, true);

		} catch (Exception e) {
			log.error("Exception occurred while signing out :::  " + e.getMessage());
			Assert.assertEquals(false, true);
		}
	}

}
