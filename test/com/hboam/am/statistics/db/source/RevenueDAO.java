package com.hboam.am.statistics.db.source;

import java.util.List;

import com.hboam.am.statistics.core.Dimension;

public interface RevenueDAO {
	
	/**
	 * �������
	 * @param d
	 * @return
	 */
	public List<Object[]> getRevenue(Dimension d);
	
	/**
	 * ��ý������
	 * @param d
	 * @return
	 */
	public List<Object[]> getBalance(Dimension d);
	
	/**
	 * �����ʧ�˻�������
	 * @param d
	 * @return
	 */
	public List<Object[]> getLossUserRevenue(Dimension d);
	
	/**
	 * ���֧����ʽ�������
	 * @param d
	 * @param chargeType
	 * @return
	 */
	public List<Object[]> getUserRevenueByChargeType(Dimension d, String chargeType);

}
