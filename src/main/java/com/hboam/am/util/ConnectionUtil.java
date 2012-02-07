package com.hboam.am.util;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.hboam.am.core.db.DBConnectionFactory;

public class ConnectionUtil {

	public static Connection getConnection(String dbResource){
		
			Connection conn = DBConnectionFactory.getConnection(dbResource);
			return conn;
	}
	
}
