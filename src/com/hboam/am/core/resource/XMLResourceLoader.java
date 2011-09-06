package com.hboam.am.core.resource;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hboam.am.connector.Connector;
import com.hboam.am.connector.ConnectorAware;
import com.hboam.am.core.Component;
import com.hboam.am.core.Constants;
import com.hboam.am.core.Executor;
import com.hboam.am.core.ExecutorConfig;
import com.hboam.am.core.ExecutorContext;
import com.hboam.am.core.Lifecycle;
import com.hboam.am.core.StandardExecutorContext;
import com.hboam.am.core.db.DBResourceKey;
import com.hboam.am.core.pipeline.Pipeline;
import com.hboam.am.core.pipeline.PipelineFactory;
import com.hboam.am.core.pipeline.Valve;
import com.hboam.am.util.ReflectionUtil;
import com.hboam.am.util.XmlUtil;

public class XMLResourceLoader implements Lifecycle {

	private String COMPONENTS_RESOURCE_PATH = "conf/hboam-components.xml";
	Logger logger = LoggerFactory.getLogger("core");
	
	List<Component> componentList = new ArrayList<Component>();
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		// Load the xml file
		loadComponents();
		
		for( Component  c: componentList ){
			if(c instanceof Lifecycle)
				((Lifecycle)c).init();
		}
		
	}


	private void loadComponents(){
		
		try {
			XmlUtil xmlUtil = new XmlUtil(COMPONENTS_RESOURCE_PATH);
			List<Element> eList = xmlUtil.getElements("/components/component");
			for ( Element e : eList ){
				String componentName = e.attributeValue("name");
				String clazz = e.attributeValue("class");
				
				if(clazz == null)
					clazz = Constants.DEFAULT_COMPONENT_CLASS;
				Object o = ReflectionUtil.reflect(clazz);
				
				if( o instanceof Component )
					componentList.add((Component)o);
				Component c = (Component)o;
				c.setComponentName(componentName);
				
				List<Element> execList = e.elements("executor");
				for( Element ex : execList ){
					String executorName = ex.attributeValue("name");
					String id = ex.attributeValue("id");
					String res = ex.attributeValue("res");
					// scriptType scriptFilePath
					String cron = ex.attributeValue("cron");
					String executorConfigClazz = ex.attributeValue("config-class");
					if( executorConfigClazz == null )
						executorConfigClazz = Constants.DEFAULT_EXECUTOR_CONFIG_CLASS_NAME;
					String executorClass = ex.attributeValue("class");
					if( executorClass == null )
						executorClass = Constants.DEFAULT_EXECUTOR_CLASS;
					
					// load the pipeline
					Element pE  = ex.element("pipeline");
					
					Pipeline pipeline = null;
					if( pE != null ){
						 pipeline = loadPipeline(pE);
					}
					// load all properties
					Map<String, String> propsMap = new HashMap<String, String>();
					Element propsE = ex.element("properties");
					if(propsE !=null){
						List<Element> propsElementList = propsE.elements("property");
						for(Element p : propsElementList){
							propsMap.put(p.attributeValue("name"), p.getText());
						}
					}
					Object eo = ReflectionUtil.reflect(executorConfigClazz);
					Class<?> executorclazz = ReflectionUtil.getClass(executorClass);
					if ( eo instanceof ExecutorConfig)
						c.addExecutorConfig((ExecutorConfig)eo);
					
					ExecutorConfig cfg = (ExecutorConfig)eo;
					cfg.setPipeline(pipeline);
					cfg.setPropsMap(propsMap);
					cfg.setComponent(c);
					cfg.setExecutorName(executorName);
					cfg.setCron(cron);
					cfg.setId(id);
					cfg.setResPath(res);
					cfg.setExecutorClazz(executorclazz);
				}
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private Pipeline loadPipeline(Element pE) {
		
		Pipeline p = PipelineFactory.getPipeline();
		
		List<Element> vList = pE.elements("valve");
		
		for( Element e : vList ){
			String vClazz = e.getText();
			
			
			
			
			Valve v = (Valve)ReflectionUtil.reflect(vClazz);
			String connectorName = e.attributeValue("connector");
			if( connectorName != null ){
				String connectorClazz = Constants.connectorMapping.get(connectorName);
				Connector c = (Connector)ReflectionUtil.reflect(connectorClazz);
				if( v instanceof ConnectorAware )
					((ConnectorAware)v).setConnector(c);
			}
			
			p.addValve(v);
		}
		return p;
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
