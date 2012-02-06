package com.hboam.am.statistics.db.mid;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.core.LOGLevel;
import com.hboam.am.core.db.DBResource;
import com.hboam.am.statistics.core.Dimension;
import com.hboam.am.statistics.util.JDBCTemplateWrapper;

public class MidConsumeCategoryDAOImpl implements MidConsumeCategoryDAO {

	@Override
	public void insert(Dimension d, String category, int consumeSum) {
		String sql = "insert into consume_category " +
				"(createtime,game_id,subgame_id,begin_date,end_date,period,channel_id," +
				"category,consume_sum) " +
				"values " +
				"(now(),?,?,?,?,?,?,?,?)";

		try {
			JDBCTemplateWrapper.getJDBCTemplate(DBResource.MID_STATISTICS).insert(sql, 
					new Object[]{d.getGame().getGameId(),
								d.getSubgame().getSubgameId(),
								d.getBeginDate(),
								d.getEndDate(),
								d.getPeriod(),
								d.getChannel().getChannelId(),
								category,
								consumeSum
								});
		} catch (SQLException e) {
			logger.error("statistics insert error",e);
		}
	}

	Logger logger = LoggerFactory.getLogger(LOGLevel.STATISTICS);

}
