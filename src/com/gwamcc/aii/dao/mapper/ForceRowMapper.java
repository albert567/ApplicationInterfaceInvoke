package com.gwamcc.aii.dao.mapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.gwamcc.aii.dao.impl.ForceNodeDaoImpl;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.ForceAdjDataForm;
import com.gwamcc.aii.forms.ForceAdjForm;
import com.gwamcc.aii.forms.ForceNodeForm;
import com.gwamcc.aii.forms.ForceRowArgForm;

public class ForceRowMapper<T> implements RowMapper<T>{
	
	private int nodeID;
	private String nodeFromID;
	private String paramID;
	private String paramName;
	private String tableName;
	private ForceAdjDataForm adjData;
	private List<DefaultForm> formList;
	private ForceNodeDaoImpl forceNodeDao;
	private JdbcTemplate jdbcTemplate;
	
	public ForceRowMapper(ForceRowArgForm argForm){
		this.nodeID = argForm.getNodeID();
		this.nodeFromID = argForm.getNodeFromID();
		this.paramID = argForm.getParamID();
		this.paramName = argForm.getParamName();
		this.tableName = argForm.getTableName();
		this.adjData = argForm.getAdjData();
		this.formList = argForm.getFormList();
		this.forceNodeDao = argForm.getForceNodeDao();
		this.jdbcTemplate = argForm.getJdbcTemplate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T mapRow(ResultSet rs, int row) throws SQLException {
		ForceAdjForm adjForm = new ForceAdjForm();
		
		adjForm.setNodeFID(nodeID)
				.setNodeFrom(nodeFromID)//设置为table+ID
				.setNodeTID(rs.getInt(paramID))
				.setNodeTo(tableName+rs.getInt(paramID));//设置为table+ID
		adjForm.setData(adjData);
		
		
		Method method = null;
		int level = rs.getInt("level");
		try {
			if(level>0){
				method = ForceNodeDaoImpl.class.getMethod("level"+rs.getInt("Level"), int.class,String.class,List.class,JdbcTemplate.class);
				ForceNodeForm dform = (ForceNodeForm)method.invoke(this.forceNodeDao, 
						rs.getInt(paramID),rs.getString(paramName),formList,jdbcTemplate);
				this.formList.add(dform);
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
		
		return (T) adjForm;
	}
}
