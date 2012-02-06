package com.hboam.am.statistics.db.source;

import java.util.List;

import com.hboam.am.statistics.core.Dimension;

public interface RoleDAO {
	
	/**
	 * 获得登录的角色数
	 * @param d
	 * @return
	 */
	List<Object[]> getLoginedRoleNum(Dimension d);

}
