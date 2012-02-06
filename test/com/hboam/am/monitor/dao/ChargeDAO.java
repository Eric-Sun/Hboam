package com.hboam.am.monitor.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ChargeDAO {
	
	/**
	 * 获得一段时间内的充值数量
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<Object[]> getChargedPassportCount(Date beginDate, Date endDate) throws SQLException;

}
