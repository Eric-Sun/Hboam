package com.hboam.am.statistics.db.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.core.LOGLevel;
import com.hboam.am.core.db.JDBCTemplate;
import com.hboam.am.core.db.RowMapper;
import com.hboam.am.statistics.core.Dimension;
import com.hboam.am.statistics.util.ConcatUtil;
import com.hboam.am.statistics.util.DBOperationUtils;
import com.hboam.am.statistics.util.GameUtil;
import com.hboam.am.statistics.util.JDBCTemplateWrapper;

public class ConsumeDAOImpl implements ConsumeDAO {

	@Override
	public List<Object[]> getConsumeSumByCategory(Dimension d,
			String consumeCategoryPattern) {
		
		List<JDBCTemplate> jList = GameUtil.getDBTemplates(d.getGame(), d.getSubgame());
		List<Object[]> returnList = new ArrayList<Object[]>();
		for( JDBCTemplate j : jList ) {
			String sql = "select ifnull(sum(gold),0) from log_consume " +
					"where "+DBOperationUtils.getChan(new String[]{d.getChannel().getChannelId()},null)+
					"  and time between ? and ? and item like '%"+consumeCategoryPattern+"%'";
			
			try {
				List<Object[]> l1 = j.query(sql, new Object[]{d.getBeginDate(),d.getEndDate()}, new RowMapper() {
					
					@Override
					public Object[] fetch(ResultSet rs) throws SQLException {
						Object[] o = new Object[1];
						o[0] = rs.getString(1);
						return o;
					
					}
				});
				returnList = ConcatUtil.add(returnList, l1);
			} catch (SQLException e) {
				logger.error("consume role num error",e);
			}
		}
		return returnList;
	}


	@Override
	public List<Object[]> getConsumeSum(Dimension d) {
		
		List<JDBCTemplate> jList = GameUtil.getDBTemplates(d.getGame(), d.getSubgame());
		List<Object[]> returnList = new ArrayList<Object[]>();
		for( JDBCTemplate j : jList ) {
			String sql = "select ifnull(sum(gold),0) from log_consume " +
					"where "+DBOperationUtils.getChan(new String[]{d.getChannel().getChannelId()},null)+
					"  and time between ? and ?";
			
			try {
				List<Object[]> l1 = j.query(sql, new Object[]{d.getBeginDate(),d.getEndDate()}, new RowMapper() {
					
					@Override
					public Object[] fetch(ResultSet rs) throws SQLException {
						Object[] o = new Object[1];
						o[0] = rs.getString(1);
						return o;
					
					}
				});
				returnList = ConcatUtil.add(returnList, l1);
			} catch (SQLException e) {
				logger.error("consume role num error",e);
			}
		}
		return returnList;
		
	}

	
	@Override
	public List<Object[]> getConsumedRoleNum(Dimension d) {

		List<JDBCTemplate> jList = GameUtil.getDBTemplates(d.getGame(), d.getSubgame());
		List<Object[]> returnList = new ArrayList<Object[]>();
		for( JDBCTemplate j : jList ) {
			String sql = "select count(distinct roleid) from log_consume " +
					"where "+DBOperationUtils.getChan(new String[]{d.getChannel().getChannelId()},null)+
					"  and time between ? and ?";
			
			try {
				List<Object[]> l1 = j.query(sql, new Object[]{d.getBeginDate(),d.getEndDate()}, new RowMapper() {
					
					@Override
					public Object[] fetch(ResultSet rs) throws SQLException {
						Object[] o = new Object[1];
						o[0] = rs.getString(1);
						return o;
					
					}
				});
				returnList = ConcatUtil.add(returnList, l1);
			} catch (SQLException e) {
				logger.error("consume role num error",e);
			}
		}
		return returnList;
		
	}

	Logger logger = LoggerFactory.getLogger(LOGLevel.STATISTICS);
}
