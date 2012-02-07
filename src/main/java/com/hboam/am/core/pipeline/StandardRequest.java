package com.hboam.am.core.pipeline;

import java.util.HashMap;
import java.util.Map;

public class StandardRequest implements Request {

	private Map<String, Object> m = new HashMap<String, Object>();
	
	@Override
	public void setParameter(String key, Object value) {
		m.put(key, value);
	}

	@Override
	public Object getParameter(String key) {
		return m.get(key);
	}

}
