package com.hboam.am.core;


/**
 * ��һ������ĸ�����ڵ�����ҵ�ĸ����ϣ���������ĸ���ֽ׶�û�о���ĺ���
 * ���а���һ��ExecutorConfig��Ҳ���������Ӽ���
 * <p>
 * ������ʱ��ͨ������init������Ȼ��init��������������Ӽ��ĳ�ʼ���������ʽ���������Ӷ��������еĶ���
 * Ȼ��ϵͳͨ��shceduler���е���
 * @author Eric
 *
 */
public interface Component extends Lifecycle	{
	
	/**
	 * �����������֣����û�з���null
	 * @return
	 */
	public String getComponentName();
	
	/**
	 * �������������
	 * @param componentName
	 * @exception �������null������illegalArgument���쳣
	 */
	public void setComponentName(String componentName);
	
	/**
	 * �ڶ����в���һ��ExecutorConfig
	 * @param e
	 */
	public void addExecutorConfig(ExecutorConfig e);
	
}
