package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {		
	
	private WebDriver driver;
	
	@FindBy(linkText = "Sign in")
	//@FindBy(type = "Sign in")
	WebElement signInLink;
 
	//id="logo"
	@FindBy(xpath = "//select[@id='search hundreds of hotel sites']")
	WebElement verifyText;
 
	@FindBy(id = "submitbutton")
	WebElement submitBtn;
	
	@FindBy(linkText = "My Account")
	WebElement myAccLink;
	
	public HomePage (WebDriver driver){
        this.driver = driver;
    }

    public HomePage clickOnSignIn(){
    	signInLink.click();
        return PageFactory.initElements(driver, HomePage.class);
    }


}
