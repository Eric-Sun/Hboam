package com.hboam.am.statistics.util;

import java.util.Calendar;
import java.util.Date;

import com.hboam.am.statistics.core.Period;

public class DateUtil {
	
	public static Date getlastBeginDate(Period period){

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		switch (period) {
		case DAY:
			cal.add(Calendar.DAY_OF_YEAR,-1);
			break;
		case WEEK:
			cal.add(Calendar.DAY_OF_YEAR,-1);
			cal.add(Calendar.DAY_OF_WEEK, cal.get(Calendar.DAY_OF_WEEK)*-1+2);
			break;
		case MONTH:
			cal.add(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)*-1+1);
			break;
		}
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}

	public static Date getlastBeginDate(Period period,Date date){

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		switch (period) {
		case DAY:
			cal.add(Calendar.DAY_OF_YEAR,0);
			break;
		case WEEK:
			cal.add(Calendar.DAY_OF_YEAR,-1);
			cal.add(Calendar.DAY_OF_WEEK, cal.get(Calendar.DAY_OF_WEEK)*-1+2);
			break;
		case MONTH:
			cal.add(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)*-1+1);
			break;
		}
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}
	
	public static Date getlastEndDate(Period period){

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		switch (period) {
		case DAY:
			cal.add(Calendar.DAY_OF_YEAR, -1);
			break;
		case WEEK:
			cal.add(Calendar.DAY_OF_YEAR,-1);
			cal.add(Calendar.DAY_OF_WEEK, cal.get(Calendar.DAY_OF_WEEK)*-1+1);
			break;
		case MONTH:
			cal.add(Calendar.DAY_OF_YEAR,-1);
			cal.add(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)*-1+cal.getMaximum(Calendar.DAY_OF_MONTH));
			break;
		}
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE,59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}

	public static Date getEndDate(){

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_YEAR, -1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE,59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}

	
	public static void main(String[] args){
		System.out.println(DateUtil.getlastBeginDate(Period.MONTH));
		System.out.println(DateUtil.getlastEndDate(Period.MONTH));
		
		System.out.println(DateUtil.getlastBeginDate(Period.WEEK));
		System.out.println(DateUtil.getlastEndDate(Period.WEEK));
		
		System.out.println(DateUtil.getlastBeginDate(Period.DAY));
		System.out.println(DateUtil.getlastEndDate(Period.DAY));
	}
	
}
