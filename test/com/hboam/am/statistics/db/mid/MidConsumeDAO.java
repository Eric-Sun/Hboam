package com.hboam.am.statistics.db.mid;

import java.util.List;

import com.hboam.am.statistics.core.Dimension;

public interface MidConsumeDAO {
	
	public void insert(Dimension d,int consumeSum,int consumedRoleNum);

}
