package com.hboam.am.statistics.db.source;

import java.util.List;

import com.hboam.am.statistics.core.Dimension;

public interface OrderDAO {
	
	/**
	 * 获得15天的流失用户数量
	 * @param d
	 * @return
	 */
	public List<Object[]> getLossUserNum(Dimension d);
	
	/**
	 * 获得充值账户数 
	 * @param d
	 * @return
	 */
	public List<Object[]> getChargedUserNum(Dimension d);
	
	/**
	 * 获得新充值的账户数
	 * @param d
	 * @return
	 */
	public List<Object[]> getNewChargedUserNum(Dimension d);

}
