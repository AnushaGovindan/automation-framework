package com.automation.actions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.automation.config.Constants;
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
		//js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);
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
			return Constants.KEYWORD_FAIL + " -- unable to navigate";
		}
		return Constants.KEYWORD_PASS;
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
			return Constants.KEYWORD_FAIL + " -- unable to open the url";
		}
		return Constants.KEYWORD_PASS;
	}
	
	
	/**
	 * Find webelement based on Locator Type
	 * @param strLocatorType
	 * @param strLocator
	 * @return WebElement
	 * @throws Exception
	 */
	public WebElement getElementByLocator(String strLocatorType, String strLocatorVal) throws Exception {
	//public WebElement getElementByLocator(String strWebElement) throws Exception {
        
		log.info("Locate the webelement: "+strLocatorVal+" based on locator type: "+strLocatorType);        
       /* String locator = prop.getProperty(strElement); 
        // extract the locator type and value from the object
        String locatorType = locator.split(":")[0];
        String locatorValue = locator.split(":")[1];
         
        log.debug("Retrieving object of type '" + locatorType + "' and value '" + locatorValue);*/
		
	//	DriverBuilder.Instance.findElement(MapObjectRepository.getByLocator(strElement));
         
        if(strLocatorType.toLowerCase().equals("id"))
            return DriverBuilder.Instance.findElement(By.id(strLocatorVal)); //By.id(locatorValue);
        else if(strLocatorType.toLowerCase().equals("name"))
            return DriverBuilder.Instance.findElement(By.name(strLocatorVal));
        else if((strLocatorType.toLowerCase().equals("classname")) || (strLocatorType.toLowerCase().equals("class")))
            return DriverBuilder.Instance.findElement(By.className(strLocatorVal));
        else if((strLocatorType.toLowerCase().equals("tagname")) || (strLocatorType.toLowerCase().equals("tag")))
            return DriverBuilder.Instance.findElement(By.tagName(strLocatorVal));
        else if((strLocatorType.toLowerCase().equals("linktext")) || (strLocatorType.toLowerCase().equals("link")))
            return DriverBuilder.Instance.findElement(By.linkText(strLocatorVal));
        else if(strLocatorType.toLowerCase().equals("partiallinktext"))
            return DriverBuilder.Instance.findElement(By.partialLinkText(strLocatorVal));
        else if((strLocatorType.toLowerCase().equals("cssselector")) || (strLocatorType.toLowerCase().equals("css")))
            return DriverBuilder.Instance.findElement(By.cssSelector(strLocatorVal));
        else if(strLocatorType.toLowerCase().equals("xpath"))
            return DriverBuilder.Instance.findElement(By.xpath(strLocatorVal));
        else
            throw new Exception("Illegal locator '" + strLocatorType + "'");
    }
	
	/**
	 * 
	 * Click on the WebElement based on the Locator Type
	 * @param strLocatorType
	 * @param strLocator
	 * @return
	 * @throws Exception
	 */
	public void clickElementByLocator(String strLocatorType, String strLocatorVal) throws Exception {
        
		log.info("Click on the webelement: "+strLocatorVal+" based on locator type: "+strLocatorType);              
		try{		
			if (strLocatorType.toLowerCase().equals("id"))
				DriverBuilder.Instance.findElement(By.id(strLocatorVal)).click(); // By.id(locatorValue);
			else if (strLocatorType.toLowerCase().equals("name"))
				DriverBuilder.Instance.findElement(By.name(strLocatorVal)).click();
			else if ((strLocatorType.toLowerCase().equals("classname")) || (strLocatorType.toLowerCase().equals("class")))
				DriverBuilder.Instance.findElement(By.className(strLocatorVal)).click();
			else if ((strLocatorType.toLowerCase().equals("tagname")) || (strLocatorType.toLowerCase().equals("tag")))
				DriverBuilder.Instance.findElement(By.tagName(strLocatorVal)).click();
			else if ((strLocatorType.toLowerCase().equals("linktext")) || (strLocatorType.toLowerCase().equals("link")))
				DriverBuilder.Instance.findElement(By.linkText(strLocatorVal)).click();
			else if (strLocatorType.toLowerCase().equals("partiallinktext"))
				DriverBuilder.Instance.findElement(By.partialLinkText(strLocatorVal)).click();
			else if ((strLocatorType.toLowerCase().equals("cssselector")) || (strLocatorType.toLowerCase().equals("css")))
				DriverBuilder.Instance.findElement(By.cssSelector(strLocatorVal)).click();
			else if (strLocatorType.toLowerCase().equals("xpath"))
				DriverBuilder.Instance.findElement(By.xpath(strLocatorVal)).click();
		} catch (Exception e) {
			log.error("Unable to click on element using the locator, Exception occurred:  "+e.getMessage());
		}
    }
	
	public void sendKeysByLocator(String strLocatorType, String strLocatorVal, String strInput) throws Exception {
        
		log.info("Locate the webelement: "+strLocatorVal+" based on locator type: "+strLocatorType+" and sendKeys: "+strInput);              
		try{		
			if (strLocatorType.toLowerCase().equals("id"))
				DriverBuilder.Instance.findElement(By.id(strLocatorVal)).sendKeys(strInput); // By.id(locatorValue);
			else if (strLocatorType.toLowerCase().equals("name"))
				DriverBuilder.Instance.findElement(By.name(strLocatorVal)).sendKeys(strInput);
			else if ((strLocatorType.toLowerCase().equals("classname")) || (strLocatorType.toLowerCase().equals("class")))
				DriverBuilder.Instance.findElement(By.className(strLocatorVal)).sendKeys(strInput);
			else if ((strLocatorType.toLowerCase().equals("tagname")) || (strLocatorType.toLowerCase().equals("tag")))
				DriverBuilder.Instance.findElement(By.tagName(strLocatorVal)).sendKeys(strInput);
			else if ((strLocatorType.toLowerCase().equals("linktext")) || (strLocatorType.toLowerCase().equals("link")))
				DriverBuilder.Instance.findElement(By.linkText(strLocatorVal)).sendKeys(strInput);
			else if (strLocatorType.toLowerCase().equals("partiallinktext"))
				DriverBuilder.Instance.findElement(By.partialLinkText(strLocatorVal)).sendKeys(strInput);
			else if ((strLocatorType.toLowerCase().equals("cssselector")) || (strLocatorType.toLowerCase().equals("css")))
				DriverBuilder.Instance.findElement(By.cssSelector(strLocatorVal)).sendKeys(strInput);
			else if (strLocatorType.toLowerCase().equals("xpath"))
				DriverBuilder.Instance.findElement(By.xpath(strLocatorVal)).sendKeys(strInput);
		} catch (Exception e) {
			log.error("Unable to click on element using the locator, Exception occurred:  "+e.getMessage());
		}
    }
	
	/**
	 * Retrieve text for the WebElement based on Locator Type
	 * @param strLocatorType
	 * @param strLocator
	 * @return
	 * @throws Exception
	 */
	public String getTextByLocator(String strLocatorType, String strLocatorVal) throws Exception {
        
		log.info("Locate the webelement: "+strLocatorVal+" based on locator type: "+strLocatorType);  
		String strText = "";
		try{		
			if (strLocatorType.toLowerCase().equals("id"))
				strText = DriverBuilder.Instance.findElement(By.id(strLocatorVal)).getText(); // By.id(locatorValue);
			else if (strLocatorType.toLowerCase().equals("name"))
				strText = DriverBuilder.Instance.findElement(By.name(strLocatorVal)).getText();
			else if ((strLocatorType.toLowerCase().equals("classname")) || (strLocatorType.toLowerCase().equals("class")))
				strText = DriverBuilder.Instance.findElement(By.className(strLocatorVal)).getText();
			else if ((strLocatorType.toLowerCase().equals("tagname")) || (strLocatorType.toLowerCase().equals("tag")))
				strText = DriverBuilder.Instance.findElement(By.tagName(strLocatorVal)).getText();
			else if ((strLocatorType.toLowerCase().equals("linktext")) || (strLocatorType.toLowerCase().equals("link")))
				strText = DriverBuilder.Instance.findElement(By.linkText(strLocatorVal)).getText();
			else if (strLocatorType.toLowerCase().equals("partiallinktext"))
				strText = DriverBuilder.Instance.findElement(By.partialLinkText(strLocatorVal)).getText();
			else if ((strLocatorType.toLowerCase().equals("cssselector")) || (strLocatorType.toLowerCase().equals("css")))
				strText = DriverBuilder.Instance.findElement(By.cssSelector(strLocatorVal)).getText();
			else if (strLocatorType.toLowerCase().equals("xpath"))
				strText = DriverBuilder.Instance.findElement(By.xpath(strLocatorVal)).getText();
		} catch (Exception e) {
			log.error("Exception occurred:  "+e.getMessage());
			return Constants.KEYWORD_FAIL + " -- Unable to retreive text of the WebElement using the locator ";
		}
		return strText;
    }
	
	/**
	 * Verify if the given webelement based on locator type is displayed
	 * @param strLocatorType
	 * @param strLocator
	 * @return
	 * @throws Exception
	 */	
	public boolean isWebelementDisplayed(String strLocatorType, String strLocatorVal) throws Exception {
        
		log.info("Locate the webelement: "+strLocatorVal+" based on locator type: "+strLocatorType); 
		boolean elemDisplayed = false ;
		try{		
			if (strLocatorType.toLowerCase().equals("id"))
				elemDisplayed = DriverBuilder.Instance.findElement(By.id(strLocatorVal)).isDisplayed(); // By.id(locatorValue);
			else if (strLocatorType.toLowerCase().equals("name"))
				elemDisplayed = DriverBuilder.Instance.findElement(By.name(strLocatorVal)).isDisplayed();
			else if ((strLocatorType.toLowerCase().equals("classname")) || (strLocatorType.toLowerCase().equals("class")))
				elemDisplayed = DriverBuilder.Instance.findElement(By.className(strLocatorVal)).isDisplayed();
			else if ((strLocatorType.toLowerCase().equals("tagname")) || (strLocatorType.toLowerCase().equals("tag")))
				elemDisplayed = DriverBuilder.Instance.findElement(By.tagName(strLocatorVal)).isDisplayed();
			else if ((strLocatorType.toLowerCase().equals("linktext")) || (strLocatorType.toLowerCase().equals("link")))
				elemDisplayed = DriverBuilder.Instance.findElement(By.linkText(strLocatorVal)).isDisplayed();
			else if (strLocatorType.toLowerCase().equals("partiallinktext"))
				elemDisplayed = DriverBuilder.Instance.findElement(By.partialLinkText(strLocatorVal)).isDisplayed();
			else if ((strLocatorType.toLowerCase().equals("cssselector")) || (strLocatorType.toLowerCase().equals("css")))
				elemDisplayed = DriverBuilder.Instance.findElement(By.cssSelector(strLocatorVal)).isDisplayed();
			else if (strLocatorType.toLowerCase().equals("xpath"))
				elemDisplayed = DriverBuilder.Instance.findElement(By.xpath(strLocatorVal)).isDisplayed();
		} catch (Exception e) {
			log.error("Unable to locate Webelement, Exception occurred:  "+e.getMessage());
			return elemDisplayed ;
		}
		return elemDisplayed;
    }
	
	/**
	 * Verify if the given webelement based on locator type is enabled
	 * @param strLocatorType
	 * @param strLocator
	 * @return
	 * @throws Exception
	 */	
	public boolean isWebelementEnabled(String strLocatorType, String strLocatorVal) throws Exception {
        
		log.info("Locate the webelement: "+strLocatorVal+" based on locator type: "+strLocatorType); 
		boolean elemEnabled = false ;
		try{		
			if (strLocatorType.toLowerCase().equals("id"))
				elemEnabled = DriverBuilder.Instance.findElement(By.id(strLocatorVal)).isEnabled(); // By.id(locatorValue);
			else if (strLocatorType.toLowerCase().equals("name"))
				elemEnabled = DriverBuilder.Instance.findElement(By.name(strLocatorVal)).isEnabled();
			else if ((strLocatorType.toLowerCase().equals("classname")) || (strLocatorType.toLowerCase().equals("class")))
				elemEnabled = DriverBuilder.Instance.findElement(By.className(strLocatorVal)).isEnabled();
			else if ((strLocatorType.toLowerCase().equals("tagname")) || (strLocatorType.toLowerCase().equals("tag")))
				elemEnabled = DriverBuilder.Instance.findElement(By.tagName(strLocatorVal)).isEnabled();
			else if ((strLocatorType.toLowerCase().equals("linktext")) || (strLocatorType.toLowerCase().equals("link")))
				elemEnabled = DriverBuilder.Instance.findElement(By.linkText(strLocatorVal)).isEnabled();
			else if (strLocatorType.toLowerCase().equals("partiallinktext"))
				elemEnabled = DriverBuilder.Instance.findElement(By.partialLinkText(strLocatorVal)).isEnabled();
			else if ((strLocatorType.toLowerCase().equals("cssselector")) || (strLocatorType.toLowerCase().equals("css")))
				elemEnabled = DriverBuilder.Instance.findElement(By.cssSelector(strLocatorVal)).isEnabled();
			else if (strLocatorType.toLowerCase().equals("xpath"))
				elemEnabled = DriverBuilder.Instance.findElement(By.xpath(strLocatorVal)).isEnabled();
		} catch (Exception e) {
			log.error("Unable to locate Webelement, Exception occurred:  "+e.getMessage());
			return elemEnabled ;
		}
		return elemEnabled;
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
			return Constants.KEYWORD_FAIL + " -- unable to capture screenshot";
		}
		return Constants.KEYWORD_PASS;
			
	}
	
	
}

