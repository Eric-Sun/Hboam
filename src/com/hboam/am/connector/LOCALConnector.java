package com.hboam.am.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.core.LOGLevel;
import com.hboam.am.core.Lifecycle;

public class LOCALConnector implements Connector, Lifecycle {

	Logger logger = LoggerFactory.getLogger(LOGLevel.CONNECTOR);

	public void init() {
		
	}

	public void destroy() {
		
		
	}

	public Object call(ConnectorParameters params) throws ConnectorRuntimeException {
		
		Runtime runtime = Runtime.getRuntime();
//		String[] cmd = { "/bin/sh", "-c", params.getCmd()}; 
		String[] cmd= {params.getCmd()};
		Process pro;
		BufferedReader br;
		try {
			pro = runtime.exec(params.getCmd());
			int exitVal = pro.exitValue();
			br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			return br;
		} catch (Exception e) {
			throw new ConnectorRuntimeException(e);
		}
	}
	
	public void validate(ConnectorParameters params) throws ConnectorParametersException{
		if(params.getCmd()==null)
			throw new ConnectorParametersException("cmd is needed!");
		
	}

}
