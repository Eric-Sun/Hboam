package com.hboam.am.connector;

public class ConnectorParametersFactory {
	
	private ConnectorParametersFactory(){}
	
	public static ConnectorParameters getConnectorParameters(String protocol){
		if( protocol.equals(Protocol.HTTP))
			return new HTTPConnectorParameters();
		if( protocol.equals(Protocol.LOCAL))
			return new LOCALConnectorParameters();
		if( protocol.equals(Protocol.SOCKET))
			return new SOCKETConnectorParameters();
		throw new IllegalArgumentException(" protocol "+protocol+ " donnot have ConnectorParameters Implementation");
	}

}
