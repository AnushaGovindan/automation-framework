package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {	
	
	private WebDriver driver;
	
	@FindBy(xpath = "//input[@id='Kkmk-location']")
	WebElement fromLocation;
 
	@FindBy(xpath = "//select[@id='pswd']")
	WebElement datePicker;
 
	@FindBy(id = "guests")
	WebElement chooseGuest;
	
	@FindBy(id="searchBtn")
	WebElement searchButton;
	 
	@FindBy(id="travelingFor")
	WebElement travelingFor;
	
	@FindBy(id="compareSites")
	WebElement compareSites;
	
	public SearchPage (WebDriver driver){
        this.driver = driver;
    }

    public SearchPage loginAsUser(String user, String dates, String guests){
    	/*fromLocation.sendKeys(user);
    	datePicker.sendKeys(dates);
    	chooseGuest.sendKeys(guests);
    	searchButton.click();*/
        return PageFactory.initElements(driver, SearchPage.class);
    }
	

}
