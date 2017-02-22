package com.swframework.util;

import java.util.Base64;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class PasswordEnDecodeUtil {
	
	private static final String userPwd = "Sametime@1";
	private static Logger log = Logger.getLogger(PasswordEnDecodeUtil.class);

	@Test
	public static void encodeString() throws Exception
	{		
	     String base64EncodedString = Base64.getEncoder().encodeToString(userPwd.getBytes("utf-8"));
	 //    log.info("Base64 Encoded String (Basic) :" + base64EncodedString);
	}
	
	@Test
	public static String decodeString(String strToBeDecoded) throws Exception
	{
         // Decode
         String base64DecodedBytes = new String(Base64.getDecoder().decode(strToBeDecoded), "utf-8");		
       //  log.info("Original String: " + base64DecodedBytes);
         return base64DecodedBytes;

	}
}
