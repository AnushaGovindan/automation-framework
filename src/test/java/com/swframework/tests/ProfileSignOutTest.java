package com.swframework.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import com.automation.actions.DriverBuilder;
import com.automation.pages.MyAccountPage;


public class ProfileSignOutTest extends BaseTest {

	private static Logger log = Logger.getLogger(ProfileSignOutTest.class);

	@Test
	public void profileSignOut() {
		try {
			log.info("sign out from application ");
			MyAccountPage myAccPage = PageFactory.initElements(DriverBuilder.Instance, MyAccountPage.class);
			myAccPage.clickOnProfileIcon();
			myAccPage.signOutProfile();
			
			AssertJUnit.assertEquals(true, true);
			
		} catch (Exception e) {
			log.error("Exception occurred while signing out :::  " + e.getMessage());
			AssertJUnit.assertEquals(false, true);
		}		
	}

}
