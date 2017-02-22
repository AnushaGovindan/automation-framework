package com.swframework.tests;

import org.eclipse.jetty.util.log.Log;
import org.testng.annotations.Test;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.automation.config.MapObjectRepository;
import com.swframework.util.TestDataProviderUtil;

public class MapObjRepTest {
  @Test
  public void f() {
	  
	//  MapObjectRepository.loadPropMap();
	  TestDataProviderUtil.loadPropMap();
//	  Map<String, String> localMap = MapObjectRepository.map;
	  
	 /* for (Entry<String, String> entry : localMap.entrySet()) {
		    System.out.println("Key = " + entry.getKey() + " , Value = " + entry.getValue());
		}
	  
	 System.out.println(localMap.get("loginpage.username.textbox"));*/
	  
	  try {
	//	MapObjectRepository.getByLocator("homepage.signin.link");
		 System.out.println(TestDataProviderUtil.dataProp.getProperty("BrowserName"));
		System.out.println(TestDataProviderUtil.dataProp.getProperty("Password"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}
