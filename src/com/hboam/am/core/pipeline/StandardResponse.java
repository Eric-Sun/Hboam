package com.hboam.am.core.pipeline;

import java.util.HashMap;
import java.util.Map;

public class StandardResponse implements Response {

	private Map<String, Object> m = new HashMap<String, Object>();
	
	
	@Override
	public void setResult(String key, String value) {
		m.put(key, value);
	}

	@Override
	public Object getResult(String key) {
		return m.get(key);
		
	}

}
