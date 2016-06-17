package com.gwamcc.aii.dao.core;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.gwamcc.aii.util.sql2.SQLUtils;

/**
 * 简单Excel行导航
 * @author 范大德
 *
 * @param <T>
 */
public class DefaultRowMapper<T> implements RowMapper<T> {

	private Class<T> cs;
	public DefaultRowMapper(Class<T> cs){
		this.cs=cs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T mapRow(ResultSet rs, int row) throws SQLException {
		int count= rs.getMetaData().getColumnCount();
		Map<String, Object>map=new HashMap<String,Object>();
		for(int i=1;i<=count;i++){
			map.put(rs.getMetaData().getColumnLabel(i).toUpperCase(), rs.getObject(i));
		}
		try {
			return (T) SQLUtils.coverMapToBean(map, cs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
