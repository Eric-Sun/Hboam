package com.hboam.am.core;

import java.util.List;

/**
 * Executor是一个执行器，每一个需要调度的作业需要继承的接口，通过复写<code>doExecute</code>接口
 * 来实现作业的根本，可以通过<code>getExecutorContext()</code>方法获得对应的上下文，包括，配置文件和其他信息
 * @author Eric
 * @since 0.0.2
 */
public interface Executor {
	
	public void doExecute(ExecutorContext cfg);
	
}
