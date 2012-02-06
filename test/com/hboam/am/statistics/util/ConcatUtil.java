package com.hboam.am.statistics.util;

import java.util.ArrayList;
import java.util.List;

public class ConcatUtil {
	
	public static List<Object[]> append(List<Object[]> l1 ,List<Object[]> l2){
		l1.addAll(l2);
		return l1;
	}
	
	public static List<Object[]> add(List<Object[]> l1 , List<Object[]> l2 ){
		return add(l1, l2,null);
	}
	
	/**
	 * exclude column  1,2,3
	 * ºöÂÔ1,2,3ÁÐ
	 * @param l1
	 * @param l2
	 * @param excludeColumn
	 * @return
	 */
	public static List<Object[]> add(List<Object[]> l1 , List<Object[]> l2 ,String excludeColumn){
		
		List<Integer> excludeList = new ArrayList<Integer>();
		if(excludeColumn!=null){
			String[] arr = excludeColumn.split(",");
			for( String s : arr ){
				excludeList.add(new Integer(s));
			}
		}
		List<Object[]> l3 = new ArrayList<Object[]>();
		for( int i=0;i<l2.size();i++ ){
			Object[] t = new Object[l2.get(i).length];
			for( int j=0;j<l2.get(i).length;j++){
				if(l2.get(i)[j]==null)
					continue;
				if( excludeList.contains(j)) 
					continue;
				if( l1.size()==0 || l1.get(i)==null || l1.get(i)[j]==null){
					if( l2.get(i)[j].toString().indexOf(".")>0)
						t[j] = new Float(l2.get(i)[j].toString().equals("")?"0":l2.get(i)[j].toString())+"";
					else
						t[j] = new Integer(l2.get(i)[j].toString().equals("")?"0":l2.get(i)[j].toString())+"";
				}else{
					if( l1.get(i)[j].toString().indexOf(".")>0)
						t[j] = new Float(l1.get(i)[j].toString().equals("")?"0":l1.get(i)[j].toString())
							+new Float(l2.get(i)[j].toString().equals("")?"0":l2.get(i)[j].toString())+"";
					else
						t[j] = new Integer(l1.get(i)[j].toString().equals("")?"0":l1.get(i)[j].toString())
							+new Integer(l2.get(i)[j].toString().equals("")?"0":l2.get(i)[j].toString())+"";
				}
			}
			l3.add(t);
		}
		return l3;
	}
}
