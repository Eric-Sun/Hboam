package com.hboam.am.statistics.valve;

import java.util.Date;
import java.util.List;

import com.hboam.am.core.Constants;
import com.hboam.am.core.pipeline.BaseValve;
import com.hboam.am.core.pipeline.Request;
import com.hboam.am.core.pipeline.Response;
import com.hboam.am.statistics.core.Game;
import com.hboam.am.statistics.core.Subgame;
import com.hboam.am.statistics.db.source.ResourceDAO;
import com.hboam.am.statistics.db.source.ResourceDAOImpl;

public class InitGameValve extends BaseValve {
	
	ResourceDAO resourceDAO  = new ResourceDAOImpl();

	@Override
	protected void doHandle(Request req, Response resp) {
		System.out.println("bbbbb"+" "+ new Date());
//		List<Game> gameList = resourceDAO.getGames();
//		// 初始化线程中关于game,subgame的对象群
//		for( Game g : gameList ){
//			List<Subgame> sgList = resourceDAO.getSubgamesByGame(g.getGameId());
//			for ( Subgame sg : sgList ){
//				g.getSubgameMap().put(sg.getSubgameId(), sg);
//			}
//		}
//		
//		req.setParameter(com.hboam.am.statistics.core.Constants.KEY_GAMES, gameList);
	}

}
