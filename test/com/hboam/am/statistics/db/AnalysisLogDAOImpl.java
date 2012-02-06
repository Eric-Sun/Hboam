package com.hboam.am.statistics.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.core.LOGLevel;
import com.hboam.am.core.db.DBResource;
import com.hboam.am.core.db.JDBCTemplate;
import com.hboam.am.core.db.RowMapper;
import com.hboam.am.statistics.core.Channel;
import com.hboam.am.statistics.core.Dimension;
import com.hboam.am.statistics.core.Game;
import com.hboam.am.statistics.core.Subgame;
import com.hboam.am.statistics.util.JDBCTemplateWrapper;

public class AnalysisLogDAOImpl implements AnalysisLogDAO {

	@Override
	public boolean check(Dimension d,String selectorKey, String period) {
		
		JDBCTemplate jdbcTemplate = JDBCTemplateWrapper.getJDBCTemplate(DBResource.MID_STATISTICS);
		
		String sql = " select count(1) from analysis_log where  begin_date=?" +
				" and end_date = ? and `key` = ? and finished = 1 and period=? and game_id=? and subgame_id=? and channel_id=? ";
		List<Object[]> l1 = null;
		try {
			l1 = jdbcTemplate.query(sql, new Object[]{
					d.getBeginDate(),
					d.getEndDate(),
					selectorKey,
					period,
					d.getGame().getGameId(),
					d.getSubgame().getSubgameId(),
					d.getChannel().getChannelId()
			}, new RowMapper() {
				
				@Override
				public Object[] fetch(ResultSet rs) throws SQLException {
					Object[] oArr = new Object[1];
					oArr[0]=rs.getString(1);
					return oArr;
				}
			});
		} catch (SQLException e) {
			logger.error("insert selector error",e);
		}
		
		return l1.get(0)[0].toString().equals("0")?true:false;
		
	}

	@Override
	public void insert( Dimension d , String selectorKey, String period ) {
		
		JDBCTemplate jdbcTemplate = JDBCTemplateWrapper.getJDBCTemplate(DBResource.MID_STATISTICS);
		
		String sql = "insert into analysis_log " +
				"(begin_date,end_date,`key`,finished,period,game_id,subgame_id,channel_id) " +
				"values " +
				"(?,?,?,1,?,?,?,?)";
		
		try {
			jdbcTemplate.insert(sql, new Object[]{
					d.getBeginDate(),
					d.getEndDate(),
					selectorKey,
					period,
					d.getGame().getGameId(),
					d.getSubgame().getSubgameId(),
					d.getChannel().getChannelId()
			});
		} catch (SQLException e) {
			logger.error("insert selector error",e);
		}
	}

	Logger logger = LoggerFactory.getLogger(LOGLevel.STATISTICS);
}
