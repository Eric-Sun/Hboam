package com.hboam.am.statistics.db.mid;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.core.LOGLevel;
import com.hboam.am.core.db.DBResource;
import com.hboam.am.statistics.core.Dimension;
import com.hboam.am.statistics.util.JDBCTemplateWrapper;

public class MidUserDAOImpl implements MidUserDAO {

	@Override
	public void insert(Dimension d, int registerUserNum, int loginedUserNum,
			int oldLoginedUserNum, int newIntoGameUserNum, int newLevel5UserNum) {
		
		String sql = "insert into user " +
				"(createtime,game_id,subgame_id,channel_id,begin_date,end_date,period," +
				"register_user_num,logined_user_num,old_logined_user_num," +
				"new_into_game_user_num,new_level5_user_num) " +
				"values " +
				"(now(),?,?,?,?,?,?,?,?,?,?,?)";

		try {
			JDBCTemplateWrapper.getJDBCTemplate(DBResource.MID_STATISTICS).insert(sql, 
					new Object[]{d.getGame().getGameId(),
								d.getSubgame().getSubgameId(),
								d.getChannel().getChannelId(),
								d.getBeginDate(),
								d.getEndDate(),
								d.getPeriod(),
								registerUserNum,
								loginedUserNum,
								oldLoginedUserNum,
								newIntoGameUserNum,
								newLevel5UserNum,
								});
		} catch (SQLException e) {
			logger.error("statistics insert error",e);
		}
		
	}
	Logger logger = LoggerFactory.getLogger(LOGLevel.STATISTICS);
}
