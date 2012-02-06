package com.hboam.am.statistics.core;

import java.util.Date;

public class Subgame {
	
	private String subgameId;
	private boolean subgameIsOnline;
	private String subgameDescription;
	private String subgameName;
	private Date subgameBeginDate;
	private Date subgameEndDate;
	public Date getSubgameBeginDate() {
		return subgameBeginDate;
	}
	public void setSubgameBeginDate(Date subgameBeginDate) {
		this.subgameBeginDate = subgameBeginDate;
	}
	public Date getSubgameEndDate() {
		return subgameEndDate;
	}
	public void setSubgameEndDate(Date subgameEndDate) {
		this.subgameEndDate = subgameEndDate;
	}
	public String getSubgameId() {
		return subgameId;
	}
	public void setSubgameId(String subgameId) {
		this.subgameId = subgameId;
	}
	public boolean isSubgameIsOnline() {
		return subgameIsOnline;
	}
	public void setSubgameIsOnline(boolean subgameIsOnline) {
		this.subgameIsOnline = subgameIsOnline;
	}
	public String getSubgameDescription() {
		return subgameDescription;
	}
	public void setSubgameDescription(String subgameDescription) {
		this.subgameDescription = subgameDescription;
	}
	public String getSubgameName() {
		return subgameName;
	}
	public void setSubgameName(String subgameName) {
		this.subgameName = subgameName;
	}

}
