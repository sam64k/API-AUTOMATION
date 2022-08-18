package com.theverygroup.lf.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

public class DataGenarator {
	
	public static String getRandomString(int length, Boolean alphaNumeric, Boolean digit) {
		return RandomStringUtils.random(length, alphaNumeric, digit);
	}
	public static String getCurrentDate(String format) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String formatedDate = formatter.format(date);
		return formatedDate;
	}

	public static void main(String args[]) {
		

		System.out.println("Date:"+DataGenarator.getCurrentDate("yyyy-MM-dd'T'hh:mm:ss"));
		
	}
}
