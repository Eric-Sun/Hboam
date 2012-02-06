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

public class DBConnectionFactory implements Lifecycle{
	
	private String HBOAM_RESOUCE_PATH="conf/hboam.xml";
	private static String PROXOOL_RESOUCE_PATH="conf/proxool.xml";
	private static  Logger logger = LoggerFactory.getLogger(LOGLevel.CORE);
<<<<<<< HEAD
	private boolean inited = false;
=======
>>>>>>> 16c35aa679e23ae7f304929c34fbc588f2b4566d
	
	public void init() {
		// TODO Auto-generated method stub
		// load all db resource 
<<<<<<< HEAD
		if( ! inited )
			loadProxool();
	}

	private void loadProxool(){
		if ( ! inited ){
			try {
				JAXPConfigurator.configure(PROXOOL_RESOUCE_PATH, false);
				inited = true;
			} catch (ProxoolException e) {
				logger.error(" load proxool error ",e);
			}
=======
		loadProxool();
	}

	private void loadProxool(){
		try {
			JAXPConfigurator.configure(PROXOOL_RESOUCE_PATH, false);
		} catch (ProxoolException e) {
			logger.error(" load proxool error ",e);
>>>>>>> 16c35aa679e23ae7f304929c34fbc588f2b4566d
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
