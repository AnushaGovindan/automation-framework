package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {	
	
	
	private WebDriver driver;
	
	@FindBy(id = "username")
	WebElement userNameField;
 
	@FindBy(xpath = "//select[@id='pswd']")
	WebElement pswdField;
 
	@FindBy(id = "submitbutton")
	WebElement submitBtn;
	
	/*@findBy(id="userName")
	 * WebElement userName;
	 */
	
	public LoginPage (WebDriver driver){
        this.driver = driver;
    }

    public LoginPage loginAsUser(String user, String pw){
    	userNameField.sendKeys(user);
    	pswdField.sendKeys(pw);
    	submitBtn.click();
        return PageFactory.initElements(driver, LoginPage.class);
    }
	
	// Get the User name from Home Page
	public String getHomePageDashboardUserName() {
		return userNameField.getText();
	}

}
