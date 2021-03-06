package com.hboam.am.core.db;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.core.LOGLevel;
import com.hboam.am.core.Lifecycle;
import com.hboam.am.util.XmlUtil;

/**
 * 数据库链接的工厂类，用来获得对应的数据库链接
 * <p> 默认使用proxool连接池
 * @author Eric
 *
 */
public class DBConnectionFactory implements Lifecycle{
	
	private String HBOAM_RESOUCE_PATH="conf/hboam.xml";
	private static String PROXOOL_RESOUCE_PATH="conf/proxool.xml";
	private static  Logger logger = LoggerFactory.getLogger(LOGLevel.CORE);
	private boolean inited = false;
	
	public void init() {
		// TODO Auto-generated method stub
		// load all db resource 
		if( ! inited )
			loadProxool();
	}
	/**
	 * 导入配置参数
	 */
	private void loadProxool(){
		if ( ! inited ){
			try {
				JAXPConfigurator.configure(PROXOOL_RESOUCE_PATH, false);
				inited = true;
			} catch (ProxoolException e) {
				logger.error(" load proxool error ",e);
			}
		}
	}
	
	@Override
	public void destroy() {
		
	}
	/**
	 * return An connection specified the dbResource ( log )
	 * if any.
	 * <p> If exception threw, return null
	 * @param dbResource
	 * @return
	 */
	public static Connection getConnection(String dbResource){
		
		try {
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
			
			Connection conn = DriverManager.getConnection("proxool."+dbResource); 
			
			return conn;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("db error!",e);
			return null;
		}
		
	}
	
}
