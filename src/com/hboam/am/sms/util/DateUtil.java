package com.hboam.am.sms.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static  String getBeginDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date  = sdf.format(new Date())+" 00:00:00";
		return date;
	}
	
	public static  String getEndDate(){
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date  = sdf2.format(new Date());
		return date;
	}
}
