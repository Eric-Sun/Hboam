package com.hboam.am.statistics.db.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.core.LOGLevel;
import com.hboam.am.core.db.DBResource;
import com.hboam.am.core.db.JDBCTemplate;
import com.hboam.am.core.db.RowMapper;
import com.hboam.am.statistics.core.Dimension;
import com.hboam.am.statistics.util.DBOperationUtils;
import com.hboam.am.statistics.util.JDBCTemplateWrapper;

public class RevenueDAOImpl implements RevenueDAO {

	@Override
	public List<Object[]> getUserRevenueByChargeType(Dimension d,
			String chargeType) {
		JDBCTemplate t = JDBCTemplateWrapper.getJDBCTemplate(DBResource.PASSPORT);
		String sql = "select ifnull(sum(gold)/100,0) from consume_charge_order where " +
				" cp_callback=1 and "+ DBOperationUtils.getChan(new String[]{d.getChannel().getChannelId()},null)+
				" and  `time` between ? and ? and "+
				DBOperationUtils.getGame(new String[]{d.getGame().getGameId()})+
				"and " +
				DBOperationUtils.getSubgame(new String[]{d.getSubgame().getSubgameId()},null)+
				" and chargeType = ?";
		List<Object[]> returnList = new ArrayList<Object[]>();
		
		try {
			returnList = t.query(sql, new Object[]{d.getBeginDate(),d.getEndDate(),chargeType},new RowMapper() {
				
				@Override
				public Object[] fetch(ResultSet rs) throws SQLException {
					Object[] o = new Object[1];
					o[0] = rs.getString(1);
					return o;
				}
			});
		} catch (SQLException e) {
			logger.error("statistics db error",e);
		}
		return returnList;
	}

	@Override
	public List<Object[]> getRevenue(Dimension d) {
		JDBCTemplate t = JDBCTemplateWrapper.getJDBCTemplate(DBResource.PASSPORT);
		String sql = "select ifnull(sum(gold)/100,0) from consume_charge_order where " +
				" cp_callback=1 and "+ DBOperationUtils.getChan(new String[]{d.getChannel().getChannelId()},null)+
				" and  `time` between ? and ? and "+
				DBOperationUtils.getGame(new String[]{d.getGame().getGameId()})+
				"and " +
				DBOperationUtils.getSubgame(new String[]{d.getSubgame().getSubgameId()},null);
		List<Object[]> returnList = new ArrayList<Object[]>();
		
		try {
			returnList = t.query(sql, new Object[]{d.getBeginDate(),d.getEndDate()},new RowMapper() {
				
				@Override
				public Object[] fetch(ResultSet rs) throws SQLException {
					Object[] o = new Object[1];
					o[0] = rs.getString(1);
					return o;
				}
			});
		} catch (SQLException e) {
			logger.error("statistics db error",e);
		}
		return returnList;
	}

	@Override
	public List<Object[]> getBalance(Dimension d) {

		JDBCTemplate t = JDBCTemplateWrapper.getJDBCTemplate(DBResource.PASSPORT);
		String sql = "select ifnull(sum(balance),0) from consume_charge_order where " +
				" cp_callback=1 and "+ DBOperationUtils.getChan(new String[]{d.getChannel().getChannelId()},null)+
				" and `time` between ? and ? and "+
				DBOperationUtils.getGame(new String[]{d.getGame().getGameId()})+
				"and " +
				DBOperationUtils.getSubgame(new String[]{d.getSubgame().getSubgameId()},null);
		List<Object[]> returnList = new ArrayList<Object[]>();
		
		try {
			returnList = t.query(sql, new Object[]{d.getBeginDate(),d.getEndDate()},new RowMapper() {
				
				@Override
				public Object[] fetch(ResultSet rs) throws SQLException {
					Object[] o = new Object[1];
					o[0] = rs.getString(1);
					return o;
				}
			});
		} catch (SQLException e) {
			logger.error("statistics db error",e);
		}
		return returnList;
	}

	
	@Override
	public List<Object[]> getLossUserRevenue(Dimension d) {

		JDBCTemplate t = JDBCTemplateWrapper.getJDBCTemplate(DBResource.PASSPORT);
		String sql = "select ifnull(sum(o.gold)/100,0) from consume_charge_order o " +
				" where "+
				DBOperationUtils.getGame(new String[]{d.getGame().getGameId()},"o")+
				"and " +
				DBOperationUtils.getSubgame(new String[]{d.getSubgame().getSubgameId()},"o")+
				"and " +
				DBOperationUtils.getChan(new String[]{d.getChannel().getChannelId()}, "o")+
				"and o.cp_callback=1 and o.passport in(select passport from passport where lastlogin <=date_sub(?,interval 15 day)) " ;
		List<Object[]> returnList = new ArrayList<Object[]>();
		
		try {
			returnList = t.query(sql, new Object[]{d.getBeginDate()},new RowMapper() {
				
				@Override
				public Object[] fetch(ResultSet rs) throws SQLException {
					Object[] o = new Object[1];
					o[0] = rs.getString(1);
					return o;
				}
			});
		} catch (SQLException e) {
			logger.error("statistics db error",e);
		}
		return returnList;
	}
	
	Logger logger = LoggerFactory.getLogger(LOGLevel.STATISTICS);

}
