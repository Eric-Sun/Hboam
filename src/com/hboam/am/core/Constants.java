package com.hboam.am.core;

import java.util.HashMap;
import java.util.Map;

public class Constants {
	/**
	 * 在quartz中的context的key
	 */
	public final static String CTXT_KEY="HBOAM_KEY";
	
	/**
	 * 配置文件中没有 executor的config的特殊实现的时候，使用默认的实现的类
	 */
	public final static String DEFAULT_EXECUTOR_CONFIG_CLASS_NAME="com.hboam.am.core.StandardExecutorConfig";
	
	/**
	 * 默认的component的实现类
	 */
	public final static String DEFAULT_COMPONENT_CLASS = "com.hboam.am.core.StandardComponent";
	
	
	public final static Map<String, String> connectorMapping = new HashMap<String, String>();
	
	static{
		connectorMapping.put("HTTP", "com.hboam.am.connector.HTTPConnector");
		connectorMapping.put("LOCAL", "com.hboam.am.connector.LOCALConnector");
	}
	
	public final static String DEFAULT_EXECUTOR_CLASS = "com.hboam.am.core.StandardExecutor";
	
}
