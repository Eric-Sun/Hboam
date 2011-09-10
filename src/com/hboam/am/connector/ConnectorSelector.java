package com.hboam.am.connector;

import java.util.HashMap;
import java.util.Map;

public class ConnectorSelector {

	private Map<String, Connector> map = new HashMap<String, Connector>();
	
	public void add(String s, Connector c){
		map.put(s, c);
	}
	
	public Connector get(String s){
		return map.get(s);
	}
	
}
