package com.hboam.am.sms.valve;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.connector.Connector;
import com.hboam.am.connector.ConnectorAware;
import com.hboam.am.connector.ConnectorParameters;
import com.hboam.am.connector.ConnectorRuntimeException;
import com.hboam.am.core.db.DBResource;
import com.hboam.am.core.pipeline.Pipeline;
import com.hboam.am.core.pipeline.Response;
import com.hboam.am.core.pipeline.Request;
import com.hboam.am.core.pipeline.Valve;
import com.hboam.am.sms.db.SMSDBHandler;

public class FetchDataValve implements Valve,ConnectorAware {

	Logger logger = LoggerFactory.getLogger("sms");
	public void setConnector(Connector c) {
		this.c = c;
	}
	private Pipeline p ;
	private Connector c;
	public void setPipeline(Pipeline p) {
		this.p = p;
		
	}

	public void handle(Request req , Response resp) {
		
		List<String> phoneList = (List<String>)req.getParameter("phoneList");
		SMSDBHandler db = new SMSDBHandler();
		String chargedPassportNum = db.getChargedPassportNum();
		String chargeSum = db.getChargeSum();
		String newPassportNum = db.getNewPassportNum();
		
		String avgOnlineNum35 = db.getAvgOnlineNum(DBResource.GAME35);
		String avgOnlineNum36 = db.getAvgOnlineNum(DBResource.GAME36);
		
		String avgOnline = new Integer(avgOnlineNum35)+new Integer(avgOnlineNum36)+"";
		
		String message = "总充值数："+chargeSum+" 充值账户数："+chargedPassportNum
		+" 新注册账户数："+newPassportNum+" 平均在线人数："+avgOnline+"";
		
		for(String p : phoneList){
			send(p, message);
		}
		
		
	}
	
	private void send(String phone,String message){
		StringBuffer url = new StringBuffer("http://sdkhttp.eucp.b2m.cn/sdkproxy/sendsms.action?cdkey=3SDK-VXL-0130-LJVNR&password=693781");
        url.append("&phone=").append(phone);
        url.append("&message=").append(message);
        url.append("&addserial=");
        
        ConnectorParameters p = new ConnectorParameters();
        p.setUrl(url.toString());
        try {
			c.call(p);
		} catch (ConnectorRuntimeException e) {
			logger.error("error",e);
		}
	}

	public void setNext(Valve v) {
	}

}
