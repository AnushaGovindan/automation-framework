package com.swframework.tests;

import com.automation.actions.WebElementActions;
import com.automation.config.MapObjectRepository;
import com.automation.actions.DriverBuilder;

import org.testng.annotations.BeforeSuite;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;

public class BaseTest {

	private static Logger log = Logger.getLogger(ProfileSignOutTest.class);
	WebElementActions drActions = new WebElementActions();	

	@BeforeSuite
	public void beforeSuite() {
		 MapObjectRepository.loadPropMap();
		 String browserName = "Firefox"; //Chrome
		 
		DriverBuilder.initDriver(browserName);
		log.info("Initialized Driver ");
	}

	@AfterSuite
	public void afterSuite() {
		log.info("executing aftersuite code");
		//DriverBuilder.quitDriver();
	}

}
