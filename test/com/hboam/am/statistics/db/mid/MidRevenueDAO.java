package com.hboam.am.statistics.db.mid;

import java.util.List;

import com.hboam.am.statistics.core.Dimension;

public interface MidRevenueDAO {
	
	/**
	 * ��������ͳ���м��
	 * @param d
	 * @param revenue
	 * @param balance
	 * @param lossUserRevenue
	 * @param chargedUserNum
	 * @param newChargedUserNum 
	 * @param xiaoeRevenue 
	 * @param szxRevenue 
	 * @return
	 */
	public void insert(Dimension d, float revenue,float balance,float lossUserRevenue, 
			int chargedUserNum, float szxRevenue, float xiaoeRevenue, int newChargedUserNum);

}
