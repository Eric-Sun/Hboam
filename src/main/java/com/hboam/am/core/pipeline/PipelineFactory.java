package com.hboam.am.core.pipeline;

public class PipelineFactory {
	
	private PipelineFactory(){}
	
	public static Pipeline getPipeline(){
		return new StandardPipeline();
	}

}
