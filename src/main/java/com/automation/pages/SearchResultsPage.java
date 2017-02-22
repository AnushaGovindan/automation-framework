package com.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage {
	
	@FindBy(css="div[id*='display-location']")
	WebElement searchResultsDisplayedLocation;
	
	public String getDisplayedLocation(){
		return searchResultsDisplayedLocation.getText();
	}
	
}
