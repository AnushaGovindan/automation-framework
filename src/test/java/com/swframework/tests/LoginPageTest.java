package com.swframework.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import com.automation.actions.DriverBuilder;
import com.automation.pages.LoginPage;
import com.automation.pages.MyAccountPage;

public class LoginPageTest extends BaseTest{

	private static Logger log = Logger.getLogger(LoginPageTest.class);

	@Test(priority = 1)
	public void doCheckPageTitle() {
		
		String appUrl = "http://www.kayak.com";
		String gotURl = drActions.getUrl(appUrl);
		log.info(" Opened  appln :::::::  " + gotURl);
		
		String strPageTitle = "kayak";
		String pageTitle = DriverBuilder.Instance.getTitle();
		log.info("Page Title -->>>>>>>>>>>   " + pageTitle);

		AssertJUnit.assertEquals(pageTitle.toLowerCase().contains(strPageTitle), true);
	}

	@Test(priority = 2)
	public void signInAsUser() {
		log.info("*****  Click on SignUp *****");
		try {
			
			LoginPage loginPage = PageFactory.initElements(DriverBuilder.Instance, LoginPage.class);
			
			String userId = "";
			String pswd = "";
			
			loginPage.loginAsUser(userId, pswd);
			Thread.sleep(5000);		
			loginPage.moveToModalDialogBox();		
			
			MyAccountPage myProfileAcc = PageFactory.initElements(DriverBuilder.Instance, MyAccountPage.class);
			myProfileAcc.clickOnProfileIcon();
			drActions.captureScreenshot("MyAccount_Details");
			Thread.sleep(2000);
			
			String strLoggedInUser = myProfileAcc.getUserLoggedIn();
			log.info("user logged in is >>>>>   " + strLoggedInUser);			
			AssertJUnit.assertEquals(strLoggedInUser.toLowerCase().contains(userId), true);

		} catch (Exception e) {
			log.error("Exception occurred during sign in ::  " + e.getMessage());
		}
	}
	
}
