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
import com.hboam.am.statistics.util.ConcatUtil;
import com.hboam.am.statistics.util.DBOperationUtils;
import com.hboam.am.statistics.util.GameUtil;
import com.hboam.am.statistics.util.JDBCTemplateWrapper;

public class RoleDAOImpl implements RoleDAO {

	@Override
	public List<Object[]> getLoginedRoleNum(Dimension d) {

		List<JDBCTemplate> jList = GameUtil.getDBTemplates(d.getGame(), d.getSubgame());
		List<Object[]> returnList = new ArrayList<Object[]>();
		for( JDBCTemplate j : jList ) {
		String sql = "select count(distinct roleid) from log_login where o_date between ? and ?" +
				"and " +
				DBOperationUtils.getChannel(new String[]{d.getChannel().getChannelId()}, null) ;
				
				
			try {
				List<Object[]> l1  = j.query(sql, new Object[]{d.getBeginDate(),d.getEndDate()},new RowMapper() {
					
					@Override
					public Object[] fetch(ResultSet rs) throws SQLException {
						Object[] o = new Object[1];
						o[0] = rs.getString(1);
						return o;
					}
				});
				returnList = ConcatUtil.add(returnList, l1);
			} catch (SQLException e) {
				logger.error("statistics db error",e);
			}
		}
		return returnList;
	}
	Logger logger = LoggerFactory.getLogger(LOGLevel.STATISTICS);

}
