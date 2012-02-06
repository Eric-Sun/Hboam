package com.hboam.am.statistics.core;

public class Channel {
	
	
	
	private String channelId;
	private String channelName;
	private String channelDescription;
	public Channel(String channelId){
		this.channelId = channelId ;
	}
	public Channel(){}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelDescription() {
		return channelDescription;
	}
	public void setChannelDescription(String channelDescription) {
		this.channelDescription = channelDescription;
	}
	
	public String toString(){
		return "["+channelId+","+channelName+"]";
	}

}
