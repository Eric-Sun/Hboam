package com.hboam.am.core.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.core.LOGLevel;
/**
 * 控制JDBC访问的类，每次查询都会关闭链接，放入对应的链接池内
 * @author Eric
 *
 */
public class JDBCTemplate {
	
	Logger logger = LoggerFactory.getLogger(LOGLevel.CORE);
	
	Connection conn;
	
	public JDBCTemplate(Connection conn){
		this.conn = conn;
	}
	
	public List<Object[]> query(String sql, Object[] params,RowMapper map) throws SQLException{
		List<Object[]> resultList = new ArrayList<Object[]>();
	
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for( int i=1;i<=params.length;i++){
			pstmt.setObject(i, params[i-1]);
		}
		ResultSet rs = pstmt.executeQuery();
		while( rs.next() ){
			Object[] oArr = null;
			oArr = map.fetch(rs);
			resultList.add(oArr);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return resultList;
	}
	
	
	public void insert(String sql,Object[] params) throws SQLException{
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for( int i=1;i<=params.length;i++){
			pstmt.setObject(i, params[i-1]);
		}
		pstmt.execute();
		pstmt.close();
		conn.close();
	}

}
