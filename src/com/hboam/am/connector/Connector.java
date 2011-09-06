package com.hboam.am.connector;

public interface Connector {

	public Object call(ConnectorParameters params) throws ConnectorRuntimeException;
	
	public void validate(ConnectorParameters params) throws ConnectorParametersException;
	
}
