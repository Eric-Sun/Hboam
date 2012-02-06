package com.hboam.am.statistics.db.mid;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.core.LOGLevel;
import com.hboam.am.core.db.DBResource;
import com.hboam.am.core.db.RowMapper;
import com.hboam.am.statistics.core.Channel;
import com.hboam.am.statistics.core.Dimension;
import com.hboam.am.statistics.core.Game;
import com.hboam.am.statistics.core.Subgame;
import com.hboam.am.statistics.util.JDBCTemplateWrapper;
import com.hboam.am.util.ConnectionUtil;

public class MidOnlineDAOImpl implements MidOnlineDAO {

	@Override
	public void insertIntoMidOnline(Dimension d,
			int maxOnlineNum, int avgOnlineNum, float newAvgOnlineTime, float oldAvgOnlineTime ,
			float allAvgOnlineTime, String period,int allOnlineMinutes, int allOnlineUserNum ) {

		String sql = "insert into online " +
				"(createtime,game_id,subgame_id,max_online_num,avg_online_num,new_avg_online_time," +
				"all_avg_online_time,begin_date,end_date,period,old_avg_online_time,channel_id," +
				"all_online_minutes,all_online_user_num) " +
				"values " +
				"(now(),?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			JDBCTemplateWrapper.getJDBCTemplate(DBResource.MID_STATISTICS).insert(sql, 
					new Object[]{d.getGame().getGameId(),
								d.getSubgame().getSubgameId(),
								maxOnlineNum,
								avgOnlineNum,
								newAvgOnlineTime,
								allAvgOnlineTime,
								d.getBeginDate(),
								d.getEndDate(),
								period,
								allAvgOnlineTime,
								d.getChannel().getChannelId(),
								allOnlineMinutes, 
								allOnlineUserNum 
								});
		} catch (SQLException e) {
			logger.error("statistics insert error",e);
		}
	}

	Logger logger = LoggerFactory.getLogger(LOGLevel.STATISTICS);
}
