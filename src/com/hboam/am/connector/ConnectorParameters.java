package com.hboam.am.connector;

public class ConnectorParameters {

	/**
	 * socket
	 */
	private String ip;
	private String port;
	/**
	 * socket & local
	 */
	private String cmd;
	/**
	 * http
	 */
	private String url;
	private String postData;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPostData() {
		return postData;
	}
	public void setPostData(String postData) {
		this.postData = postData;
	}
	
}