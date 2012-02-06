package com.hboam.am.statistics.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.hboam.am.statistics.core.Channel;
import com.hboam.am.statistics.core.Dimension;
import com.hboam.am.statistics.core.Game;
import com.hboam.am.statistics.core.Period;
import com.hboam.am.statistics.core.Subgame;

public class DimensionUtil {
	
	private static SimpleDateFormat yyyyMMdd00 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
	private static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
	public static Iterator<Dimension> iterator(List<Game> gameList ,
			String period,List<Channel> channelList){
		
		Date needSplitBeginDate = null;
		Date needSplitEndDate = null;
		EndDateFetchType type ;
		
		List<Dimension> dList = new ArrayList<Dimension>();
		// 处理每一个subgame的需要处理的时间间隔
		Date endDate = DateUtil.getEndDate();
	
		for ( Game game : gameList ){
			Iterator<Subgame> subgameIter = game.getSubgameMap().values().iterator();
			
			while(subgameIter.hasNext()){
				Subgame subgame = subgameIter.next();
				
				needSplitBeginDate = subgame.getSubgameBeginDate();
				Date subgameEndDate = subgame.getSubgameEndDate();
				// 找到周期内的第一天
				needSplitBeginDate = DateUtil.getlastBeginDate(Period.valueOf(period), needSplitBeginDate);
				// 转换所有的结束时间，方便比对
				
				Date afterCvtSubgameEndDate = subgameEndDate;
				Date afterCvtEndDate = endDate;
				if( subgameEndDate == null ){
					needSplitEndDate = afterCvtEndDate;
					type = EndDateFetchType.COMMON_LAST_DATE;
				}else if( afterCvtSubgameEndDate.getTime() >= afterCvtEndDate.getTime() ){
					// 说明分区结束时间比前一天大，所以最后结束时间为昨天
					needSplitEndDate = afterCvtEndDate;
					type = EndDateFetchType.COMMON_LAST_DATE;
				}else{
					// 结束时间为分区的结束时间
					needSplitEndDate = afterCvtSubgameEndDate;
					type = EndDateFetchType.SUBGAME_END_DATE;
				}
				
				List<SplitDate> sdList = doSplit(needSplitBeginDate,needSplitEndDate,type,Period.valueOf(period));
				for( SplitDate sd : sdList ){
					for( Channel channel : channelList ){
						Dimension d = new Dimension();
						d.setBeginDate(sd.beginDate);
						d.setEndDate(sd.endDate);
						d.setChannel(channel);
						d.setGame(game);
						d.setSubgame(subgame);
						d.setPeriod(period);
						dList.add(d);
					}
				}
			}
		}
	
		return dList.iterator();
	}
	
	/**
	 * 传入的时候开始时间为00:00:00 结束时间为 23:59:59
	 * @param needSplitBeginDate
	 * @param needSplitEndDate
	 * @param type
	 * @param period
	 * @return
	 */
	public static List<SplitDate> doSplit(Date needSplitBeginDate,
			Date needSplitEndDate, EndDateFetchType type,Period period) {
		List<SplitDate> sdList = new ArrayList<DimensionUtil.SplitDate>();
		Calendar needSplitBeginCal = Calendar.getInstance();
		needSplitBeginCal.setTime(needSplitBeginDate);
 		Calendar needSplitEndCal = Calendar.getInstance();
		needSplitEndCal.setTime(needSplitEndDate);
		
		Calendar beginTmp = Calendar.getInstance();
		
		switch (type) {
		case COMMON_LAST_DATE:
			// 结束后时间为当前天的前一天的时候，周和月的判断要放弃后面的天数，
			// 也就是只处理当前日期前的整月和整周
			switch (period) {
			case DAY:
				int bd = needSplitBeginCal.get(Calendar.DAY_OF_YEAR);
				int ed = needSplitEndCal.get(Calendar.DAY_OF_YEAR);
				beginTmp.setTime(needSplitBeginCal.getTime()); 
				for ( int i=0; i<ed-bd+1; i++ ){
					SplitDate sd = new DimensionUtil.SplitDate();
					beginTmp.add(Calendar.DAY_OF_YEAR, i);
					sd.beginDate = beginTmp.getTime();
					sd.endDate = new Date(beginTmp.getTime().getTime()+(long)(24*60*60*1000-1000));
					sdList.add(sd);
					beginTmp.setTime(needSplitBeginCal.getTime()); 
				}
				return sdList;
			case WEEK:
				int bw = needSplitBeginCal.get(Calendar.WEEK_OF_YEAR);
				int ew = needSplitEndCal.get(Calendar.WEEK_OF_YEAR);
				beginTmp.setTime(needSplitBeginCal.getTime()); 
				for ( int i=0; i<ew-bw; i++ ){
					SplitDate sd = new DimensionUtil.SplitDate();
					beginTmp.add(Calendar.WEEK_OF_YEAR, i);
					sd.beginDate = beginTmp.getTime();
					sd.endDate = new Date(beginTmp.getTime().getTime()+(long)(7*24*60*60*1000-1000));
					sdList.add(sd);
					beginTmp.setTime(needSplitBeginCal.getTime()); 
				}
				return sdList;
			case MONTH:
				int bm = needSplitBeginCal.get(Calendar.MONTH);
				int em = needSplitEndCal.get(Calendar.MONTH);
				beginTmp.setTime(needSplitBeginCal.getTime()); 
				for ( int i=0; i<em-bm; i++ ){
					SplitDate sd = new DimensionUtil.SplitDate();
					beginTmp.add(Calendar.MONTH, i);
					sd.beginDate = beginTmp.getTime();
					beginTmp.add(Calendar.MONTH, i+1);
					sd.endDate = new Date(beginTmp.getTime().getTime()-(long)(1000));
					if(sd.endDate.getTime()>needSplitEndCal.getTime().getTime() && i==em-bm )
						sd.endDate=needSplitEndCal.getTime();
					sdList.add(sd);
					beginTmp.setTime(needSplitBeginCal.getTime()); 
				}
				return sdList;
			}
			return sdList;
		case SUBGAME_END_DATE:
			// 结束时间为分区的结束的时间的时候，周和月的最后的天数为结束天数
			// 也就是处理到分区的结束日期
			switch (period) {
			case DAY:
				int bd = needSplitBeginCal.get(Calendar.DAY_OF_YEAR);
				int ed = needSplitEndCal.get(Calendar.DAY_OF_YEAR);
				beginTmp.setTime(needSplitBeginCal.getTime()); 
				for ( int i=0; i<ed-bd+1; i++ ){
					SplitDate sd = new DimensionUtil.SplitDate();
					beginTmp.add(Calendar.DAY_OF_YEAR, i);
					sd.beginDate = beginTmp.getTime();
					sd.endDate = new Date(beginTmp.getTime().getTime()+(long)(24*60*60*1000-1000));
					sdList.add(sd);
					beginTmp.setTime(needSplitBeginCal.getTime()); 
				}
				return sdList;
			case WEEK:
				int bw = needSplitBeginCal.get(Calendar.WEEK_OF_YEAR);
				int ew = needSplitEndCal.get(Calendar.WEEK_OF_YEAR);
				beginTmp.setTime(needSplitBeginCal.getTime()); 
				for ( int i=0; i<ew-bw+1; i++ ){
					SplitDate sd = new DimensionUtil.SplitDate();
					beginTmp.add(Calendar.WEEK_OF_YEAR, i);
					sd.beginDate = beginTmp.getTime();
					sd.endDate = new Date(beginTmp.getTime().getTime()+(long)(7*24*60*60*1000-1000));
					if(sd.endDate.getTime()>needSplitEndCal.getTime().getTime() && i==ew-bw )
						sd.endDate=needSplitEndCal.getTime();
					sdList.add(sd);
					beginTmp.setTime(needSplitBeginCal.getTime()); 
				}
				return sdList;
			case MONTH:
				int bm = needSplitBeginCal.get(Calendar.MONTH);
				int em = needSplitEndCal.get(Calendar.MONTH);
				beginTmp.setTime(needSplitBeginCal.getTime()); 
				for ( int i=0; i<em-bm+1; i++ ){
					SplitDate sd = new DimensionUtil.SplitDate();
					beginTmp.add(Calendar.MONTH, i);
					sd.beginDate = beginTmp.getTime();
					beginTmp.add(Calendar.MONTH, i+1);
					sd.endDate = new Date(beginTmp.getTime().getTime()-(long)(1000));
					if(sd.endDate.getTime()>needSplitEndCal.getTime().getTime() && i==em-bm )
						sd.endDate=needSplitEndCal.getTime();
					sdList.add(sd);
					beginTmp.setTime(needSplitBeginCal.getTime()); 
				}
				return sdList;
			}
			return sdList;
		default:
			return sdList;
		}
	}



	static class SplitDate{
		Date beginDate;
		Date endDate;
	}
	
	enum EndDateFetchType{
		// 当前的日期前一天的获得方式
		COMMON_LAST_DATE,
		// 分区的结束日期
		SUBGAME_END_DATE
	}
}
