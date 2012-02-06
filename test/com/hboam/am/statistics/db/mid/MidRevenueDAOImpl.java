package com.hboam.am.statistics.db.mid;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.core.LOGLevel;
import com.hboam.am.core.db.DBResource;
import com.hboam.am.statistics.core.Dimension;
import com.hboam.am.statistics.util.JDBCTemplateWrapper;

public class MidRevenueDAOImpl implements MidRevenueDAO {

	@Override
	public void insert(Dimension d, float revenue, float balance,
			float lossUserRevenue, int chargedUserNum,float szxRevenue, float xiaoeRevenue, int newChargedUserNum) {
		
		String sql = "insert into revenue " +
				"(createtime,game_id,subgame_id,channel_id,begin_date,end_date,revenue," +
				"balance,charged_user_num,loss_user_revenue," +
				"new_charged_user_num,szx_revenue,cellphone_revenue) " +
				"values " +
				"(now(),?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			JDBCTemplateWrapper.getJDBCTemplate(DBResource.MID_STATISTICS).insert(sql, 
					new Object[]{d.getGame().getGameId(),
								d.getSubgame().getSubgameId(),
								d.getChannel().getChannelId(),
								d.getBeginDate(),
								d.getEndDate(),
								revenue,
								balance,
								chargedUserNum,
								lossUserRevenue,
								szxRevenue,
								xiaoeRevenue,
								newChargedUserNum
								});
		} catch (SQLException e) {
			logger.error("statistics insert error",e);
		}
	}

	Logger logger = LoggerFactory.getLogger(LOGLevel.STATISTICS);

}
