package com.swframework.tests;

import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import com.automation.actions.DriverBuilder;


public class DriverTest extends BaseTest {
	// http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=08c1691d32c1dba2f5bfa6c3a2f2c79f
	// http://functionaltestautomation.blogspot.com/2009/10/dataprovider-data-driven-testing-with.html

	private static Logger log = Logger.getLogger(DriverTest.class);

	@Test(priority = 1)
	public void doVerifyPageTitle() {
		String appUrl = "http://www.kayak.com";
		String gotURl = drActions.getUrl(appUrl);
		log.info(" Opened  appln :::::::  " + gotURl);		

		String pageTitle = DriverBuilder.Instance.getTitle();
		log.info("Page Title -->>>>>>>>>>>   " + pageTitle);

		Assert.assertEquals(pageTitle.toLowerCase().contains("kayak"), true);
	}

	@Test(priority = 2)
	public void clickSignUp() {
		log.info("*****  Click on SignUp *****");
		try {
			drActions.clickElementByLocator("homepage.signin.link");

			Thread.sleep(5000);
			// DriverBuilder.Instance.switchTo().activeElement().click();
			// DriverBuilder.Instance.switchTo().frame(1);
			
			new Actions(DriverBuilder.Instance).moveToElement(drActions.getElementByLocator("signin.page.username.txtbox")).click();
			//drActions.sendKeysByLocator("signin.page.username.txtbox", "");
			drActions.sendKeysByLocator("signin.page.username.txtbox", "");
			drActions.sendKeysByLocator("signin.page.password.txtbox", "");
			drActions.clickElementByLocator("signin.page.login.button");

			Thread.sleep(5000);			
			new Actions(DriverBuilder.Instance).moveToElement(drActions.getElementByLocator("after.signin.modal.dialogbox")).sendKeys(Keys.ENTER);
			drActions.clickElementByLocator("close.icon.modal.dialogbox");
			drActions.clickElementByLocator("myaccount.profile.icon");
			
			drActions.captureScreenshot("MyAccount_Details");
			Thread.sleep(2000);

			String strLoggedInUser = drActions.getTextByLocator("myaccount.preferences.loggedin.user");
			log.info("user logged in is >>>>>   " + strLoggedInUser);
			Assert.assertEquals(strLoggedInUser.toLowerCase().contains(""), true);

		} catch (Exception e) {
			log.error("Exception occurred during sign in ::  " + e.getMessage());
		}
	}

	@Test(priority = 3)
	public void searchDestination() {
		log.info("Search for the given destination ");
		try {
			drActions.clickElementByLocator("searchpage.traveler.leisure.radiobutton");
			/*Thread.sleep(3000);
			WebElement bookingCB = DriverBuilder.Instance.findElement(By.cssSelector("span.input.checked"));
		    //booking checkbox is selected by default
		    if(bookingCB.isSelected()){
		    	bookingCB.click();
		    }*/
			drActions.clickElementByLocator("searchpage.where.location.txtbox");
			drActions.sendKeysByLocator("searchpage.where.location.txtbox", "");
			//drActions.clickElementByLocator("searchpage.where.location.txtbox");			
			
			drActions.clickElementByLocator("searchpage.traveldates.checkin.txtbox");
			drActions.clickElementByLocator("searchpage.traveldates.checkin.datepicker"); // sendKeys("03/12/2017");
			drActions.clickElementByLocator("searchpage.traveldates.checkout.txtbox");
			drActions.clickElementByLocator("searchpage.traveldates.checkout.datepicker");
			
		    Thread.sleep(2000);	
		    drActions.clickElementByLocator("searchpage.search.button");
			Thread.sleep(2000);
			String getDisplayLocation = drActions.getTextByLocator("searchresultspage.displayed.where.location");
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
			
			drActions.clickElementByLocator("myaccount.profile.icon");
			drActions.clickElementByLocator("myaccount.profile.signout.button");
			Assert.assertEquals(true, true);
			
		} catch (Exception e) {
			log.error("Exception occurred while signing out :::  " + e.getMessage());
			Assert.assertEquals(false, true);
		}		
	}

}