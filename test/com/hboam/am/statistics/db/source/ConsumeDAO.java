package com.hboam.am.statistics.db.source;

import java.util.List;

import com.hboam.am.statistics.core.Dimension;

public interface ConsumeDAO {
	
	/**
	 * 获得消费的金额
	 * @param d
	 * @return
	 */
	public List<Object[]> getConsumeSum(Dimension d);
	
	/**
	 * 获得消费的角色数
	 * @param d
	 * @return
	 */
	public List<Object[]> getConsumedRoleNum(Dimension d);
	
	/**
	 * 通过分类获得消费的总金额
	 * @param d
	 * @param consumeCategoryPattern
	 * @return
	 */
	public List<Object[]> getConsumeSumByCategory(Dimension d,String consumeCategoryPattern);

}
