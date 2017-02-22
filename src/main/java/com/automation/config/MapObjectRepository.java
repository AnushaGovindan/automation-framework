package com.automation.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.automation.config.AutoConstants;

/**
 * Class to load object repository and use it locate webelement
 * @author anusha
 *
 */
public class MapObjectRepository {
	
	private static Logger log = Logger.getLogger(MapObjectRepository.class);
	public static Properties prop = new Properties();
	//public static Map<String,String>map = new HashMap<String,String>();

	/**
	 * Method to load obj repository
	 * @param strPath
	 */
	public static void loadPropMap () {
		log.info("Load Object Repository from "+AutoConstants.OR_PROP_PATH);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(AutoConstants.OR_PROP_PATH);
			prop.load(inputStream);
			
		/*	for ( Entry<Object, Object> entry : prop.entrySet()) {
	            map.put((String) entry.getKey(), (String) entry.getValue());
			}	*/	
		} catch (IOException e) {
			log.error("Exception occurred while loading property file from "+AutoConstants.OR_PROP_PATH+"  --> "+e.getMessage());
		}finally {
			if (inputStream != null) {		
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		 
	}


	
	/**
	 * Locate webelement based on the type specified in property file
	 * @param strElement
	 * @return
	 * @throws Exception
	 */
	public static By getByLocator(String strElement) throws Exception {
        
		log.info("Locate the webelement based on locator type");        
        String locator = prop.getProperty(strElement); 
        // extract the locator type and value from the object
        String locatorType = locator.split(":")[0];
        String locatorValue = locator.split(":")[1];
         
        log.debug("Retrieving object of type '" + locatorType + "' and value '" + locatorValue);
         
        if(locatorType.toLowerCase().equals("id"))
            return By.id(locatorValue);
        else if(locatorType.toLowerCase().equals("name"))
            return By.name(locatorValue);
        else if((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
            return By.className(locatorValue);
        else if((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
            return By.tagName(locatorValue);
        else if((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
            return By.linkText(locatorValue);
        else if(locatorType.toLowerCase().equals("partiallinktext"))
            return By.partialLinkText(locatorValue);
        else if((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
            return By.cssSelector(locatorValue);
        else if(locatorType.toLowerCase().equals("xpath"))
            return By.xpath(locatorValue);
        else
            throw new Exception("Illegal locator '" + locatorType + "'");
    }
}
