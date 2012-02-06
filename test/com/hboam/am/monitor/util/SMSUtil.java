package com.hboam.am.monitor.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SMSUtil {
	static Log logger = LogFactory.getLog("monitor");
	    public static boolean send(String tel,String message){
	                StringBuffer url = new StringBuffer("http://sdkhttp.eucp.b2m.cn/sdkproxy/sendsms.action?cdkey=3SDK-VXL-0130-LJVNR&password=693781");
	                url.append("&phone=").append(tel);
                    url.append("&message=").append(message);
	                url.append("&addserial=");
	               String info =null;
	            try {
	            	info= new HttpUtil().requestHttpContent(url.toString());
	               
	            } catch (IOException ex) {
	            	logger.error("error",ex);
	            }
	                return isSend(info);
	    }
	    private static boolean isSend(String text){
	        if(text == null||text.indexOf("<error>")<1){
	            return false;
	        }
	        String tem = text.toUpperCase();
	        int code = Integer.parseInt(text.substring(tem.indexOf("<ERROR>")+7,tem.indexOf("</ERROR>")).trim());
	        return code==0?true:false;
	    }

}
