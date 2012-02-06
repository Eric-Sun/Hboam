package com.hboam.am.monitor.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ChargeDAO {
	
	/**
	 * ���һ��ʱ���ڵĳ�ֵ����
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<Object[]> getChargedPassportCount(Date beginDate, Date endDate) throws SQLException;

}
