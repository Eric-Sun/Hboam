package com.hboam.am.core;

import java.util.HashMap;
import java.util.Map;

public class Constants {
	/**
	 * ��quartz�е�context��key
	 */
	public final static String CTXT_KEY="HBOAM_KEY";
	
	/**
	 * �����ļ���û�� executor��config������ʵ�ֵ�ʱ��ʹ��Ĭ�ϵ�ʵ�ֵ���
	 */
	public final static String DEFAULT_EXECUTOR_CONFIG_CLASS_NAME="com.hboam.am.core.StandardExecutorConfig";
	
	/**
	 * Ĭ�ϵ�component��ʵ����
	 */
	public final static String DEFAULT_COMPONENT_CLASS = "com.hboam.am.core.StandardComponent";
	
	
	public final static Map<String, String> connectorMapping = new HashMap<String, String>();
	
	static{
		connectorMapping.put("HTTP", "com.hboam.am.connector.HTTPConnector");
		connectorMapping.put("LOCAL", "com.hboam.am.connector.LOCALConnector");
	}
	
	public final static String DEFAULT_EXECUTOR_CLASS = "com.hboam.am.core.StandardExecutor";
	
}
