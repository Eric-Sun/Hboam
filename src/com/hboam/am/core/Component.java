package com.hboam.am.core;


/**
 * 是一个抽象的概念，用于调度作业的父集合，类似于组的概念，现阶段没有具体的含义
 * 其中包含一组ExecutorConfig，也就是他的子集合
 * <p>
 * 启动的时候通过调用init方法，然后init方法会调用所有子集的初始化，完成链式的启动，从而启动所有的对象，
 * 然后系统通过shceduler进行调用
 * @author Eric
 *
 */
public interface Component extends Lifecycle	{
	
	/**
	 * 获得组件的名字，如果没有返回null
	 * @return
	 */
	public String getComponentName();
	
	/**
	 * 设置组件的名称
	 * @param componentName
	 * @exception 如果传入null，返回illegalArgument的异常
	 */
	public void setComponentName(String componentName);
	
	/**
	 * 在队列中插入一个ExecutorConfig
	 * @param e
	 */
	public void addExecutorConfig(ExecutorConfig e);
	
}
