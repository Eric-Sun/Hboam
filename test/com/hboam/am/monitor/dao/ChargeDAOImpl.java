package com.hboam.am.monitor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.hboam.am.core.db.DBResource;
import com.hboam.am.core.db.JDBCTemplate;
import com.hboam.am.core.db.RowMapper;
import com.hboam.am.statistics.util.JDBCTemplateWrapper;

public class ChargeDAOImpl implements ChargeDAO {

	@Override
	public List<Object[]> getChargedPassportCount(Date beginDate, Date endDate) throws SQLException {
		
		JDBCTemplate jTemplate = JDBCTemplateWrapper.getJDBCTemplate(DBResource.PASSPORT);
		
		String sql = "select count(1) from consume_charge_order where cp_callback=1 and time between ? and ?";
		
		List<Object[]> list = jTemplate.query(sql, new Object[]{beginDate,endDate},
				new RowMapper() {
					
					@Override
					public Object[] fetch(ResultSet arg0) throws SQLException {
						
						Object[] o = new Object[1];
						o[0] = arg0.getString(1);
						return o;
					}
				});
		
		
		return list;
	}

}
