package com.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage {	
	
	@FindBy(css = "span[id*='traveler-leisure']")
	WebElement travelerLeisureRadioBtn;
 
	@FindBy(css = "span.input.checked")
	WebElement bookingCompareCheckBox;
 
	@FindBy(css = "input[id$='location']")
	WebElement whereLocationTxtBox;
	
	@FindBy(css="input[id*='checkIn']")
	WebElement checkInTxtBox;
	 
	@FindBy(css="div[id*='201703'] > div > div.weeks > div:nth-child(2) > div:nth-child(5) > div")
	WebElement checkInDatePicker;
	
	@FindBy(css="input[id*='checkOut']")
	WebElement checkOutTxtBox;
	
	@FindBy(css="div[id*='201703'] > div > div.weeks > div:nth-child(4) > div:nth-child(6) > div")
	WebElement checkOutDatePicker;
	
	@FindBy(css="button[id*='submit']")
	WebElement searchButton;
	

    public void searchForHotels(String whereLocation){
    	
    	travelerLeisureRadioBtn.click();
    	whereLocationTxtBox.click();
    	whereLocationTxtBox.sendKeys(whereLocation);				
		
    	checkInTxtBox.click();
    	checkInDatePicker.click();
    	checkOutTxtBox.click();
    	checkOutDatePicker.click();    	
		
	    searchButton.click();	
		
    }
	

}
