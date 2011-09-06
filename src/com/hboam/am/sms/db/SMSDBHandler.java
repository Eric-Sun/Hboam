package com.hboam.am.sms.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.core.db.DBConnectionFactory;
import com.hboam.am.core.db.DBResource;
import com.hboam.am.sms.util.DateUtil;
import com.hboam.am.util.ConnectionUtil;

public class SMSDBHandler {
	
	private Logger logger = LoggerFactory.getLogger("sms");
	public String getAvgOnlineNum(String dbResource) {
		
		try {
			String sql = "SELECT ROUND(AVG(users)) FROM onlineusers " +
					"WHERE o_date BETWEEN ? AND ?";
			PreparedStatement pstmt = ConnectionUtil.getConnection(dbResource).prepareStatement(sql);
			pstmt.setString(1, DateUtil.getBeginDate());
			pstmt.setString(2,DateUtil.getEndDate());
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String sum = rs.getString(1);
			pstmt.close();
			rs.close();
			return sum;
		} catch (SQLException e) {
			logger.error("error",e);
			return null;
		}
	}

	public String getNewPassportNum() {
		
		try {
			Connection conn = ConnectionUtil.getConnection(DBResource.PASSPORT);
			String sql = "select count(1) from passport where registerDate between ? and ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, DateUtil.getBeginDate());
			pstmt.setString(2,DateUtil.getEndDate());
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String sum = rs.getString(1);
			pstmt.close();
			rs.close();
			return sum;
		} catch (SQLException e) {
			logger.error("error",e);
			return null;
		}
	}

	public String getChargedPassportNum() {
		
		try {
			Connection conn = ConnectionUtil.getConnection(DBResource.PASSPORT);
			String sql = "select count(distinct passport) from consume_charge_order where cp_callback =1 " +
					"and time between ? and ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, DateUtil.getBeginDate());
			pstmt.setString(2,DateUtil.getEndDate());
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String sum = rs.getString(1);
			pstmt.close();
			rs.close();
			return sum;
		} catch (SQLException e) {
			logger.error("error",e);
			return null;
		}
	}
	public String getChargeSum(){
		try {
			Connection conn = ConnectionUtil.getConnection(DBResource.PASSPORT);;
			String sql = "select ifnull(sum(gold)/100,0) from consume_charge_order where cp_callback =1 " +
					"and time between ? and ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, DateUtil.getBeginDate());
			pstmt.setString(2,DateUtil.getEndDate());
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String sum = rs.getString(1);
			pstmt.close();
			rs.close();
			return sum;
		} catch (SQLException e) {
			logger.error("error",e);
		}
		return null;
	}
}
