package com.hboam.am.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionUtil {
	static Logger logger = LoggerFactory.getLogger("core");
	
	public static  Object reflect(String clazz){
		Object o;
		try {
			o = Class.forName(clazz).newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			
			logger.error("reflect error! clazzName = "+clazz,e);
			return null;
		}
		return o;
	}
	
	
	public static Class getClass(String clazz){
		try {
			return Class.forName(clazz);
		} catch (ClassNotFoundException e) {
			logger.error("get class error! clazzName = "+clazz,e);
			return null;
		}
	}
	
}
