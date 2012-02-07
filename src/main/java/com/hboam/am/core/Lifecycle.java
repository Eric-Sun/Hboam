package com.hboam.am.core;
/**
 * the lifecycle class control all of the object's lifecycle in the system.
 * You can initialize and destroy. all the object in the chain.If you init the first(head )Object in the system.
 * all the Object will be initialized sequentially.
 * @author Eric
 *
 */
public interface Lifecycle {

	public int NOT_INITIALIZE= 0;
	public int INITIALIZING=1;
	public int INITIALIZED=2;
	
	public void init();
	
	public void destroy();	
	
}
