package com.gwamcc.aii.util.sql2;

import java.util.Date;
import java.util.Map;

import org.springframework.util.LinkedCaseInsensitiveMap;

public class RowMap extends LinkedCaseInsensitiveMap<Object>{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Object getObject(String name){
		return get(name);
	}
	public String getString(String name){
		return get(name)==null?null:get(name).toString();
	}
	public int getInt(String name){
		return get(name)==null?Integer.MIN_VALUE:Integer.parseInt(getString(name));
	}
	public long getLong(String name){
		return get(name)==null?Long.MIN_VALUE:Long.parseLong(getString(name));
	}
	public double getDouble(String name){
		return get(name)==null?Double.NaN:Double.parseDouble(getString(name));
	}
	public boolean getBoolean(String name){
		return get(name)==null?false:"|1|true|t|".indexOf("|"+getString(name).toLowerCase()+"|")>-1;
	}
	public Date getDate(String name){
		Object obj=get(name);
		if (obj instanceof Date) {
			Date date = (Date) obj;
			return date;
		}else if(obj instanceof java.sql.Date){
			java.sql.Date sdate=(java.sql.Date)obj;
			return new Date(sdate.getTime());
		}
		return null;
	}
	public static RowMap toRowMap(Map<String, Object>map){
		if(map==null){
			return null;
		}
		RowMap row= new RowMap();
		row.putAll(map);
		return row;
	}
}
