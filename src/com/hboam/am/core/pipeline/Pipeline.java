package com.hboam.am.core.pipeline;

import com.hboam.am.core.ExecutorContext;

public interface Pipeline {
	
	public void addValve(Valve v);
	
	public void handle();
	
	public void setExecutorContext(ExecutorContext ctxt);
	
	public ExecutorContext getExecutorContext();
	
}
