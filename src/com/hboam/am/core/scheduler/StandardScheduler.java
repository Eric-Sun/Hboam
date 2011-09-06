package com.hboam.am.core.scheduler;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import java.io.IOException;
import java.text.ParseException;

import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.core.Constants;
import com.hboam.am.core.Executor;
import com.hboam.am.core.ExecutorCaller;
import com.hboam.am.core.ExecutorConfig;
import com.hboam.am.core.ExecutorContext;

public class StandardScheduler implements Scheduler {

	

	Logger logger = LoggerFactory.getLogger("core");

	private org.quartz.Scheduler scheduler;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			logger.error("quartz scheduler created Unsuccessfully", e);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			logger.error("quartz scheduler shutdowned Unsuccessfully", e);
		}
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		try {
			scheduler.start();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			logger.error("quartz scheduler started Unsuccessfully", e);
		}
	}

	@Override
	public void addAndStartJob(ExecutorContext ctxt) {
		ExecutorConfig cfg = ctxt.getExecutorConfig();
		String componentName = cfg.getComponent().getComponentName();
		
		String executorName = cfg.getExecutorName();
		
		// TODO Auto-generated method stub
		JobDetail job = newJob(ExecutorCaller.class).withIdentity(
				componentName, executorName)
				.build();

		// Trigger the job to run now, and then repeat every 40 seconds
		Trigger trigger;
		try {
			trigger = newTrigger()
					.withIdentity(componentName,
							executorName).startNow()
					.withSchedule(cronSchedule(cfg.getCron())).build();

			job.getJobDataMap().put(Constants.CTXT_KEY, ctxt);
			
			// Tell quartz to schedule the job using our trigger
			scheduler.scheduleJob(job, trigger);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Job started Unsuccessfully Key=" + componentName+"."+executorName, e);
		}

	}
	
	
}
