package com.hboam.am.statistics.db.source;

import java.util.List;

import com.hboam.am.statistics.core.Dimension;

public interface ConsumeDAO {
	
	/**
	 * ������ѵĽ��
	 * @param d
	 * @return
	 */
	public List<Object[]> getConsumeSum(Dimension d);
	
	/**
	 * ������ѵĽ�ɫ��
	 * @param d
	 * @return
	 */
	public List<Object[]> getConsumedRoleNum(Dimension d);
	
	/**
	 * ͨ�����������ѵ��ܽ��
	 * @param d
	 * @param consumeCategoryPattern
	 * @return
	 */
	public List<Object[]> getConsumeSumByCategory(Dimension d,String consumeCategoryPattern);

}
