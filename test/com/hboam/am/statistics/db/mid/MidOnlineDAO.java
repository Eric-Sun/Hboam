package com.hboam.am.statistics.db.mid;

import java.util.Date;
import java.util.List;

import com.hboam.am.statistics.core.Channel;
import com.hboam.am.statistics.core.Dimension;
import com.hboam.am.statistics.core.Game;
import com.hboam.am.statistics.core.Subgame;

public interface MidOnlineDAO {

	/**
	 * 生成online的中间表
	 * @param game
	 * @param subgame
	 * @param maxOnlineNum
	 * @param avgOnlineNum
	 * @param newAvgOnlineTime
	 * @param allAvgOnlineTime
	 * @param allOnlineUserNum 
	 * @param allOnlineMinutes 
	 * @param beginDate
	 * @param endDate
	 * @param channel 
	 * @return
	 */
	public void insertIntoMidOnline(Dimension d,int maxOnlineNum,int avgOnlineNum,
			float newAvgOnlineTime, float oldAvgOnlineTime,float allAvgOnlineTime, String period,
			int allOnlineMinutes, int allOnlineUserNum
			);
	
	
}
