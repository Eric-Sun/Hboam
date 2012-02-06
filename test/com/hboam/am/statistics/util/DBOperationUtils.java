package com.hboam.am.statistics.util;

public class DBOperationUtils {
	
	public static String getSubgame(String[] subgameArray,String prefix){
		if ( subgameArray[0]==null)
			return " 1=1 ";
		if(subgameArray[0].toLowerCase().indexOf("allsubgame")>0)
			return " 1=1 ";
		
		StringBuffer sb = new StringBuffer();
		if(prefix!=null)
			sb.append(" "+prefix+".subgame in (");
		else
			sb.append(" subgame in (");
		for( String s : subgameArray ){
			sb.append("'"+s+"',");
		}
		sb.delete(sb.length()-1,sb.length());
		sb.append(") ");
		return sb.toString();
	}
	
	public static String getChan(String[] chanArray,String prefix){
		if ( chanArray[0]==null)
			return " 1=1 ";
		if(chanArray[0].toUpperCase().equals("ALLCHANNEL"))
			return " 1=1 ";
		StringBuffer sb = new StringBuffer();
		if(prefix!=null)
			sb.append(" "+prefix+".chanId in (");
		else
			sb.append(" chanId in (");
		for( String s : chanArray ){
			sb.append("'"+s+"',");
		}
		sb.delete(sb.length()-1,sb.length());
		sb.append(") ");
		return sb.toString();
	}
	
	public static String getChannel(String[] chanArray,String prefix){
		if(chanArray[0]==null)
			return " 1=1 ";
		if(chanArray[0].toUpperCase().equals("ALLCHANNEL"))
			return " 1=1 ";
		StringBuffer sb = new StringBuffer();
		if(prefix!=null)
			sb.append(" "+prefix+".channel in (");
		else
			sb.append(" channel in (");
		for( String s : chanArray ){
			sb.append("'"+s+"',");
		}
		sb.delete(sb.length()-1,sb.length());
		sb.append(") ");
		return sb.toString();
	}
	
	
	public static String getGame(String[] gameArray){
		return getGame(gameArray,null);
	}
	public static String getGame(String[] gameArray,String prefix){
		if(gameArray[0]==null)
			return " 1=1 ";
		StringBuffer sb = new StringBuffer();
		if(prefix!=null)
			sb.append(" "+prefix+".game in (");
		else
			sb.append(" game in (");
		for( String s : gameArray ){
			sb.append("'"+s+"',");
		}
		sb.delete(sb.length()-1,sb.length());
		sb.append(") ");
		return sb.toString();
	}

}
