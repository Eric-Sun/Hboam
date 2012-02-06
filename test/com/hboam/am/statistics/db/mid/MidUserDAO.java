package com.hboam.am.statistics.db.mid;

import com.hboam.am.statistics.core.Dimension;

public interface MidUserDAO {

	/**
	 * 
	 * @param d
	 * @param registerUserNum
	 * @param allLoginedUserNum
	 * @param oldLoginedUserNum
	 * @param newIntoGameUserNum
	 * @param newLevel5UserNum
	 */
	public void insert(Dimension d ,int registerUserNum, int allLoginedUserNum, int oldLoginedUserNum,
			int newIntoGameUserNum, int newLevel5UserNum);
	
}
