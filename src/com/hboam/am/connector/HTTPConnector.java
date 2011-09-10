package com.hboam.am.connector;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.core.Lifecycle;
import com.hboam.am.util.HttpUtil;

public class HTTPConnector implements Connector, Lifecycle {
	HTTPConnectorParameters connectorParam;
	Logger logger = LoggerFactory.getLogger("core");
	@Override
	public void init() {
	}

	@Override
	public void destroy() {
	}

	@Override
	public Object call(ConnectorParameters params)
			throws ConnectorRuntimeException {
		HttpUtil http = new HttpUtil();
		
		try {
			return http.requestHttpContent(connectorParam.getUrl());
		} catch (IOException e) {
			logger.error("e",e);
			return null;
		}
	}

	@Override
	public void validate(ConnectorParameters params)
			throws ConnectorParametersException {
		if ( !(  params instanceof HTTPConnectorParameters) )
			throw new IllegalArgumentException(" Connector Parameters is error");
		
		this.connectorParam = (HTTPConnectorParameters)params;
	}

}
