package com.hboam.am.statistics.db.source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.lf5.util.ResourceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.core.db.JDBCTemplate;
import com.hboam.am.core.db.RowMapper;
import com.hboam.am.statistics.core.Channel;
import com.hboam.am.statistics.core.Dimension;
import com.hboam.am.statistics.core.Game;
import com.hboam.am.statistics.core.Subgame;
import com.hboam.am.statistics.util.ConcatUtil;
import com.hboam.am.statistics.util.DBOperationUtils;
import com.hboam.am.statistics.util.GameUtil;

public class OnlineDAOImpl implements OnlineDAO {

	@Override
	public List<Object[]> getAllOnlineMinutes(Dimension d) {
		
		List<Object[]> returnList = new ArrayList<Object[]>();

		List<JDBCTemplate> tList = GameUtil.getDBTemplates(d.getGame(), d.getSubgame());
		for ( JDBCTemplate t : tList ){
			try {
				String sql="SELECT ifnull(SUM(s.onlineminutes),0) "+
						"FROM mmorpg.stat_online s INNER JOIN mmorpg.role r  "+
						"ON s.passport = r.passport WHERE "+
						" s.time between ? and ? and s.onlineminutes<10000 and "+DBOperationUtils.getChannel(new String[]{d.getChannel().getChannelId()},"r");
					List<Object[]> l1 = t.query(sql, new Object[]{ d.getBeginDate() , d.getEndDate() }, new RowMapper() {
					@Override
					public Object[] fetch(ResultSet rs) throws SQLException {
						Object[] oArr = new Object[1];
						oArr[0] = rs.getString(1);
						return oArr;
					}
				});
				
				returnList = ConcatUtil.add(returnList,l1 );
			} catch (SQLException e) {
				logger.error("statstics db error",e);
			} finally{
			}
		}
		return returnList;
	}
	@Override
	public List<Object[]> getAllOnlineUserNum(Dimension d) {
		
		List<Object[]> returnList = new ArrayList<Object[]>();

		List<JDBCTemplate> tList = GameUtil.getDBTemplates(d.getGame(), d.getSubgame());
		for ( JDBCTemplate t : tList ){
			try {
				String sql="SELECT ifnull(COUNT(DISTINCT s.passport),0) "+
						"FROM mmorpg.stat_online s INNER JOIN mmorpg.role r  "+
						"ON s.passport = r.passport WHERE "+
						" s.time between ? and ? and s.onlineminutes<10000 and "+DBOperationUtils.getChannel(new String[]{d.getChannel().getChannelId()},"r");
					List<Object[]> l1 = t.query(sql, new Object[]{ d.getBeginDate() , d.getEndDate() }, new RowMapper() {
					@Override
					public Object[] fetch(ResultSet rs) throws SQLException {
						Object[] oArr = new Object[1];
						oArr[0] = rs.getString(1);
						return oArr;
					}
				});
				
				returnList = ConcatUtil.add(returnList,l1 );
			} catch (SQLException e) {
				logger.error("statstics db error",e);
			} finally{
			}
		}
		return returnList;
	}
	@Override
	public List<Object[]> getOldAvgOnlineTime(Dimension d) {
		
		List<Object[]> returnList = new ArrayList<Object[]>();

		List<JDBCTemplate> tList = GameUtil.getDBTemplates(d.getGame(), d.getSubgame());
		for ( JDBCTemplate t : tList ){
			try {
				String sql="SELECT ifnull(SUM(s.onlineminutes)/COUNT(DISTINCT s.passport),0)AS AVG "+
						"FROM mmorpg.stat_online s INNER JOIN mmorpg.role r  "+
						"ON s.passport = r.passport WHERE "+
						" r.registertime < ? and s.time between ? and ? and s.onlineminutes<10000  and "+DBOperationUtils.getChannel(new String[]{d.getChannel().getChannelId()},"r");
					List<Object[]> l1 = t.query(sql, new Object[]{ d.getBeginDate() , d.getBeginDate() , d.getEndDate() }, new RowMapper() {
					@Override
					public Object[] fetch(ResultSet rs) throws SQLException {
						Object[] oArr = new Object[1];
						oArr[0] = rs.getString(1);
						return oArr;
					}
				});
				
				returnList = ConcatUtil.add(returnList,l1 );
			} catch (SQLException e) {
				logger.error("statstics db error",e);
			} finally{
			}
		}
		return returnList;
	}
	@Override
	public List<Object[]> getAllAvgOnlineTime(Dimension d) {
		
		List<Object[]> returnList = new ArrayList<Object[]>();

		List<JDBCTemplate> tList = GameUtil.getDBTemplates(d.getGame(), d.getSubgame());
		for ( JDBCTemplate t : tList ){
			try {
				
				String sql="SELECT ifnull(SUM(s.onlineminutes)/COUNT(DISTINCT s.passport),0) AS AVG "+
							"FROM mmorpg.stat_online s   "+
							" WHERE s.time between ? and ? and s.onlineminutes<10000  and  "+DBOperationUtils.getChan(new String[]{d.getChannel().getChannelId()},"s");
				List<Object[]> l1 = t.query(sql, new Object[]{  d.getBeginDate() , d.getEndDate() }, new RowMapper() {
					@Override
					public Object[] fetch(ResultSet rs) throws SQLException {
						Object[] oArr = new Object[1];
						oArr[0] = rs.getString(1);
						return oArr;
					}
				});
				
				returnList = ConcatUtil.add(returnList,l1 );
			} catch (SQLException e) {
				logger.error("statstics db error",e);
			} finally{
			}
		}
		return returnList;
	}
	@Override
	public List<Object[]> getNewAvgOnlineTime(Dimension d) {
		
		List<Object[]> returnList = new ArrayList<Object[]>();

		List<JDBCTemplate> tList = GameUtil.getDBTemplates(d.getGame(), d.getSubgame());
		for ( JDBCTemplate t : tList ){
			try {
				String sql="SELECT ifnull(SUM(s.onlineminutes)/COUNT(DISTINCT s.passport),0) AS AVG "+
						"FROM mmorpg.stat_online s INNER JOIN mmorpg.role r  "+
						"ON s.passport = r.passport WHERE" +
						" r.registertime between ? and ? and s.onlineminutes<10000  and  s.time between ? and ? and "+DBOperationUtils.getChannel(new String[]{d.getChannel().getChannelId()},"r");
			List<Object[]> l1 = t.query(sql, new Object[]{ d.getBeginDate() , d.getEndDate() , d.getBeginDate() , d.getEndDate() }, new RowMapper() {	
					@Override
					public Object[] fetch(ResultSet rs) throws SQLException {
						Object[] oArr = new Object[1];
						oArr[0] = rs.getString(1);
						return oArr;
					}
				});
				
				returnList = ConcatUtil.add(returnList,l1 );
			} catch (SQLException e) {
				logger.error("statstics db error",e);
			} finally{
			}
		}
		return returnList;
	}
	@Override
	public List<Object[]> getMaxAndAvgOnline(Dimension d) {
		List<Object[]> returnList = new ArrayList<Object[]>();

		List<JDBCTemplate> tList = GameUtil.getDBTemplates(d.getGame(), d.getSubgame());
		for ( JDBCTemplate t : tList ){
			try {
				String sql = "SELECT MAX(maxusers),ROUND(AVG(users)) " +
						"FROM onlineusers where o_date between ? and ? GROUP BY DATE(o_date)";
				List<Object[]> l1 = t.query(sql, new Object[]{ d.getBeginDate() , d.getEndDate() }, new RowMapper() {
					@Override
					public Object[] fetch(ResultSet rs) throws SQLException {
						Object[] oArr = new Object[2];
						oArr[0] = rs.getString(1);
						oArr[1] = rs.getString(2);
						return oArr;
					}
				});
				
				returnList = ConcatUtil.add(returnList, l1);
			} catch (SQLException e) {
				logger.error("statstics db error",e);
			} finally{
			}
		}
		return returnList;
	}


	Logger logger = LoggerFactory.getLogger("statistics");
}
