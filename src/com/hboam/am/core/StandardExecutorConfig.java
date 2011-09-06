package com.hboam.am.core;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.hboam.am.core.pipeline.Pipeline;
import com.hboam.am.core.scheduler.Scheduler;
import com.hboam.am.core.scheduler.SchedulerFactory;

/**
 * 主要设置了每一个executor对应scheduler的初始化方法
 * @author Eric
 *
 */
public class StandardExecutorConfig implements ExecutorConfig {
	
	private final static String BASE_PATH="conf/";

	private String resPath;

	@Override
	public void setResPath(String resPath) {
		this.resPath = resPath;
	}


	@Override
	public String getResPath() {
		return resPath;
	}

	private final static String RES_DESCRIPTOR = "res";
	Map<String,String> propsMap = null;
	
	private String resourceBasePath = null;
	
	@Override
	public String getResouceBasePath() {
		return resourceBasePath;
	}

	
	public void setPropsMap(Map<String, String> propsMap) {
		this.propsMap = propsMap;
	}

	public Map<String, String> getPropsMap() {
		return propsMap;
	}

	protected String id;
	protected String cron;
	protected String executorName;
	protected Class executorClazz = null;
	protected Pipeline p = null;
	
	@Override
	public void setPipeline(Pipeline p) {
		this. p = p;
	}

	@Override
	public Pipeline getPipeline() {
		
		return p;
	}
	@Override
	public void setExecutorClazz(Class clazz) {
		this.executorClazz = clazz;
		resourceBasePath = BASE_PATH;
	}

	@Override
	public Class getExecutorClazz() {
		
		return executorClazz;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		
		return id;
	}
	
	protected Component component;
	
	@Override
	public void setComponent(Component component) {
		this.component  = component;
	}

	@Override
	public Component getComponent() {
		
		return component;
	}

	@Override
	public void setExecutorName(String executorName) {
		this.executorName = executorName;
		
	}

	@Override
	public String getExecutorName() {
		return executorName;
	}

	@Override
	public String getCron() {
		return  cron;
	}

	@Override
	public void setCron(String cron) {
		this . cron =  cron;
		
	}
}
