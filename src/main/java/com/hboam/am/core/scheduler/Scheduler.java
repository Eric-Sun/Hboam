package com.hboam.am.core.scheduler;

import com.hboam.am.core.ExecutorConfig;
import com.hboam.am.core.ExecutorContext;
import com.hboam.am.core.Lifecycle;

public interface Scheduler extends Lifecycle{

	public void start();
	
	public void addAndStartJob(ExecutorContext ctxt);
	
	
}
