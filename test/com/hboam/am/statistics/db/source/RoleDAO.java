package com.hboam.am.statistics.db.source;

import java.util.List;

import com.hboam.am.statistics.core.Dimension;

public interface RoleDAO {
	
	/**
	 * ��õ�¼�Ľ�ɫ��
	 * @param d
	 * @return
	 */
	List<Object[]> getLoginedRoleNum(Dimension d);

}
