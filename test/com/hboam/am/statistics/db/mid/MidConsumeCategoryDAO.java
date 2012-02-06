package com.hboam.am.statistics.db.mid;

import com.hboam.am.statistics.core.Dimension;

public interface MidConsumeCategoryDAO {
	
	public void insert(Dimension d,String category, int consumeSum);

}
