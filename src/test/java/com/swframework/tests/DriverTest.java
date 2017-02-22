package com.swframework.tests;

import org.testng.annotations.Test;

import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import com.automation.actions.DriverBuilder;
import com.automation.config.MapObjectRepository;
import com.swframework.config.WebConstants;


public class DriverTest extends BaseTest {
	// http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=08c1691d32c1dba2f5bfa6c3a2f2c79f
	// http://functionaltestautomation.blogspot.com/2009/10/dataprovider-data-driven-testing-with.html

	private static Logger log = Logger.getLogger(DriverTest.class);

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
			
		//	new Actions(DriverBuilder.Instance).moveToElement(drActions.getElementByLocator(WebConstants.CSS_LOCATOR, "input[id$='username']")).click();
			//drActions.clickElementByLocator(WebConstants.CSS_LOCATOR, "input[id$='username']");
			drActions.sendKeysByLocator(WebConstants.CSS_LOCATOR, "input[id$='username']", "");
			drActions.sendKeysByLocator(WebConstants.CSS_LOCATOR, "input[id$='password']", "");
			drActions.clickElementByLocator(WebConstants.CSS_LOCATOR, "button.submit");

			Thread.sleep(5000);			
		//	new Actions(DriverBuilder.Instance).moveToElement(drActions.getElementByLocator(WebConstants.CSS_LOCATOR, "div[id$='dialog-body']")).sendKeys(Keys.ENTER);
			drActions.clickElementByLocator(WebConstants.CSS_LOCATOR, "a[id$='dialog-close'] > svg:nth-child(1) > path:nth-child(2)");
			drActions.clickElementByLocator(WebConstants.CSS_LOCATOR, ".profile-icon");

			String strLoggedInUser = drActions.getTextByLocator(WebConstants.CSS_LOCATOR, "div.subtitle:nth-child(1)");
			log.info("user logged in is >>>>>   " + strLoggedInUser);
			// Assert.assertEquals(strLoggedInUser.toLowerCase().contains(""), true);

		} catch (Exception e) {
			log.error("Exception occurred during sign in ::  " + e.getMessage());
		}
	}

	@Test(priority = 3)
	public void searchDestination() {
		log.info("Search for the given destination ");
		try {
			drActions.clickElementByLocator(WebConstants.CSS_LOCATOR, "span[id*='traveler-leisure']");
			/*Thread.sleep(3000);
			WebElement bookingCB = DriverBuilder.Instance.findElement(By.cssSelector("span.input.checked"));
		    //booking checkbox is selected by default
		    if(bookingCB.isSelected()){
		    	bookingCB.click();
		    }*/
			drActions.clickElementByLocator(WebConstants.CSS_LOCATOR, "input[id$='location']");
			drActions.sendKeysByLocator(WebConstants.CSS_LOCATOR, "input[id$='location']", "");
			drActions.clickElementByLocator(WebConstants.CSS_LOCATOR, "input[id$='location']");			
			
			drActions.clickElementByLocator(WebConstants.CSS_LOCATOR, "input[id*='checkIn']");
			drActions.clickElementByLocator(WebConstants.CSS_LOCATOR, "div[id*='201703'] > div > div.weeks > div:nth-child(2) > div:nth-child(5) > div"); // sendKeys("03/12/2017");
			drActions.clickElementByLocator(WebConstants.CSS_LOCATOR, "input[id*='checkOut']");
			drActions.clickElementByLocator(WebConstants.CSS_LOCATOR, "div[id*='201703'] > div > div.weeks > div:nth-child(4) > div:nth-child(6) > div");
			
		    Thread.sleep(2000);	
		    drActions.clickElementByLocator(WebConstants.CSS_LOCATOR, "button[id*='submit']");
			Thread.sleep(2000);
			String getDisplayLocation = drActions.getTextByLocator(WebConstants.CSS_LOCATOR, "div[id*='display-location']");
			log.info("Searched for location :::   " + getDisplayLocation);
			Assert.assertEquals(getDisplayLocation.toLowerCase().contains(""), true);
			Thread.sleep(3000);

		} catch (Exception e) {
			log.error("Exception occurred while searching :::  " + e.getMessage());
		}
	}

	@Test(priority = 4)
	public void appSignOut() {
		try {
			log.info("sign out from application ");
			
			drActions.clickElementByLocator(WebConstants.CSS_LOCATOR, ".profile-icon");
			drActions.clickElementByLocator(WebConstants.XPATH_LOCATOR, "//span[.='Sign out']");
			Assert.assertEquals(true, true);
			
		} catch (Exception e) {
			log.error("Exception occurred while signing out :::  " + e.getMessage());
			Assert.assertEquals(false, true);
		}		
	}

}
