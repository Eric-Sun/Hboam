package com.hboam.am.statistics.db;

import java.util.Date;

import com.hboam.am.statistics.core.Channel;
import com.hboam.am.statistics.core.Dimension;
import com.hboam.am.statistics.core.Game;
import com.hboam.am.statistics.core.Subgame;

public interface AnalysisLogDAO {
	
	/**
	 * ����selector��������Ϊ�Ѿ����
	 * @param game
	 * @param subgame
	 * @param beginDate
	 * @param endDate
	 * @param selectorKey
	 */
	public void insert(Dimension d, String selectorKey, String period);
	
	
	/**
	 * �����������ǲ����Ѿ�������
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
