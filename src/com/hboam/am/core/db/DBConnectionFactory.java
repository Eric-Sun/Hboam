package com.hboam.am.core.db;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.core.Lifecycle;
import com.hboam.am.util.XmlUtil;

public class DBConnectionFactory implements Lifecycle{
	
	private String HBOAM_RESOUCE_PATH="conf/hboam.xml";
	private static  Logger logger = LoggerFactory.getLogger("core");
	private static Map<String, DBResourceKey> dbMap = null;
	
	public void init() {
		// TODO Auto-generated method stub
		// load all db resource 
		dbMap = new HashMap<String, DBResourceKey>();
		
		loadBHoam();
	}

	private void loadBHoam(){
		try {
			XmlUtil xml = new XmlUtil(HBOAM_RESOUCE_PATH);
			List<Element> dbList = xml.getElements("/hboam/db");
			for( Element e : dbList ){
				String dbName = e.attributeValue("name");
				String url = e.elementText("url");
				String user = e.elementText("user");
				String pwd = e.elementText("pwd");
				DBResourceKey dbKey = new DBResourceKey(dbName, url, pwd, user);
				dbMap.put(dbName, dbKey);
			}
		} catch (Exception e) {
			logger.error("error",e);
		}
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * return An connection specified the dbResource ( log )
	 * if any.
	 * <p> If exception happened, return null
	 * @param dbResource
	 * @return
	 */
	public static Connection getConnection(String dbResource){
		DBResourceKey key = dbMap.get(dbResource);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			return DriverManager.getConnection(key.getUrl(),key.getUser(),key.getPwd());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("db error!",e);
			return null;
		}
		
		
		
	}
	
	
	
}
