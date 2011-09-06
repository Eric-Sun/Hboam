package com.hboam.am.core.pipeline;

public class PipelineFactory {
	
	private PipelineFactory(){}
	
	private static Pipeline p = new StandardPipeline();
	public static Pipeline getPipeline(){
		if( p == null )
			p = new StandardPipeline();
		return p ;
	}

}
