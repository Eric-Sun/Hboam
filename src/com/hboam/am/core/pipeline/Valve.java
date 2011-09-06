package com.hboam.am.core.pipeline;

public interface Valve {
	
	public void handle(Request req,Response resp);

	public void setNext(Valve v);
	
	public void setPipeline(Pipeline p);
	
}
