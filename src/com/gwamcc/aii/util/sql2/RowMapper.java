package com.gwamcc.aii.util.sql2;

import java.util.Map;

public abstract class RowMapper{
	private Map<String, Object> context;
	public RowMapper(Map<String, Object> context) {
		this.context=context;

	}
	public abstract RowMap mapper(RowMap map)throws Exception;
	public Object getContext(String key){
		return context.get(key);
	}
}
