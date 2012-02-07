package com.hboam.am.core.pipeline;

public interface Request {
	
	public void setParameter(String key,Object value);
	
	public Object getParameter(String key);

}
