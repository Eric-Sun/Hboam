package com.hboam.am.core.pipeline;

import java.util.Map;

public interface Valve {
	
	public void handle(Request req,Response resp);

	public void setNext(Valve v);
	
	public void setPipeline(Pipeline p);
	
	public void setParams(Map<String, String> paramsMap);
	
}
