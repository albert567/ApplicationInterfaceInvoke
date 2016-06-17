package com.gwamcc.aii.forms;

import java.util.HashMap;
import java.util.Map;

import com.gwamcc.aii.util.StrKit;
import com.gwamcc.aii.util.sql2.SQLUtils;

public class UpdateInfo {
	private DefaultForm info;
	private Map<String,Object>change;


	public DefaultForm getInfo() {
		return info;
	}
	public void setInfo(DefaultForm info) {
		this.info = info;
	}
	public Map<String, Object> getChange() {
		return change;
	}
	public void setChange(Map<String, Object> change) {
		this.change = change;
	}

	public static UpdateInfo newUpdate(DefaultForm before,DefaultForm now) throws Exception {
		UpdateInfo info=new UpdateInfo();
		info.setInfo(before);
		Map<String,Object>map=new HashMap<String, Object>();
		Map<String,Object>beforeMap=SQLUtils.covertBeanToMap(before);
		Map<String,Object>nowMap=SQLUtils.covertBeanToMap(now);
		for(String key:nowMap.keySet()){
			Object val=nowMap.get(key);
			if(val!=null&&beforeMap.containsKey(key)&&!val.equals(beforeMap.get(key))){
				map.put(key, nowMap.get(key));
			}
		}
		info.setChange(map);
		return info;
	}

	@Override
	public String toString() {
		return StrKit.toString(this);
	}
}
