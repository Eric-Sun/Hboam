package com.hboam.am.core.pipeline;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.hboam.am.core.ExecutorContext;
import com.hboam.am.core.Lifecycle;

public class StandardPipeline implements Pipeline, Lifecycle {
	
	public ExecutorContext getExecutorContext() {
		
		return ctxt;
	}

	private List<Valve> vList = new LinkedList<Valve>();

	public void init() {
	}

	public void destroy() {
	}

	private ExecutorContext ctxt = null;
	public void setExecutorContext(ExecutorContext ctxt) {
		this.ctxt = ctxt;
	}
	public void addValve(Valve v,Map<String, String> paramsMap) {
		v.setPipeline(this);
		
		if( vList.size()!=0 )
			vList.get(vList.size()-1).setNext(v);
		vList.add(v);
		if( v instanceof Lifecycle )
			((Lifecycle)v).init();
		
		v.setParams(paramsMap);
	}

	public void handle() {
		
		if(vList.size()!=0){
			
			Response resp = new StandardResponse();
			
			Request  req= new StandardRequest();

			vList.get(0).handle(req, resp);
			
		}

	}

}
