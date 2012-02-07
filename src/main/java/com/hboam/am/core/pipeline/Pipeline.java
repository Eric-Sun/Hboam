package com.hboam.am.core.pipeline;

import java.util.List;
import java.util.Map;

import com.hboam.am.core.ExecutorContext;

public interface Pipeline {
	
	public void addValve(Valve v,Map<String, String> paramsMap);
	
	public void handle();
	
	public void setExecutorContext(ExecutorContext ctxt);
	
	public ExecutorContext getExecutorContext();
	
}
