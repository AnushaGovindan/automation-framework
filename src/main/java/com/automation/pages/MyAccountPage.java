package com.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage {		
	
	@FindBy(css = ".profile-icon")
	@CacheLookup
	WebElement profileIcon;
 
	@FindBy(css = "div.subtitle:nth-child(1)")
	@CacheLookup
	WebElement checkLoggedInUser;
 
	@FindBy(xpath = "//span[.='Sign out']")
	@CacheLookup
	WebElement signOutProfile;
	

    public void clickOnProfileIcon(){
    	profileIcon.click();
    }
    
    public String getUserLoggedIn(){
    	return checkLoggedInUser.getText();
    }
    
    public void signOutProfile(){
    	signOutProfile.click();
    }
    
}
