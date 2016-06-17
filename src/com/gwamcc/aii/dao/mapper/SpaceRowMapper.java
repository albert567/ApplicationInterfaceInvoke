package com.gwamcc.aii.dao.mapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.gwamcc.aii.dao.impl.SpaceNodeDaoImpl;
import com.gwamcc.aii.forms.SpaceNodeDataForm;
import com.gwamcc.aii.forms.SpaceNodeForm;

public class SpaceRowMapper<T> implements RowMapper<T>{
	
	private String paramID;
	private String paramName;
	private String tableName;
	private SpaceNodeDaoImpl spaceNodeDao;
	
	public SpaceRowMapper(String paramID,String paramName,String tableName,SpaceNodeDaoImpl spaceNodeDao){
		this.paramID = paramID;
		this.paramName = paramName;
		this.tableName = tableName;
		this.spaceNodeDao = spaceNodeDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T mapRow(ResultSet rs, int row) throws SQLException {
		Method method = null;
		SpaceNodeForm dform = null;
		
		int level = rs.getInt("level");
		try {
			if(level>0){
				method = SpaceNodeDaoImpl.class.getMethod("level"+rs.getInt("Level"), 
						int.class,String.class);
				dform = (SpaceNodeForm)method.invoke(this.spaceNodeDao, 
						rs.getInt(paramID),rs.getString(paramName));
			}else{
				dform = new SpaceNodeForm();
				dform.setId(tableName+(++SpaceNodeDaoImpl.uniqueID))
				.setName(rs.getString(paramName))
				.setData(new SpaceNodeDataForm().setNodeID(rs.getInt(paramID)).setType(tableName))
				.setChildren(new ArrayList<SpaceNodeForm>());
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (T) dform;
	}
}
