package com.hboam.am.monitor.valve;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.core.pipeline.BaseValve;
import com.hboam.am.core.pipeline.Request;
import com.hboam.am.core.pipeline.Response;

public abstract class MonitorBaseValve extends BaseValve {
	
	public Logger logger = LoggerFactory.getLogger("monitor");
	

	protected abstract void doMonitorHandle(Request arg0, Response arg1) ;

	@Override
	protected void doHandle(Request arg0, Response arg1) {
		
		doMonitorHandle(arg0, arg1);
		
	}
		

}
