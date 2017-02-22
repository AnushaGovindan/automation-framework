package com.swframework.tests;

import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.automation.config.MapObjectRepository;

public class MapObjRepTest {
  @Test
  public void f() {
	  
	  MapObjectRepository.loadPropMap();
	  Map<String, String> localMap = MapObjectRepository.map;
	  
	  for (Entry<String, String> entry : localMap.entrySet()) {
		    System.out.println("Key = " + entry.getKey() + " , Value = " + entry.getValue());
		}
	  
	 System.out.println(localMap.get("loginpage.username.textbox"));
  }
}
