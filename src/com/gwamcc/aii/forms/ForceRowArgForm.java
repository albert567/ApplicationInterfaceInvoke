package com.gwamcc.aii.forms;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.gwamcc.aii.dao.impl.ForceNodeDaoImpl;
/**
 * ForceRowMapper构造参数对象
 * @author 张亚平
 *
 */
public class ForceRowArgForm {
	private int nodeID;
	private String nodeFromID;
	private String paramID;
	private String ParamName;
	private String tableName;
	private ForceAdjDataForm adjData;
	private List<DefaultForm> formList;//节点容器
	private ForceNodeDaoImpl forceNodeDao;//daoimpl this
	private JdbcTemplate jdbcTemplate;
	
	public ForceRowArgForm(){}
	public ForceRowArgForm(int nodeID, String nodeFromID, String nodeFromName, String paramID, String paramName,
			ForceAdjDataForm adjData, List<DefaultForm> formList, ForceNodeDaoImpl forceNodeDao,JdbcTemplate jdbcTemplate) {
		super();
		this.nodeID = nodeID;
		this.nodeFromID = nodeFromID;
		this.paramID = paramID;
		ParamName = paramName;
		this.adjData = adjData;
		this.formList = formList;
		this.forceNodeDao = forceNodeDao;
		this.jdbcTemplate = jdbcTemplate;
	}
	public int getNodeID() {
		return nodeID;
	}
	public ForceRowArgForm setNodeID(int nodeID) {
		this.nodeID = nodeID;
		return this;
	}
	public String getNodeFromID() {
		return nodeFromID;
	}
	public ForceRowArgForm setNodeFromID(String nodeFromID) {
		this.nodeFromID = nodeFromID;
		return this;
	}
	public String getParamID() {
		return paramID;
	}
	public ForceRowArgForm setParamID(String paramID) {
		this.paramID = paramID;
		return this;
	}
	public String getParamName() {
		return ParamName;
	}
	public ForceRowArgForm setParamName(String paramName) {
		ParamName = paramName;
		return this;
	}
	public ForceAdjDataForm getAdjData() {
		return adjData;
	}
	public ForceRowArgForm setAdjData(ForceAdjDataForm adjData) {
		this.adjData = adjData;
		return this;
	}
	public List<DefaultForm> getFormList() {
		return formList;
	}
	public ForceRowArgForm setFormList(List<DefaultForm> formList) {
		this.formList = formList;
		return this;
	}
	public ForceNodeDaoImpl getForceNodeDao() {
		return forceNodeDao;
	}
	public ForceRowArgForm setForceNodeDao(ForceNodeDaoImpl forceNodeDao) {
		this.forceNodeDao = forceNodeDao;
		return this;
	}
	public String getTableName() {
		return tableName;
	}
	public ForceRowArgForm setTableName(String tableName) {
		this.tableName = tableName;
		return this;
	}
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public ForceRowArgForm setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		return this;
	}
	
	
}
