package com.swframework.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import com.automation.actions.DriverBuilder;
import com.automation.pages.SearchPage;
import com.automation.pages.SearchResultsPage;


public class SearchPageAndResultTest extends BaseTest {

	private static Logger log = Logger.getLogger(SearchPageAndResultTest.class);

	@Test
	public void searchForGivenLocation() {
		log.info("Search for the given destination ");
		try {
			SearchPage searchPage = PageFactory.initElements(DriverBuilder.Instance, SearchPage.class);
			
			String strLocation = "";
			searchPage.searchForHotels(strLocation);
			
			Thread.sleep(3000);
			SearchResultsPage searchResultsPage = PageFactory.initElements(DriverBuilder.Instance, SearchResultsPage.class);
			String getDisplayLocation = searchResultsPage.getDisplayedLocation();
			log.info("Searched for location :::   " + getDisplayLocation);
			
			AssertJUnit.assertEquals(getDisplayLocation.toLowerCase().contains(strLocation), true);
			Thread.sleep(3000);

		} catch (Exception e) {
			log.error("Exception occurred while searching :::  " + e.getMessage());
		}
	}

}
