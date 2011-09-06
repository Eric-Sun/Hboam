package com.hboam.am.core;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.hboam.am.core.scheduler.ExecutorKey;
import com.hboam.am.core.scheduler.Scheduler;
import com.hboam.am.core.scheduler.SchedulerFactory;

public class StandardExecutorContext implements ExecutorContext {

	
	private ExecutorConfig cfg;
	
	@Override
	public ExecutorConfig getExecutorConfig() {
		
		return cfg;
	}

	@Override
	public void setExecutorConfig(ExecutorConfig cfg) {
		this.cfg = cfg;
	}


	

}
