package com.automation.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.automation.config.WebConstants;

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
		log.info("Load Object Repository from "+WebConstants.OR_PROP_PATH);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(WebConstants.OR_PROP_PATH);
			prop.load(inputStream);
			
		/*	for ( Entry<Object, Object> entry : prop.entrySet()) {
	            map.put((String) entry.getKey(), (String) entry.getValue());
			}	*/	
		} catch (IOException e) {
			log.error("Exception occurred while loading property file from "+WebConstants.OR_PROP_PATH+"  --> "+e.getMessage());
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
        log.info("Prop value ->>   "+locator);
        // extract the locator type and value from the object  
        String[] propValueArr = locator.split("\\|");
        String locatorType = propValueArr[0];
        String locatorValue = propValueArr[1];
         
        log.debug("Retrieving object of type '" + locatorType + "' and value '" + locatorValue);
         
        if(locatorType.toLowerCase().equals(WebConstants.ID_LOCATOR))
            return By.id(locatorValue);
        else if(locatorType.toLowerCase().equals(WebConstants.NAME_LOCATOR))
            return By.name(locatorValue);
        else if((locatorType.toLowerCase().equals(WebConstants.CLASS_NAME_LOCATOR)) || (locatorType.toLowerCase().equals(WebConstants.SHORT_CLASS_NAME_LOCATOR)))
            return By.className(locatorValue);
        else if((locatorType.toLowerCase().equals(WebConstants.TAGNAME_LOCATOR)) || (locatorType.toLowerCase().equals(WebConstants.SHORT_TAGNAME_LOCATOR)))
            return By.tagName(locatorValue);
        else if((locatorType.toLowerCase().equals(WebConstants.LINK_TEXT_LOCATOR)) || (locatorType.toLowerCase().equals(WebConstants.SHORT_LINK_TEXT_LOCATOR)))
            return By.linkText(locatorValue);
        else if(locatorType.toLowerCase().equals(WebConstants.PARTIAL_LINK_TEXT_LOCATOR))
            return By.partialLinkText(locatorValue);
        else if((locatorType.toLowerCase().equals(WebConstants.CSS_LOCATOR)) || (locatorType.toLowerCase().equals(WebConstants.SHORT_CSS_LOCATOR)))
            return By.cssSelector(locatorValue);
        else if(locatorType.toLowerCase().equals(WebConstants.XPATH_LOCATOR))
            return By.xpath(locatorValue);
        else
            throw new Exception("Illegal locator '" + locatorType + "'");
    }
}
