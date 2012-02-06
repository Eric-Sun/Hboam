package com.hboam.am.statistics.db.source;

import java.util.List;

import com.hboam.am.statistics.core.Dimension;

public interface UserDAO {

	/**
	 * ���ע���û���
	 * @param d
	 * @return
	 */
	public List<Object[]> getRegisterUserNum(Dimension d);
	
	/**
	 * ����ܵ�¼�û���
	 * @param d
	 * @return
	 */
	public List<Object[]> getAllLoginedUserNum(Dimension d);
	
	/**
	 * ������û���¼��
	 * @param d
	 * @return
	 */
	public List<Object[]> getOldLoginedUserNum(Dimension d);
	
	/**
	 * �����ע����û�������Ϸ������������ɫ��
	 * @param d
	 * @return
	 */
	public List<Object[]> getNewIntoGameUserNum(Dimension d);
	
	/**
	 * �����ע���û��Ĺ�5�����û�
	 * @param d
	 * @return
	 */
	public List<Object[]> getNewLevel5UserNum( Dimension d );
	
}
