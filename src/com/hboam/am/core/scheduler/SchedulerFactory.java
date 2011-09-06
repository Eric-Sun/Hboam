package com.hboam.am.core.scheduler;

public class SchedulerFactory {

	private SchedulerFactory factory = new SchedulerFactory();
	private SchedulerFactory(){}
	private static Scheduler scheduler = new StandardScheduler();
	public static Scheduler getScheduler(){
		if(scheduler == null)
			scheduler = new StandardScheduler();
		return scheduler;
	}
	
}
