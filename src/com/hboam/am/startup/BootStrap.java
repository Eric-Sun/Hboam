package com.hboam.am.startup;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.core.db.DBConnectionFactory;
import com.hboam.am.core.resource.XMLResourceLoader;
import com.hboam.am.core.scheduler.Scheduler;
import com.hboam.am.core.scheduler.SchedulerFactory;

public class BootStrap {
	static Logger logger = LoggerFactory.getLogger("core");
	/**
	 * initialize and start the system.
	 */
	public void init(){
	
		// initialize the log4j conf
		DOMConfigurator.configure("conf/log4j.xml"	);
		
		// initialize the scheduler 
		// It will initialize the scheduler like the quartz.
		// And prepare for the executor(job as quartz) to embed in
		
		Scheduler sche = SchedulerFactory.getScheduler();
		sche.init();
		sche.start();
		logger.info("Start scheduler SuccessFully!");
		
		// initialize the connection factory.
		// It will generate the connection for every executor.
		// Without a connection pool.WARNING
		DBConnectionFactory fac = new DBConnectionFactory();
		fac.init();
		logger.info("Start Connection Factory SuccessFully!");
		
		// initialize the configuration loader .For load all the resource( as xml files)
		XMLResourceLoader resourceLoader = new XMLResourceLoader();
		resourceLoader.init();
		
 		logger.info("Resource Load SuccessFully!");
		
	}
	
	public static void main(String[] args){
		
		BootStrap bootStrap = new BootStrap();
		
		bootStrap.init();
		
	}
	
}
