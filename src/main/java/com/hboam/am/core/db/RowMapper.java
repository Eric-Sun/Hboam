package com.hboam.am.core.db;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ������Ļ�ȡ�ķ���������query�����д�������ӿڵ�ʵ�֣�
 * ��������ɲ�ѯ֮����ResultSet�е�����
 * @author Eric
 *
 */
public interface RowMapper {
	
	/**
	 * ��ȡ�ķ���
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public Object[] fetch(ResultSet rs) throws SQLException;

}
