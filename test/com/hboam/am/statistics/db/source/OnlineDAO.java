package com.hboam.am.statistics.db.source;

import java.util.Date;
import java.util.List;

import com.hboam.am.statistics.core.Channel;
import com.hboam.am.statistics.core.Dimension;
import com.hboam.am.statistics.core.Game;
import com.hboam.am.statistics.core.Subgame;

public interface OnlineDAO {
	
	/**
	 * ��������������ƽ��������
	 * @param game
	 * @param subgame
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<Object[]> getMaxAndAvgOnline(Dimension d);
	
	
	/**
	 * ��������û���ƽ������ʱ��
	 * @param game
	 * @param subgame
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<Object[]> getAllAvgOnlineTime(Dimension d);
	
	/**
	 * �����ע���û���ƽ������ʱ��
	 * @param game
	 * @param subgame
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<Object[]> getNewAvgOnlineTime(Dimension d);
	

	/**
	 * �����ע���û���ƽ������ʱ��
	 * @param game
	 * @param subgame
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<Object[]> getOldAvgOnlineTime(Dimension d);
	
	/**
	 * �����������ʱ��
	 * @param d
	 * @return
	 */
	public List<Object[]> getAllOnlineMinutes(Dimension d);
	
	/**
	 * ����������ߵ�����
	 * @param d
	 * @return
	 */
	public List<Object[]> getAllOnlineUserNum(Dimension d);
	
}
