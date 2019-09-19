package com.kejin.mytest.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
public class WxUtil {
	
	public static String getSign(String jsapi_ticket, String noncestr, Long timestamp, String url)
			throws NoSuchAlgorithmException {
		String shaStr = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url="
				+ url;
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		byte[] result = mDigest.digest(shaStr.getBytes());
		StringBuffer signature = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			signature.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}
		return signature.toString();
	}
    
	public static String randomStr(int n) {
		String a = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] rands = new char[n];
		for (int i = 0; i < rands.length; i++) {
			int rand = (int) (Math.random() * a.length());
			rands[i] = a.charAt(rand);
		}
		return new String(rands);
	}
	
	public static void saveImageToDisk(File file,String fileName) throws FileNotFoundException   { 
		InputStream inputStream = new  FileInputStream(file) ; 
	    byte[] data = new byte[10240]; 
	    int len = 0; 
	    FileOutputStream fileOutputStream = null; 
	    try { 
	      fileOutputStream = new FileOutputStream(fileName); 
	      while ((len = inputStream.read(data)) != -1) { 
	        fileOutputStream.write(data, 0, len); 
	      } 
	    } catch (IOException e) { 
	      e.printStackTrace(); 
	    } finally { 
	      if (inputStream != null) { 
	        try { 
	          inputStream.close(); 
	        } catch (IOException e) { 
	          e.printStackTrace(); 
	        } 
	      } 
	      if (fileOutputStream != null) { 
	        try { 
	          fileOutputStream.close(); 
	        } catch (IOException e) { 
	          e.printStackTrace(); 
	        } 
	      } 
	    } 
    }
}
