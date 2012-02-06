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

public class OrderDAOImpl implements OrderDAO {

	@Override
	public List<Object[]> getNewChargedUserNum(Dimension d) {
		
		JDBCTemplate t = JDBCTemplateWrapper.getJDBCTemplate(DBResource.PASSPORT);
		
		String sql = "select count(distinct o1.passport) from consume_charge_order o1 " +
				" where o1.time between ? and ? and o1.cp_callback=1" +
				" and o1.passport not in((select distinct passport from consume_charge_order where cp_callback=1 and time <? ))  and " +
				DBOperationUtils.getGame(new String[]{d.getGame().getGameId()},"o1")+
				"and " +
				DBOperationUtils.getSubgame(new String[]{d.getSubgame().getSubgameId()},"o1")+
				"and " +
				DBOperationUtils.getChan(new String[]{d.getChannel().getChannelId()}, "o1")+
				"" ;	
		List<Object[]> returnList = new ArrayList<Object[]>();
		
		try {
			returnList = t.query(sql, new Object[]{d.getBeginDate(),d.getEndDate(),d.getBeginDate()},new RowMapper() {
				
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
	public List<Object[]> getLossUserNum(Dimension d) {

		JDBCTemplate t = JDBCTemplateWrapper.getJDBCTemplate(DBResource.PASSPORT);
		String sql = "select ifnull(count(distinct p.passport),0) from passport p,role r where p.lastlogin <=date_sub(?,interval 15 day) and  " +
				DBOperationUtils.getGame(new String[]{d.getGame().getGameId()},"p")+
				"and " +
				DBOperationUtils.getSubgame(new String[]{d.getSubgame().getSubgameId()},"r")+
				"and " +
				DBOperationUtils.getChan(new String[]{d.getChannel().getChannelId()}, "p")+
				"" ;
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
	
	@Override
	public List<Object[]> getChargedUserNum(Dimension d) {

		JDBCTemplate t = JDBCTemplateWrapper.getJDBCTemplate(DBResource.PASSPORT);
		String sql = "select count(distinct passport) from consume_charge_order where " +
				" cp_callback=1 and "+ DBOperationUtils.getChan(new String[]{d.getChannel().getChannelId()},null)+
				" and  time between ? and ? and "+
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
	
	Logger logger = LoggerFactory.getLogger(LOGLevel.STATISTICS);

}
