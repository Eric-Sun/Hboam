package com.hboam.am.statistics.core;

import java.util.Date;

public class Dimension {

	private Game game;
	private Subgame subgame;
	private Date beginDate;
	private Date endDate;
	private Channel channel;
	private String period;
	
	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Subgame getSubgame() {
		return subgame;
	}

	public void setSubgame(Subgame subgame) {
		this.subgame = subgame;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String toString(){
		return "[game="+game.getGameId()+",subgame="+subgame.getSubgameId()+",beginDate="+
					beginDate+",endDate="+endDate+",channel="+channel.getChannelId()+"]";
	}
	
	
}
