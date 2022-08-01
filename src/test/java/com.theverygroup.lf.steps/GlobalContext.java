package com.theverygroup.lf.steps;

import java.util.HashMap;
import java.util.Map;

public class GlobalContext {
	private static Map<String,String> sharedData = new HashMap<String,String>();
	private static Map<String,String> credentials = new HashMap<String,String>();
	
	public static void addToGlobalDataContext(String key, String value) {
		sharedData.put(key, value);
	}
	public static String getGlobalDataContext(String key) {
		return sharedData.get(key);
	}
	public static String getCredentials(String Key) {
		return credentials.get(Key);
	}
	public static void addCredentials(String key,String value) {
		credentials.put(key, value);
	}
}
