package com.hboam.am.statistics.db.source;

import java.util.Date;
import java.util.List;

import com.hboam.am.statistics.core.Channel;
import com.hboam.am.statistics.core.Dimension;
import com.hboam.am.statistics.core.Game;
import com.hboam.am.statistics.core.Subgame;

public interface OnlineDAO {
	
	/**
	 * 获得最高在线数和平均在线数
	 * @param game
	 * @param subgame
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<Object[]> getMaxAndAvgOnline(Dimension d);
	
	
	/**
	 * 获得所有用户的平均在线时间
	 * @param game
	 * @param subgame
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<Object[]> getAllAvgOnlineTime(Dimension d);
	
	/**
	 * 获得新注册用户的平均在线时间
	 * @param game
	 * @param subgame
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<Object[]> getNewAvgOnlineTime(Dimension d);
	

	/**
	 * 获得新注册用户的平均在线时间
	 * @param game
	 * @param subgame
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<Object[]> getOldAvgOnlineTime(Dimension d);
	
	/**
	 * 获得所有在线时间
	 * @param d
	 * @return
	 */
	public List<Object[]> getAllOnlineMinutes(Dimension d);
	
	/**
	 * 获得所有在线的人数
	 * @param d
	 * @return
	 */
	public List<Object[]> getAllOnlineUserNum(Dimension d);
	
}
