package com.automation.actions;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.automation.config.WebConstants;
import com.automation.config.MapObjectRepository;

/**
 * Class contains methods to perform actions on the browser
 * 
 */
public class WebElementActions { 

	private static Logger log = Logger.getLogger(WebElementActions.class);
	
	/**
	 * Highlight WebElement 
	 * @param element
	 */
	public void highLightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) DriverBuilder.Instance;
		
		//js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "background: yellow; border: 2px solid red;");
		log.info("highlighting element with red border");
		/*
		 *  js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 2px solid yellow;");
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		 */
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			log.error("Unable to highlight :  "+e.getMessage());
		}
		//    js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "background: white; border: 2px solid white;");
		log.info("setting element back to white border");
	}

	/**
	 * Navigate to a specific url
	 * 
	 * @param data
	 * @return String
	 */
	public String navigate(String data) {
		log.debug("Navigating to the given URL");
		try {
			DriverBuilder.Instance.navigate().to(data);
		} catch (Exception e) {
			log.error("Unable to navigate to " + data + " Exception occurred: " + e.getMessage());
			return WebConstants.KEYWORD_FAIL + " -- unable to navigate";
		}
		return WebConstants.KEYWORD_PASS;
	}

	/**
	 * Open the given url
	 * 
	 * @param data
	 * @return String
	 */
	public String getUrl(String data) {
		log.debug("Open URL");
		try {
			DriverBuilder.Instance.get(data);
		} catch (Exception e) {
			log.error("Unable to open " + data + " Exception occurred: " + e.getMessage());
			return WebConstants.KEYWORD_FAIL + " -- unable to open the url";
		}
		return WebConstants.KEYWORD_PASS;
	}
	
	
	/**
	 * Find webelement based on Locator Type
	 * @param strWebElement
	 * @return WebElement
	 * @throws Exception
	 */
	public WebElement getElementByLocator(String strWebElement) throws Exception {        
		log.info("Locate the webelement: "+strWebElement);		
		return DriverBuilder.Instance.findElement(MapObjectRepository.getByLocator(strWebElement));
    }
	
	/**
	 * 
	 * Click on the WebElement based on the Locator Type
	 * @param strWebElement
	 * @return
	 * @throws Exception
	 */
	public void clickElementByLocator(String strWebElement) throws Exception {        
		log.info("Click on the webelement: "+strWebElement);    
		DriverBuilder.Instance.findElement(MapObjectRepository.getByLocator(strWebElement)).click();
    }
	
	/**
	 * Method to input text in textbox
	 * @param strWebElement
	 * @param strInput
	 * @throws Exception
	 */
	public void sendKeysByLocator(String strWebElement, String strInput) throws Exception {        
		log.info("Locate the webelement: "+strWebElement+" and sendKeys: "+strInput);    
		DriverBuilder.Instance.findElement(MapObjectRepository.getByLocator(strWebElement)).sendKeys(strInput);
    }
	
	/**
	 * Retrieve text for the WebElement based on Locator Type
	 * @param strWebElement
	 * @return
	 * @throws Exception
	 */
	public String getTextByLocator(String strWebElement) throws Exception {        
		log.info("Locate the webelement: "+strWebElement);  
		return DriverBuilder.Instance.findElement(MapObjectRepository.getByLocator(strWebElement)).getText();
    }
	
	/**
	 * Verify if the given webelement based on locator type is displayed
	 * @param strWebElement
	 * @return
	 * @throws Exception
	 */	
	public boolean isWebelementDisplayed(String strWebElement) throws Exception {        
		log.info("Locate the webelement: "+strWebElement); 
		return DriverBuilder.Instance.findElement(MapObjectRepository.getByLocator(strWebElement)).isDisplayed();
    }
	
	/**
	 * Verify if the given webelement based on locator type is enabled
	 * @param strWebElement
	 * @return
	 * @throws Exception
	 */	
	public boolean isWebelementEnabled(String strWebElement) throws Exception {        
		log.info("Locate the webelement: "+strWebElement); 
		return DriverBuilder.Instance.findElement(MapObjectRepository.getByLocator(strWebElement)).isEnabled();
	 }


	/**
	 * Capture Screenshot for validation
	 * @param filename
	 * @return
	 */
	public String captureScreenshot(String filename) {
		log.debug("Capture Screenshot with given filename");
		try {
			File scrFile = ((TakesScreenshot)DriverBuilder.Instance).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") +"//screenshots//"+filename+".jpg"));
		}catch(Exception e){
			log.error("Exception occurred while capturing screenshot: " + e.getMessage());
			return WebConstants.KEYWORD_FAIL + " -- unable to capture screenshot";
		}
		return WebConstants.KEYWORD_PASS;			
	}
	
	
}

