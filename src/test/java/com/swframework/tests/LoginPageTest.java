package com.swframework.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import com.automation.actions.DriverBuilder;
import com.automation.pages.LoginPage;
import com.automation.pages.MyAccountPage;
import com.swframework.util.PasswordEnDecodeUtil;
import com.swframework.util.TestDataProviderUtil;

public class LoginPageTest extends BaseTest{

	private static Logger log = Logger.getLogger(LoginPageTest.class);

	@Test//(priority = 1) // , dataProvider="testDataProp")
	public void doCheckPageTitle() {
		
		String appUrl = TestDataProviderUtil.dataProp.getProperty("ApplicationURL");  //"http://www.kayak.com";
		String validatePageTitleStr = TestDataProviderUtil.dataProp.getProperty("ValidatePageTitle");
		String gotURl = drActions.getUrl(appUrl);
		log.info(" Opened  appln :::::::  " + gotURl);
		
		String pageTitle = DriverBuilder.Instance.getTitle();
		log.info("Page Title -->>>>>>>>>>>   " + pageTitle);
		AssertJUnit.assertEquals(pageTitle.toLowerCase().contains(validatePageTitleStr), true);
	}

	@Test//(priority = 2)
	public void signInAsUser() {
		log.info("*****  Click on SignUp *****");
		try {
			
			LoginPage loginPage = PageFactory.initElements(DriverBuilder.Instance, LoginPage.class);
			
			String userId = TestDataProviderUtil.dataProp.getProperty("LoginUsername"); 
			String pswd = TestDataProviderUtil.dataProp.getProperty("Password"); 
			
			String decodedPswd = PasswordEnDecodeUtil.decodeString(pswd);
			
			loginPage.loginAsUser(userId, decodedPswd);
			Thread.sleep(5000);		
			loginPage.moveToModalDialogBox();		
			
			MyAccountPage myProfileAcc = PageFactory.initElements(DriverBuilder.Instance, MyAccountPage.class);
			myProfileAcc.clickOnProfileIcon();
			
			Thread.sleep(2000);
			
			String strLoggedInUser = myProfileAcc.getUserLoggedIn();
			drActions.captureScreenshot("MyAccount_Details");
			log.info("user logged in is >>>>>   " + strLoggedInUser);			
			AssertJUnit.assertEquals(strLoggedInUser.toLowerCase().contains(userId), true);

		} catch (Exception e) {
			log.error("Exception occurred during sign in ::  " + e.getMessage());
		}
	}
	
}
