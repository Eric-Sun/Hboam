package com.hboam.am.statistics.db.mid;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.core.LOGLevel;
import com.hboam.am.core.db.DBResource;
import com.hboam.am.statistics.core.Dimension;
import com.hboam.am.statistics.util.JDBCTemplateWrapper;

public class MidRoleDAOImpl implements MidRoleDAO {

	@Override
	public void insert(Dimension d, int loginedRoleNum) {
		String sql = "insert into role " +
				"(createtime,game_id,subgame_id,channel_id,begin_date,end_date,period," +
				"logined_role_num) " +
				"values " +
				"(now(),?,?,?,?,?,?,?)";

		try {
			JDBCTemplateWrapper.getJDBCTemplate(DBResource.MID_STATISTICS).insert(sql, 
					new Object[]{d.getGame().getGameId(),
								d.getSubgame().getSubgameId(),
								d.getChannel().getChannelId(),
								d.getBeginDate(),
								d.getEndDate(),
								d.getPeriod(),
								loginedRoleNum,
								});
		} catch (SQLException e) {
			logger.error("statistics insert error",e);
		}
		
	}
	Logger logger = LoggerFactory.getLogger(LOGLevel.STATISTICS);
}
