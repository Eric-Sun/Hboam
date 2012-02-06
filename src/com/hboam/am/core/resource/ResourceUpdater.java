package com.hboam.am.core.resource;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceUpdater implements Runnable {
	
	Logger logger = LoggerFactory.getLogger("core");
	
	private Map<String , Long> filePathModifiedTimeMap = new HashMap<String, Long>();
	
	private Map<String, ResourceUpdaterListener> listenerMap = new HashMap<String, ResourceUpdaterListener>();
	
	/**
	 * interval of monitor is 1s
	 */
	private static final long INTERVAL = 1000;
	
	private long lastTickTimestamp = 0;
	public void addListener(ResourceUpdaterListener listener){
		listenerMap.put(listener.signature(), listener);
	}
	
	@Override
	public void run() {
		
		while(true){
			
			if( System.currentTimeMillis() - lastTickTimestamp >= INTERVAL ){
				trigger();
				lastTickTimestamp = System.currentTimeMillis();
			}
			
		}
		
	}
	
	private void trigger(){
		
		Set<String> fileSignatureSet = filePathModifiedTimeMap.keySet();
		
		for ( String fileSignature : fileSignatureSet ){
			File file = new File(fileSignature);
			long currentLastModifiedTime = file.lastModified();
			Long lastestLastModifiedTime = filePathModifiedTimeMap.get(fileSignature);
			// if different, invoke onChanged method
			if( lastestLastModifiedTime!=null && 
					currentLastModifiedTime != lastestLastModifiedTime ){
				ResourceUpdaterListener listener = listenerMap.get(fileSignature);
				listener.onChanged();
				logger.info("Reloading the resource. signature = "+listener.signature());
				filePathModifiedTimeMap.put(fileSignature, currentLastModifiedTime);
				logger.info("Reload the resource successfully. signature = "+listener.signature());
			}
		}
		
	}

}
