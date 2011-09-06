package com.hboam.am.core;

import java.util.Map;

import com.hboam.am.core.pipeline.Pipeline;

/**
 * 每一个Executor的配置文件，通过这个配置文件进行初始化等操作，其中包含了Executor的基本信息，和一个properities的Map
 * 可以通过xml的properties节点，进行配置，传入自己的特殊化参数
 * @author Eric
 *
 */
public interface ExecutorConfig {
	/**
	 * 设置executor的名称
	 * @param executorName
	 */
	public void setExecutorName(String executorName);
	
	/**
	 * return the exeuctor name if any
	 * @return
	 */
	public String getExecutorName();
	
	/**
	 * return the cron if any
	 * @return
	 */
	public String getCron();
	
	/**
	 * set the cron. If cron is null ,Will throw IllegalArgumentException.
	 * @param cron
	 */
	public void setCron(String cron);
	
	/**
	 * set the component to the target Excutor. So that the relationship between the exeuctor and Component will be created now
	 * . the developer can get the component information directly.
	 * @param component
	 */
	public void setComponent(Component component);
	
	/**
	 * return the specified component object
	 * @return
	 */
	public Component getComponent();
	
	/**
	 * set the unique id in this System
	 */
	public void setId(String id);
	
	/**
	 * get the id
	 * @return
	 */
	public String getId();
	
	/**
	 * set the executor class. the class will be reflect by the scheduler System. And create the target job to execute.
	 * @param clazz
	 */
	public void setExecutorClazz(Class clazz);
	
	/**
	 * get the executor class name
	 * @return
	 */
	public Class getExecutorClazz();
	
	/**
	 * 
	 */
	public void setPropsMap(Map<String, String> propsMap);
	
	/**
	 * 
	 * @return
	 */
	public Map<String, String> getPropsMap();
	
	public void setPipeline(Pipeline p);
	
	public Pipeline getPipeline();
	
	public String getResouceBasePath();
	
	public void setResPath(String resPath);
	
	public String getResPath();
	

}
