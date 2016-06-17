package com.gwamcc.aii.util.sql2;

import java.util.Date;
import java.util.LinkedList;

public class Result extends LinkedList<RowMap>{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int _index=-1;

	public boolean next(){
		return ++_index<size();
	}
	public RowMap get(){
		if(_index==-1){
			next();
		}
		if(_index>=0&&_index<size()){
			return get(_index);
		}
		return null;
	}

	public Object getObject(String name){
		return get()==null?null:get().get(name);
	}
	public String getString(String name){
		return get()==null?null:(get().getString(name));
	}
	public int getInt(String name){
		return get()==null?null:(get().getInt(name));
	}
	public long getLong(String name){
		return get()==null?null:(get().getLong(name));
	}
	public double getDouble(String name){
		return get()==null?null:(get().getDouble(name));
	}
	public boolean getBoolean(String name){
		return get()==null?false:(get().getBoolean(name));
	}
	public Date getDate(String name){
		return get()==null?null:(get().getDate(name));
	}
}
