package com.swframework.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import com.swframework.util.TestRelatedConstants;

/**
 * Class to load object repository and use it locate webelement
 * @author anusha
 *
 */
public class TestDataProviderUtil {
	
	private static Logger log = Logger.getLogger(TestDataProviderUtil.class);
	public static Properties dataProp = new Properties();
//	public static Map<String,String> dataMap = new HashMap<String,String>();

	/**
	 * Method to load TestData
	 * 
	 */
	public static void loadPropMap () {
		log.info("Load Test Data from "+TestRelatedConstants.TEST_DATA_PROP);		
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(TestRelatedConstants.TEST_DATA_PROP);
			dataProp.load(inputStream);
			
			/*for ( Entry<Object, Object> entry : dataProp.entrySet()) {
				dataMap.put((String) entry.getKey(), (String) entry.getValue());
			}	*/	
		} catch (IOException e) {
			log.error("Exception occurred while loading property file from "+TestRelatedConstants.TEST_DATA_PROP+"  --> "+e.getMessage());
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
	
	/*@DataProvider(name = "testDataProp")
	public Object[][] provideTestData() {
		//Map<String, String> map = loadPropMap();
		return new Object[][] { { dataMap } };
	}*/

}
