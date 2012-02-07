package com.hboam.am.core;

import java.util.List;

/**
 * Executor��һ��ִ������ÿһ����Ҫ���ȵ���ҵ��Ҫ�̳еĽӿڣ�ͨ����д<code>doExecute</code>�ӿ�
 * ��ʵ����ҵ�ĸ���������ͨ��<code>getExecutorContext()</code>������ö�Ӧ�������ģ������������ļ���������Ϣ
 * @author Eric
 * @since 0.0.2
 */
public interface Executor {
	
	public void doExecute(ExecutorContext cfg);
	
}
