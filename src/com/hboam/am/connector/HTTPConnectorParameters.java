package com.hboam.am.connector;

public class HTTPConnectorParameters implements ConnectorParameters {
	/**
	 * http
	 */
	private String url;
	private String postData;
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
