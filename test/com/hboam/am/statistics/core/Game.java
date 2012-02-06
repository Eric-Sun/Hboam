package com.hboam.am.statistics.core;

import java.util.HashMap;
import java.util.Map;

public class Game {
	
	private String gameName;
	private String gameId;
	private String gameDescription;
	private boolean gameIsOnline;
	private Map<String, Subgame> subgameMap = new HashMap<String, Subgame>();
	public boolean isGameIsOnline() {
		return gameIsOnline;
	}
	public void setGameIsOnline(boolean gameIsOnline) {
		this.gameIsOnline = gameIsOnline;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public String getGameDescription() {
		return gameDescription;
	}
	public void setGameDescription(String gameDescription) {
		this.gameDescription = gameDescription;
	}
	public Map<String, Subgame> getSubgameMap() {
		return subgameMap;
	}
	public void setSubgameMap(Map<String, Subgame> subgameMap) {
		this.subgameMap = subgameMap;
	}
	

}
