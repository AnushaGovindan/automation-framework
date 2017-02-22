package com.automation.actions;

import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchWindowException;

import com.automation.config.Constants;

public class WindowHandlers {

	public static Logger log = Logger.getLogger(WindowHandlers.class);

	//Move to new window
	public String switchwindow(String object, String data) throws NoSuchWindowException{

		try {
			String mainPageHandle = DriverBuilder.Instance.getWindowHandle();
			log.info("Handle Name ->>> " + mainPageHandle);
			Thread.sleep(3000);

			Set<String> newWindows = DriverBuilder.Instance.getWindowHandles();
			log.info("Total number of windows opened ******>>>>>>>>>... " + newWindows.size());

			String currentWinHandle;
			Iterator<String> iterator = newWindows.iterator();
			while (iterator.hasNext()) {
				currentWinHandle = iterator.next().toString();
				log.info("Current Window ID ->>>>>>>>>>>>>>   " + currentWinHandle);

				if (!currentWinHandle.equals(mainPageHandle)) {
					DriverBuilder.Instance.switchTo().window(currentWinHandle);
					log.info("navigated to new window **************");
				}
			}
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " Unable to Switch Window " + e.getMessage();
		}
		return Constants.KEYWORD_PASS;
	}

	// To move back to parent window

	public String switchwindowback(String object, String data) throws NoSuchWindowException {
		try {
			String winHandleBefore = DriverBuilder.Instance.getWindowHandle();
			DriverBuilder.Instance.close();
			// Switch back to original browser (first window)
			DriverBuilder.Instance.switchTo().window(winHandleBefore);
			// continue with original browser (first window)
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " Unable to Switch to main window " + e.getMessage();
		}
		return Constants.KEYWORD_PASS;
	}
	
	public boolean isAlertPresent() throws NoAlertPresentException{
	    try {
	    	DriverBuilder.Instance.switchTo().alert();
	        return true;
	    } // try
	    catch (Exception e) {
	        return false;
	    } // catch
	}

}
