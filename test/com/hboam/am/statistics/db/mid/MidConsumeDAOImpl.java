package com.hboam.am.statistics.db.mid;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.core.LOGLevel;
import com.hboam.am.core.db.DBResource;
import com.hboam.am.statistics.core.Dimension;
import com.hboam.am.statistics.util.JDBCTemplateWrapper;

public class MidConsumeDAOImpl implements MidConsumeDAO {

	@Override
	public void insert(Dimension d, int consumeSum,
			int consumedRoleNum) {

		String sql = "insert into consume " +
				"(createtime,game_id,subgame_id,begin_date,end_date,period,channel_id," +
				"consume_sum,consumed_role_num) " +
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
								consumeSum,
								consumedRoleNum
								});
		} catch (SQLException e) {
			logger.error("statistics insert error",e);
		}
	}

	Logger logger = LoggerFactory.getLogger(LOGLevel.STATISTICS);

}
