package com.hboam.am.util;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.hboam.am.core.db.DBConnectionFactory;

public class ConnectionUtil {

	private static ThreadLocal<Map<String,Connection>> map = new ThreadLocal<Map<String,Connection>>();
	
	public static Connection getConnection(String dbResource){
		
		if (map.get()==null){
			Connection conn = DBConnectionFactory.getConnection(dbResource);
			Map<String, Connection> connMap = new HashMap<String, Connection>();
			connMap.put(dbResource, conn);
			map.set(connMap);
			return conn;
		}else{
			Map<String,Connection> connMap = map.get();
			if(connMap.containsKey(dbResource))
				return connMap.get(dbResource);
			else{
				Connection conn = DBConnectionFactory.getConnection(dbResource);
				connMap.put(dbResource, conn);
				return conn;
			}
		}
	}
	
	public static void destroyConnection(String dbResource){
		map.get().remove(dbResource);
	}
	
}
