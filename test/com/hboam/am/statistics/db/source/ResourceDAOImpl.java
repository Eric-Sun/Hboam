package com.hboam.am.statistics.db.source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.core.db.DBResource;
import com.hboam.am.core.db.JDBCTemplate;
import com.hboam.am.core.db.RowMapper;
import com.hboam.am.statistics.core.Channel;
import com.hboam.am.statistics.core.Game;
import com.hboam.am.statistics.core.Subgame;
import com.hboam.am.statistics.util.JDBCTemplateWrapper;
import com.hboam.am.util.ConnectionUtil;

public class ResourceDAOImpl implements ResourceDAO {
	@Override
	public List<Object[]> getAllChannelsHaveRegister(Date beginDate,Date endDate) {
		
		JDBCTemplate t = JDBCTemplateWrapper.getJDBCTemplate(DBResource.PASSPORT);
		List<Object[]> returnlist = null;
		String sql = "select distinct p.chanId , c.channels_name from passport p left outer join channels_mapping c " +
				"on p.chanId = c.channels_id where" +
				" registerDate between ? and ? and p.chanId is not null and c.channels_name is not null";
		try {
			returnlist = t.query(sql,new Object[]{beginDate,endDate},new RowMapper() {
				@Override
				public Object[] fetch(ResultSet rs) throws SQLException {
					Object[] o = new Object[2];
					o[0] = rs.getString(1);
					o[1] = rs.getString(2);
					return o;
				}
			});
		} catch (SQLException e) {
			logger.error("load channels information error!",e);
		}
		return returnlist;
	}

	@Override
	public List<Game> getGames() {
		List<Game> gameList = new ArrayList<Game>();
		
		Connection conn = ConnectionUtil.getConnection(DBResource.MID_STATISTICS);
		
		String sql = "select game_id,game_description,game_name,game_is_online from game";
		
		try {
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			
			while(rs.next()){
				Game game = new Game();
				game.setGameId(rs.getString(1));
				game.setGameName(rs.getString(3));
				game.setGameDescription(rs.getString(2));
				game.setGameIsOnline(rs.getString(4).equals("0")?true:false);
				gameList.add(game);
			}
		} catch (SQLException e) {
			logger.error("sql error",e);
		}
		
		return gameList;
	}

	@Override
	public List<Subgame> getSubgamesByGame(String gameId) {
		
		List<Subgame> subgameList = new ArrayList<Subgame>();
		
		Connection conn = ConnectionUtil.getConnection(DBResource.MID_STATISTICS);
		
		String sql = "select subgame_id,subgame_name,subgame_description,subgame_is_online," +
				"subgame_begin_date,subgame_end_date " +
				" from subgame where game_id =?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gameId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Subgame sg = new Subgame();
				sg.setSubgameId(rs.getString(1));
				sg.setSubgameName(rs.getString(2));
				sg.setSubgameDescription(rs.getString(3));
				sg.setSubgameIsOnline(rs.getString(4).equals("0")?true:false);
				sg.setSubgameBeginDate(new Date(rs.getTimestamp(5).getTime()));
				sg.setSubgameEndDate(rs.getTimestamp(6)==null?null:new Date(rs.getTimestamp(6).getTime()));
				subgameList.add(sg);
			}
		} catch (SQLException e) {
			logger.error("sql error",e);
		}
		
		return subgameList;
	}

	Logger logger = LoggerFactory.getLogger("statistics");

}
