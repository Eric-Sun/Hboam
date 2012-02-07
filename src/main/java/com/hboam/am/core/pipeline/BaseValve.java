package com.hboam.am.core.pipeline;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.hboam.am.core.Lifecycle;

public abstract class BaseValve implements Valve  {

	@Override
	public void setParams(Map<String, String> paramsMap) {
		this.paramsMap = paramsMap;
	}

	private Pipeline pipeline = null;
	private Map<String, String> paramsMap = new HashMap<String, String>();
	private Valve nextValve = null;
	
	@Override
	public void handle(Request req, Response resp) {
		
		for(String key : paramsMap.keySet()){
			String value = paramsMap.get(key);
			req.setParameter(key, value);
		}
		doHandle(req, resp);
		if( nextValve != null )
			nextValve.handle(req, resp);
	}
	
	protected abstract void doHandle(Request req, Response resp);

	@Override
	public void setNext(Valve nextValve) {
		this.nextValve = nextValve;
	}

	@Override
	public void setPipeline(Pipeline p) {
		this.pipeline = p;
	}
	
	public Pipeline getPipeline(){
		return pipeline;
	}

	/**
	 * For create the xml file automaticlly
	 * @param fileName
	 * @return
	 */
	public String createXmlFilePath(String fileName){
		String basePath = pipeline.getExecutorContext().getExecutorConfig().getResouceBasePath();
		return basePath+File.separator+fileName;
	}
	
}
