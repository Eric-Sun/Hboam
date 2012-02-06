package com.hboam.am.core.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper {
	
	public Object[] fetch(ResultSet rs) throws SQLException;

}
