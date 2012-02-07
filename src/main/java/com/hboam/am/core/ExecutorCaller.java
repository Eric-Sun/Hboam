package com.hboam.am.core;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.core.scheduler.Scheduler;
import com.hboam.am.core.scheduler.SchedulerFactory;

public class ExecutorCaller implements Job, Lifecycle {
	
	Logger logger = LoggerFactory.getLogger("core");

	ExecutorConfig cfg = null;
	
	public ExecutorCaller(ExecutorConfig cfg){
		this. cfg = cfg;
	}
	
	public ExecutorCaller(){}

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		ExecutorContext ctxt = (ExecutorContext)arg0.getJobDetail().getJobDataMap().
				get(Constants.CTXT_KEY);
		
		ExecutorConfig cfg = ctxt.getExecutorConfig();
		cfg.getPipeline().setExecutorContext(ctxt);
		Class executorClazz = cfg.getExecutorClazz();
		try {
			Executor e = (Executor)executorClazz.newInstance();
			e.doExecute(ctxt);
		} catch (Exception e) {
			logger.error("error",e);
		}
		
		((Lifecycle)cfg.getPipeline()).init();
		
	}
	
	public void init() {
		Scheduler scheduler = SchedulerFactory.getScheduler();
		
		ExecutorContext ctxt = new StandardExecutorContext();
		ctxt.setExecutorConfig(cfg);
		
		scheduler.addAndStartJob(ctxt);
	}

	public void destroy() {
	}

}
