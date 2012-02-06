package com.hboam.am.statistics.db.source;

import java.util.List;

import com.hboam.am.statistics.core.Dimension;

public interface UserDAO {

	/**
	 * 获得注册用户数
	 * @param d
	 * @return
	 */
	public List<Object[]> getRegisterUserNum(Dimension d);
	
	/**
	 * 获得总登录用户数
	 * @param d
	 * @return
	 */
	public List<Object[]> getAllLoginedUserNum(Dimension d);
	
	/**
	 * 获得老用户登录数
	 * @param d
	 * @return
	 */
	public List<Object[]> getOldLoginedUserNum(Dimension d);
	
	/**
	 * 获得新注册的用户进入游戏的数（创建角色）
	 * @param d
	 * @return
	 */
	public List<Object[]> getNewIntoGameUserNum(Dimension d);
	
	/**
	 * 获得新注册用户的过5级的用户
	 * @param d
	 * @return
	 */
	public List<Object[]> getNewLevel5UserNum( Dimension d );
	
}
