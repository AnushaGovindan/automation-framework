package com.automation.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.automation.actions.DriverBuilder;

public class LoginPage {	
	
	@FindBy(css = "span.private-deals-label:nth-child(3) > b:nth-child(1)")
	@CacheLookup
	WebElement homepageSignInLink;
	
	@FindBy(css = "input[id$='username']")
	@CacheLookup
	WebElement signInUsername;
 
	@FindBy(css = "input[id$='password']")
	@CacheLookup
	WebElement signInPassword;
 
	@FindBy(css = "button.submit")
	@CacheLookup
	WebElement signInLoginButton;
	
	@FindBy(css = "div[id$='dialog-body']")
	@CacheLookup
	WebElement signInModalDialogBox;
 
	@FindBy(css = "a[id$='dialog-close'] > svg:nth-child(1) > path:nth-child(2)")
	@CacheLookup
	WebElement signInCloseModalDialogBox;
	

    public void loginAsUser(String user, String pswd) throws Exception {    	
    	signInModalDialogBox.click();
    	Thread.sleep(5000);
    	new Actions(DriverBuilder.Instance).moveToElement(signInUsername).click();
    	signInUsername.sendKeys(user);
    	signInPassword.sendKeys(pswd);
    	signInLoginButton.click();	    	
    }
    
    public void moveToModalDialogBox() throws Exception {
    	new Actions(DriverBuilder.Instance).moveToElement(signInModalDialogBox).sendKeys(Keys.ENTER);
    	signInCloseModalDialogBox.click();
    }

}
