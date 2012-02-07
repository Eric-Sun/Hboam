package com.hboam.am.core.scheduler;

import com.hboam.am.core.ExecutorContext;

public class ExecutorKey {

	private String componentName;
	private String executorName;
	public ExecutorContext executorContext;
	
	
	
	public ExecutorContext getExecutorContext() {
		return executorContext;
	}
	public void setExecutorContext(ExecutorContext executorContext) {
		this.executorContext = executorContext;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getExecutorName() {
		return executorName;
	}
	public void setExecutorName(String executorName) {
		this.executorName = executorName;
	}
	
	public String toString(){
		return componentName+"."+executorName;
	}
}
