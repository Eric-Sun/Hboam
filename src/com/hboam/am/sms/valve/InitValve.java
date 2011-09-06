package com.hboam.am.sms.valve;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.core.pipeline.Pipeline;
import com.hboam.am.core.pipeline.Request;
import com.hboam.am.core.pipeline.Response;
import com.hboam.am.core.pipeline.Valve;
import com.hboam.am.util.XmlUtil;

public class InitValve implements Valve {
	private Logger logger = LoggerFactory.getLogger("sms");

	private final String RES = "sender.xml";
	@Override
	public void handle(Request req, Response resp) {
		
		String basePath = p.getExecutorContext().getExecutorConfig().getResouceBasePath();
		List<String> phoneList = new ArrayList<String>();
		XmlUtil xml;
		try {
			xml = new XmlUtil(basePath+RES);
			List<Element> eList = xml.getElements("/sender/sms/phonenum");
			for( Element e : eList ){
				phoneList.add(e.getText());
			}
		} catch (Exception e) {
			logger.error("load xml error!",e);
		}
		
		req.setParameter("phoneList", phoneList);
		v.handle(req,resp);
	}

	@Override
	public void setNext(Valve v) {
		this.v = v;
	}
	Valve v = null;
	Pipeline p = null;
	@Override
	public void setPipeline(Pipeline p) {
		this.p  = p;
	}

}
