package com.hboam.am.core.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.hboam.am.core.Lifecycle;
import com.hboam.am.util.ConnectionUtil;

public abstract class AbstractDBHandler implements Lifecycle {
	
	Logger logger = LoggerFactory.getLogger("log");
	protected static String DB = null;
	protected void setDB(String dB) {
		DB = dB;
	}

	@Override
	public void init() {
	}

	
}
