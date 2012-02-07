package com.hboam.am.core;

import com.hboam.am.core.scheduler.ExecutorKey;

/**
 * the Object represent the context in the executor.<p>
 * Now ( since 0.0.2),the ExecutorConfig is involved
 * @author Eric
 *
 */
public interface ExecutorContext {
	
	public ExecutorConfig getExecutorConfig();
	
	public void setExecutorConfig(ExecutorConfig cfg);
	
}
