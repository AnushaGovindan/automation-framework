package com.swframework.tests;

import com.automation.actions.WebElementActions;
import com.automation.config.MapObjectRepository;
import com.automation.actions.DriverBuilder;

import org.testng.annotations.BeforeSuite;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;

public class BaseTest {

	private static Logger log = Logger.getLogger(DriverTest.class);
	WebElementActions drActions = new WebElementActions();	

	@BeforeSuite
	public void beforeSuite() {
		 MapObjectRepository.loadPropMap();
		// DriverBuilder.initDriver("Chrome");
		DriverBuilder.initDriver("Firefox");

		String gotURl = drActions.getUrl("http://www.kayak.com");
		log.info("BaseTest: Opened  appln :::::::  " + gotURl);
	}

	@AfterSuite
	public void afterSuite() {
		log.info("executing aftersuite code");
		DriverBuilder.quitDriver();
	}

}
