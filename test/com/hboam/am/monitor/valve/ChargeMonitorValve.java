package com.hboam.am.monitor.valve;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.hboam.am.core.pipeline.BaseValve;
import com.hboam.am.core.pipeline.Request;
import com.hboam.am.core.pipeline.Response;
import com.hboam.am.monitor.dao.ChargeDAO;
import com.hboam.am.monitor.dao.ChargeDAOImpl;
import com.hboam.am.monitor.util.MonitorUtils;
import com.hboam.am.monitor.util.SMSUtil;

public class ChargeMonitorValve extends MonitorBaseValve {
	
	ChargeDAO chargeDAO = new ChargeDAOImpl();

	@Override
	protected void doMonitorHandle(Request req, Response resp) {
		
		if( ! MonitorUtils.inChargeMonitorPeriod() ){
			logger.info(" is not in period . Date = "+new Date());
			return ;
		}
		
		// 获得开始和结束时间
		Date beginDate = MonitorUtils.getLastBeginHour();
		Date endDate = MonitorUtils.getLastEndHour();
		int count = 0;
		try {
			List<Object[]> list = chargeDAO.getChargedPassportCount(beginDate, endDate);
			count = new Integer(list.get(0)[0].toString());
		} catch (SQLException e) {
			logger.error("beginDate="+beginDate+" endDate="+endDate,e);
		}
		logger.info("charge monitor . count = "+count+" beginDate="+beginDate+" endDate="+endDate);
		if( count == 0 ){
			// error
			// send sms to sunb and dengl
			SMSUtil.send("15210334593"," Charge System may be encounter a SERIOUS problem yet! ");
//			SMSUtil.send("13701091894"," Charge System may be encounter a SERIOUS problem yet! ");
		}else{
			// no problem 
		}
	}

}
