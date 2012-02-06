package com.hboam.am.statistics.db.source;

import java.util.Date;
import java.util.List;

import com.hboam.am.statistics.core.Channel;
import com.hboam.am.statistics.core.Game;
import com.hboam.am.statistics.core.Subgame;

public interface ResourceDAO {

	/**
	 * 获得所有的游戏
	 * @return
	 */
	public List<Game> getGames();
	
	/**
	 * 获得对应游戏下的所有的分区
	 * @param gameId
	 * @return
	 */
	public List<Subgame> getSubgamesByGame(String gameId);
	
	/**
	 * 获得对应日期内的所有的有注册量的渠道集合
	 * @param date
	 * @return
	 */
	public List<Object[]> getAllChannelsHaveRegister(Date beginDate,Date endDate);
}
