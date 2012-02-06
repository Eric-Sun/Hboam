package com.hboam.am.statistics.db.source;

import java.util.List;

import com.hboam.am.statistics.core.Dimension;

public interface RevenueDAO {
	
	/**
	 * 获得收入
	 * @param d
	 * @return
	 */
	public List<Object[]> getRevenue(Dimension d);
	
	/**
	 * 获得结算后金额
	 * @param d
	 * @return
	 */
	public List<Object[]> getBalance(Dimension d);
	
	/**
	 * 获得流失账户的收入
	 * @param d
	 * @return
	 */
	public List<Object[]> getLossUserRevenue(Dimension d);
	
	/**
	 * 获得支付方式获得收入
	 * @param d
	 * @param chargeType
	 * @return
	 */
	public List<Object[]> getUserRevenueByChargeType(Dimension d, String chargeType);

}
