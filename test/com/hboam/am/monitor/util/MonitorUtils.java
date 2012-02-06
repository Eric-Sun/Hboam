package com.hboam.am.monitor.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

public class MonitorUtils {
	static Logger logger= LoggerFactory.getLogger("monitor");
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 获得上一个小时的开始时间
	 * @return
	 */
	public static Date getLastBeginHour(){
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY,-1);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}
	
	/**
	 * 获得上一个小时的结束时间
	 * @return
	 */
	public static Date getLastEndHour(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY,-1);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND,59);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}
	
	
	public static boolean inChargeMonitorPeriod(){
//		static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = sdf.format(new Date());
		String nine = s+" 10:00:00";
		String twentyfour = s+" 24:00:00";
		Date nineDate = null;
		Date twentyfourDate = null;
		try {
			nineDate = sdf1.parse(nine);
			twentyfourDate = sdf1.parse(twentyfour);
		} catch (ParseException e) {
			logger.error("error",e);
		}
		Date date = new Date();
		if( date.getTime()<twentyfourDate.getTime() && date.getTime()>nineDate.getTime() )
			return true;
		else 
			return false;
	}

}
