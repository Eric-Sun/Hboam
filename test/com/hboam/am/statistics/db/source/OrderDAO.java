package com.hboam.am.statistics.db.source;

import java.util.List;

import com.hboam.am.statistics.core.Dimension;

public interface OrderDAO {
	
	/**
	 * ���15�����ʧ�û�����
	 * @param d
	 * @return
	 */
	public List<Object[]> getLossUserNum(Dimension d);
	
	/**
	 * ��ó�ֵ�˻��� 
	 * @param d
	 * @return
	 */
	public List<Object[]> getChargedUserNum(Dimension d);
	
	/**
	 * ����³�ֵ���˻���
	 * @param d
	 * @return
	 */
	public List<Object[]> getNewChargedUserNum(Dimension d);

}
