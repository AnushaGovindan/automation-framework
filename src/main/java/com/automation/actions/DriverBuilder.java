package com.automation.actions;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.automation.config.WebConstants;

/**
 * Class to initialize required browser driver and to close browser window(s)
 * 
 */

public class DriverBuilder {

	private static Logger log = Logger.getLogger(DriverBuilder.class);
	public static WebDriver Instance ;
	// public static String browser = "Firefox";

	/**
	 * Method to initialize required browser driver
	 * 
	 * @param browserName
	 */
	public static void initDriver(String browserName) {
		try {
			log.info("Initializing Driver");
			if (Instance == null) {
				if (browserName.equalsIgnoreCase("Firefox")) {
					log.info("Creating Firefox Driver");
					//System.setProperty("webdriver.gecko.driver","");
					Instance = new FirefoxDriver();
				} else if (browserName.equalsIgnoreCase("Chrome")) {
					log.info("Creating Chrome Driver");
				//System.setProperty("webdriver.chrome.driver", "Chrome/chromedriver");
					Instance = new ChromeDriver();
				}
			}
			log.info("Driver wait time has been set to " + WebConstants.IMPLICIT_WAIT_TIME + " secs.");
			Instance.manage().timeouts().implicitlyWait(WebConstants.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
			Instance.manage().window().maximize();
			
		} catch (Exception e) {
			log.error("Exception occurred while initialising browser driver:: " + e.getMessage());
		}
	}

	/**
	 * Method to close the current browser window
	 */
	public static void closeDriver() {
		try {
			Instance.close();
			Instance = null;
			log.info("Current browser window has been closed.");
		} catch (Exception e) {
			log.error("Exception occurred while closing the current window:: " + e.getMessage());
		}

	}

	/**
	 * method to close all associated browser windows
	 */
	public static void quitDriver() {
		try {
			Instance.quit();
			Instance = null;
			log.info("ALL browser windows has been closed");
		} catch (Exception e) {
			log.error("Exception occurred while closing ALL browser windows:: " + e.getMessage());
		}
	}

}
