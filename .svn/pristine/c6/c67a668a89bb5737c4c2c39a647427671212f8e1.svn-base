package com.gwamcc.aii.util.sql2;

import java.util.HashMap;
import java.util.Map;

/**
 * 参数表
 * @author 范大德
 *
 */
public class ParamMap {
	Map<String, Object>map=new HashMap<String, Object>();
	public ParamMap(){}
	/**
	 * 添加一个参数
	 * @param key	参数名
	 * @param value	参数值
	 * @return
	 */
	public ParamMap put(String key,Object value){
		map.put(key,value);
		if(value.getClass().isArray()){
    		Object[]vals=(Object[])value;
    		for(int i=0;i<vals.length;i++){
    			map.put(key+i,vals[i]==null?null:vals[i] instanceof LikeParam?vals[i].toString():vals[i]);
    		}
    	}
		return this;
	}
	public static ParamMap map(String key,Object value){
		ParamMap map=new ParamMap();
		map.put(key,value);
		return map;
	}
	/**
	 * 获取参数表
	 * @return
	 */
	public Map<String, Object>get(){
		return map;
	}
}
