package com.hboam.am.core;

import com.hboam.am.core.Executor;
import com.hboam.am.core.ExecutorContext;

public class StandardExecutor implements Executor {

	@Override
	public void doExecute(ExecutorContext cfg) {

		cfg.getExecutorConfig().getPipeline().handle();

	}

}
