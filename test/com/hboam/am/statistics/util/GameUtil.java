package com.hboam.am.statistics.util;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.hboam.am.core.db.DBResource;
import com.hboam.am.core.db.JDBCTemplate;
import com.hboam.am.statistics.core.Game;
import com.hboam.am.statistics.core.Subgame;
import com.hboam.am.util.ConnectionUtil;

public class GameUtil {
	
	public static List<JDBCTemplate> getDBTemplates(Game game){
		
		List<String> list = new ArrayList<String>(game.getSubgameMap().keySet());
		
		List<JDBCTemplate>  tList = new ArrayList<JDBCTemplate>();
		
		for ( String dbResource : list ){
			if(dbResource.equals("ALLSUBGAME"))
				continue;
			Connection conn = ConnectionUtil.getConnection(dbResource);
			JDBCTemplate t  = new JDBCTemplate(conn);
			tList.add(t);
		}
		
		return tList;
		
	}
	
	public static List<JDBCTemplate> getDBTemplates(Game game,Subgame subgame){
		
		if( subgame == null || subgame.getSubgameId().toUpperCase().equals("ALLSUBGAME"))
			return getDBTemplates(game);
		
		List<JDBCTemplate> list = new ArrayList<JDBCTemplate>();
		list.add(new JDBCTemplate(ConnectionUtil.getConnection(subgame.getSubgameId())));
		return list;
	}
	
	
	

}
