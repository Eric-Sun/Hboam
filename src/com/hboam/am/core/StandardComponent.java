package com.hboam.am.core;

import java.util.ArrayList;
import java.util.List;

import com.hboam.am.core.pipeline.Pipeline;



/**
 * 主要实现了链状启动的功能
 * @author Eric
 *
 */
public class StandardComponent implements Component {


	private String componentName ;
	private List<ExecutorCaller> callerList = new ArrayList<ExecutorCaller>();
	
	@Override
	public void init() {
		// load all the executor to scheduler
		for( ExecutorCaller e : callerList ){
			if ( e instanceof Lifecycle ){
				((Lifecycle)e).init();
			}
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getComponentName() {
		return componentName;
	}

	@Override
	public void setComponentName(String componentName) {
		
		this.componentName = componentName;

	}


	@Override
	public void addExecutorConfig(ExecutorConfig e) {
		// TODO Auto-generated method stub
		ExecutorCaller caller = new ExecutorCaller(e);
		callerList.add(caller);
	}
}
