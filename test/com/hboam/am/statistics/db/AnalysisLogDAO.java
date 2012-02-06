package com.hboam.am.statistics.db;

import java.util.Date;

import com.hboam.am.statistics.core.Channel;
import com.hboam.am.statistics.core.Dimension;
import com.hboam.am.statistics.core.Game;
import com.hboam.am.statistics.core.Subgame;

public interface AnalysisLogDAO {
	
	/**
	 * 插入selector并且设置为已经完成
	 * @param game
	 * @param subgame
	 * @param beginDate
	 * @param endDate
	 * @param selectorKey
	 */
	public void insert(Dimension d, String selectorKey, String period);
	
	
	/**
	 * 检测这组操作是不是已经做过了
	 * @param game
	 * @param subgame
	 * @param beginDate
	 * @param endDate
	 * @param selectorKey
	 * @param channel 
	 * @return
	 */
	public boolean check(Dimension d , String selectorKey, String period);
	
}
