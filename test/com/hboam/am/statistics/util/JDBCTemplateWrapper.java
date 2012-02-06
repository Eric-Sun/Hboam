package com.hboam.am.statistics.util;

import com.hboam.am.core.db.JDBCTemplate;
import com.hboam.am.util.ConnectionUtil;

public class JDBCTemplateWrapper {
	
	public static JDBCTemplate getJDBCTemplate(String dbResource){
		return new JDBCTemplate(ConnectionUtil.getConnection(dbResource));
	}

}
