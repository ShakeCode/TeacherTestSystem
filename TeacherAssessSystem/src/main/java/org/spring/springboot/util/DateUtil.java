package org.spring.springboot.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static String pattern = "yyyy-MM-dd";
	
	public static Date reverseStrToDate(String str){
		SimpleDateFormat form = new SimpleDateFormat(pattern);
		Date date = null;
        try {
			date = form.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
}
