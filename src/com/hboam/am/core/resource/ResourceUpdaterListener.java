package com.hboam.am.core.resource;

public interface ResourceUpdaterListener {
	
	public void onChanged();
	
	public void resourceFile(String absoluteFilePath);
	
	public String signature();

}
