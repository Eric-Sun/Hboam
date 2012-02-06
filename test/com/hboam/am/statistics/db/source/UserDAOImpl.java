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

public class UserDAOImpl implements UserDAO {

	@Override
	public List<Object[]> getRegisterUserNum(Dimension d) {

		JDBCTemplate t = JDBCTemplateWrapper.getJDBCTemplate(DBResource.PASSPORT);
		
		String sql = "SELECT COUNT(distinct p.passport) FROM passport p ,loginlog l" +
				" WHERE p.registerDate BETWEEN ? AND ? and l.passport=p.passport and "+
				DBOperationUtils.getGame(new String[]{d.getGame().getGameId()},"l")+" and "+
				DBOperationUtils.getSubgame(new String[]{d.getSubgame().getSubgameId()},"l")+" and "+
				DBOperationUtils.getChan(new String[]{d.getChannel().getChannelId()},"l");	
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
	public List<Object[]> getAllLoginedUserNum(Dimension d) {

		JDBCTemplate t = JDBCTemplateWrapper.getJDBCTemplate(DBResource.PASSPORT);
		
		String sql = "Select count(distinct l.passport) from loginlog l " +
			"where l.time between ? and ? and "+
				DBOperationUtils.getGame(new String[]{d.getGame().getGameId()},"l")+" and "+
				DBOperationUtils.getSubgame(new String[]{d.getSubgame().getSubgameId()},"l")+" and "+
				DBOperationUtils.getChan(new String[]{d.getChannel().getChannelId()},"l");	
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
	public List<Object[]> getOldLoginedUserNum(Dimension d) {

		JDBCTemplate t = JDBCTemplateWrapper.getJDBCTemplate(DBResource.PASSPORT);
		
		String sql = "select count(distinct l.passport) from loginlog l ,passport p where " +
				" p.passport = l.passport and p.registerDate < ? and l.time between ? and ? and " +
				DBOperationUtils.getGame(new String[]{d.getGame().getGameId()},"l")+" and "+
				DBOperationUtils.getSubgame(new String[]{d.getSubgame().getSubgameId()},"l")+" and "+
				DBOperationUtils.getChan(new String[]{d.getChannel().getChannelId()},"l");	
		List<Object[]> returnList = new ArrayList<Object[]>();
		
		try {
			returnList = t.query(sql, new Object[]{d.getBeginDate(),d.getBeginDate(),d.getEndDate()},new RowMapper() {
				
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
	public List<Object[]> getNewIntoGameUserNum(Dimension d) {

		JDBCTemplate t = JDBCTemplateWrapper.getJDBCTemplate(DBResource.PASSPORT);
		
		String sql = "select count(distinct r.passport) from role r ,passport p where " +
				" p.passport = r.passport and p.registerDate between  ? and ? and  " +
				DBOperationUtils.getGame(new String[]{d.getGame().getGameId()},"r")+" and "+
				DBOperationUtils.getSubgame(new String[]{d.getSubgame().getSubgameId()},"r")+" and "+
				DBOperationUtils.getChan(new String[]{d.getChannel().getChannelId()},"p");	
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
	public List<Object[]> getNewLevel5UserNum(Dimension d) {

		JDBCTemplate t = JDBCTemplateWrapper.getJDBCTemplate(DBResource.PASSPORT);
		
		String sql = "select count(distinct r.passport) from role r ,passport p where " +
				" p.passport = r.passport and p.registerDate between  ? and ? and r.activetime <>'0000-00-00 00:00:00' and " +
				DBOperationUtils.getGame(new String[]{d.getGame().getGameId()},"r")+" and "+
				DBOperationUtils.getSubgame(new String[]{d.getSubgame().getSubgameId()},"r")+" and "+
				DBOperationUtils.getChan(new String[]{d.getChannel().getChannelId()},"p");	
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
