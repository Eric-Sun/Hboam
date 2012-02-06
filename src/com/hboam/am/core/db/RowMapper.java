package com.hboam.am.core.db;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 结果集的获取的方法啊，在query方法中传入这个接口的实现，
 * 用来在完成查询之后获得ResultSet中的内容
 * @author Eric
 *
 */
public interface RowMapper {
	
	/**
	 * 获取的方法
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public Object[] fetch(ResultSet rs) throws SQLException;

}
