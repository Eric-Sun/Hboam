package com.hboam.am.connector;

public class ConnectorParametersException extends RuntimeException {

	public ConnectorParametersException(Throwable msg){
		super(msg);
	}
	public ConnectorParametersException(String msg){
		super(msg);
	}
}
