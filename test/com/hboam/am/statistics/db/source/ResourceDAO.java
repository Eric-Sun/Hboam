package com.hboam.am.statistics.db.source;

import java.util.Date;
import java.util.List;

import com.hboam.am.statistics.core.Channel;
import com.hboam.am.statistics.core.Game;
import com.hboam.am.statistics.core.Subgame;

public interface ResourceDAO {

	/**
	 * ������е���Ϸ
	 * @return
	 */
	public List<Game> getGames();
	
	/**
	 * ��ö�Ӧ��Ϸ�µ����еķ���
	 * @param gameId
	 * @return
	 */
	public List<Subgame> getSubgamesByGame(String gameId);
	
	/**
	 * ��ö�Ӧ�����ڵ����е���ע��������������
	 * @param date
	 * @return
	 */
	public List<Object[]> getAllChannelsHaveRegister(Date beginDate,Date endDate);
}
