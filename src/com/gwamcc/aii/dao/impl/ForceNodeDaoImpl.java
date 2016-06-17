package com.gwamcc.aii.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gwamcc.aii.dao.ForceNodeDao;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.dao.mapper.ForceRowMapper;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.ForceAdjDataForm;
import com.gwamcc.aii.forms.ForceAdjForm;
import com.gwamcc.aii.forms.ForceNodeDataForm;
import com.gwamcc.aii.forms.ForceNodeForm;
import com.gwamcc.aii.forms.ForceRowArgForm;

/**
 * 接口方法数据操作接口实现类
 * @author 张亚平
 *
 */
@Repository
public class ForceNodeDaoImpl extends DefaultDao implements ForceNodeDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static String color = "#23A4FF";//普通线条颜色
	public static String rootColor = "#83548B";
	public static String appColor = "#47B18C";
	public static String interColor = "#83548B";
	public static String methodColor = "#216D9C";
	public static String objColor = "#EBB056";
	public static String dbColor = "#BBFFBB";
	public static String rootType = "star";
	public static String appType = "circle";
	public static String interType = "star";
	public static String methodType = "triangle";
	public static String objType = "circle";
	public static String dbType = "square";
	
	
	@Override
	public List<DefaultForm> getForceData() {
		List<DefaultForm> formList = new ArrayList<DefaultForm>();
		
		ForceNodeForm appForm = getAppData(formList,jdbcTemplate);//系统平台
		formList.add(appForm);
		
		return formList;
	}
	/**
	 * 获取系统平台节点及子节点
	 * @param formList 节点容器
	 * @return 系统平台节点
	 */
	private ForceNodeForm getAppData(List<DefaultForm> formList,JdbcTemplate jdbcTemplate){
		int root = 0;
		
		String sql = "SELECT 1 as Level,ID,Name,AppType,Description FROM T_Application";
		ForceNodeForm nodeForm = new ForceNodeForm();
		nodeForm.setId("apps").setName("系统平台");
		//node-data
		ForceNodeDataForm nodeDataForm= new ForceNodeDataForm();
		nodeDataForm.set$alpha(1).set$color(rootColor).set$type(rootType);
		nodeForm.setData(nodeDataForm);
		//node-adjacencies-data
		ForceAdjDataForm adjDataForm = new ForceAdjDataForm();
		adjDataForm.set$alpha(1).set$color(color);
		
		//参数form
		ForceRowArgForm argForm = new ForceRowArgForm();
		argForm.setNodeID(root)
				.setNodeFromID("apps")
				.setParamID("ID")
				.setParamName("Name")
				.setAdjData(adjDataForm)
				.setForceNodeDao(this)
				.setFormList(formList)
				.setTableName("T_Application");
		
		List<ForceAdjForm> adjFormList = jdbcTemplate.query(sql,
				new ForceRowMapper<ForceAdjForm>(argForm));
		//node-adjacencies
		nodeForm.setAdjacencies(adjFormList);
		
		return nodeForm;
	}
	/**
	 * 获取应用系统节点及子节点
	 * @param appID 应用系统ID
	 * @param appName 应用系统名称
	 * @param formList 节点容器
	 * @return 应用系统节点
	 */
	public ForceNodeForm level1(int appID,String appName,List<DefaultForm> formList,JdbcTemplate jdbcTemplate){//应用系统
		String sql = "SELECT 2 as Level,ID,Name,Description"
				+ " FROM T_Interface"
				+ " where ApplicationID = ?";
		
		String nodeFromID = "T_Application"+appID;
		//node-data
		ForceNodeForm interForm = new ForceNodeForm();
		ForceNodeDataForm nodeData = new ForceNodeDataForm();
		nodeData.set$alpha(1).set$color(appColor).set$type(appType);
		interForm.setId(nodeFromID).setName(appName).setData(nodeData);
		//node-adjacencies-data
		ForceAdjDataForm adjDataForm = new ForceAdjDataForm();
		adjDataForm.set$alpha(1).set$color(color);
		
		ForceRowArgForm argForm = new ForceRowArgForm();
		argForm.setNodeID(appID)
				.setNodeFromID(nodeFromID)
				.setParamID("ID")
				.setParamName("Name")
				.setAdjData(adjDataForm)
				.setForceNodeDao(this)
				.setFormList(formList)
				.setTableName("T_Interface").setJdbcTemplate(jdbcTemplate);
				
		List<ForceAdjForm> adjFormList = jdbcTemplate.query(sql,new Object[]{appID},
				new ForceRowMapper<ForceAdjForm>(argForm));
		interForm.setAdjacencies(adjFormList);
		
		//调用方
				String sql2 = "SELECT 9 as Level,B.ID,B.Name"
						+ " FROM T_ApplicationInterfaceInvoke A"
						+ " LEFT JOIN T_InterfaceMethod B ON A.InterfaceMethodID = B.ID"
						+ " WHERE A.InvokeApplicationID = ? ";
				ForceRowArgForm argForm2 = new ForceRowArgForm();
				argForm2.setNodeID(appID)
						.setNodeFromID(nodeFromID)
						.setParamID("ID")
						.setParamName("Name")
						.setAdjData(adjDataForm.set$color("#FFFFFF"))
						.setForceNodeDao(this)
						.setFormList(formList)
						.setTableName("T_InterfaceMethod").setJdbcTemplate(jdbcTemplate);
				List<ForceAdjForm> adjFormList2 = jdbcTemplate.query(sql2,new Object[]{appID},
						new ForceRowMapper<ForceAdjForm>(argForm2));
				adjFormList.addAll(adjFormList2);
				
				interForm.setAdjacencies(adjFormList);
				
		return interForm;
	}
	
	/**
	 * 获取接口节点及其子节点
	 * @param interID 接口ID
	 * @param interName 接口名称
	 * @param formList 节点容器
	 * @return 接口节点
	 */
	public ForceNodeForm level2(int interID,String interName,List<DefaultForm> formList,JdbcTemplate jdbcTemplate){//接口
		String sql = "SELECT 3 as Level,A.ID as MethodID,A.Name+B.DValue as MethodName"
				+ " FROM T_InterfaceMethod A"
				+ " LEFT JOIN T_Dict B"
				+ " ON A.MethodTypeID = B.DKey"
				+ " WHERE A.InterfaceID = ?";
		
		String nodeFromID = "T_Interface"+interID;
		//node-data
		ForceNodeForm methodForm = new ForceNodeForm();
		ForceNodeDataForm nodeData = new ForceNodeDataForm();
		nodeData.set$alpha(1).set$color(interColor).set$type(interType);
		methodForm.setId(nodeFromID).setName(interName).setData(nodeData);
		//node-adjacencies-data
		ForceAdjDataForm adjDataForm = new ForceAdjDataForm();
		adjDataForm.set$alpha(1).set$color(color);
		
		ForceRowArgForm argForm = new ForceRowArgForm();
		argForm.setNodeID(interID)
				.setNodeFromID(nodeFromID)
				.setParamID("MethodID")
				.setParamName("MethodName")
				.setAdjData(adjDataForm)
				.setForceNodeDao(this)
				.setFormList(formList)
				.setTableName("T_InterfaceMethod").setJdbcTemplate(jdbcTemplate);
				
		List<ForceAdjForm> adjFormList = jdbcTemplate.query(sql,new Object[]{interID},
				new ForceRowMapper<ForceAdjForm>(argForm));
		methodForm.setAdjacencies(adjFormList);
		return methodForm;
	}
	/**
	 * 获取接口方法节点及其子节点
	 * @param methodID 方法ID
	 * @param methodName 方法名称
	 * @param formList 节点容器
	 * @return 接口方法节点
	 */
	public ForceNodeForm level3(int methodID,String methodName,List<DefaultForm> formList,JdbcTemplate jdbcTemplate){//接口方法
		
		String sql = "SELECT 4 as Level,A.DataBaseObjID as ObjID,A.DataBaseID as DbID,B.ObjName+C.DValue as ObjName"
					+ " FROM T_IM_DBOC_Rel A"
					+ " LEFT JOIN T_DataBaseObj B"
					+ " ON A.DataBaseObjID = B.ID"
					+ " LEFT JOIN T_Dict C"
					+ " ON B.ObjType = C.DKey"
					+ " WHERE A.DataBaseObjID!=-1 "
					+ " AND A.InterfaceMethodID = ?";
		
		String nodeFromID = "T_InterfaceMethod"+methodID;
		//node-data
		ForceNodeForm objForm = new ForceNodeForm();
		ForceNodeDataForm nodeData = new ForceNodeDataForm();
		//List<DefaultForm> params = getParamsByMeID(methodID);
		//nodeData.set$alpha(1).set$color(methodColor).set$type(methodType).setArgs(params);
		nodeData.set$alpha(1).set$color(methodColor).set$type(methodType);
		objForm.setId(nodeFromID).setName(methodName).setData(nodeData);
		
		//node-adjacencies-data
		ForceAdjDataForm adjDataForm = new ForceAdjDataForm();
		adjDataForm.set$alpha(1).set$color(color);
		
		ForceRowArgForm argForm = new ForceRowArgForm();
		argForm.setNodeID(methodID)
				.setNodeFromID(nodeFromID)
				.setParamID("ObjID")
				.setParamName("ObjName")
				.setAdjData(adjDataForm)
				.setForceNodeDao(this)
				.setFormList(formList)
				.setTableName("T_DataBaseObj").setJdbcTemplate(jdbcTemplate);
				
		List<ForceAdjForm> adjFormList = jdbcTemplate.query(sql,new Object[]{methodID},
				new ForceRowMapper<ForceAdjForm>(argForm));
		
		//调用方
		String sql2 = "SELECT 6 as Level,A.InvokeApplicationID as AppID,B.Name as AppName"
				+ " FROM T_ApplicationInterfaceInvoke A"
				+ " LEFT JOIN T_Application B"
				+ " ON A.InvokeApplicationID = B.ID"
				+ " WHERE A.InterfaceMethodID = ?";
		ForceRowArgForm argForm2 = new ForceRowArgForm();
		argForm2.setNodeID(methodID)
				.setNodeFromID(nodeFromID)
				.setParamID("AppID")
				.setParamName("AppName")
				.setAdjData(adjDataForm.set$color("#FFFFFF"))
				.setForceNodeDao(this)
				.setFormList(formList)
				.setTableName("T_Application").setJdbcTemplate(jdbcTemplate);
		List<ForceAdjForm> adjFormList2 = jdbcTemplate.query(sql2,new Object[]{methodID},
				new ForceRowMapper<ForceAdjForm>(argForm2));
		adjFormList.addAll(adjFormList2);
		
		objForm.setAdjacencies(adjFormList);
		return objForm;
	}
	/**
	 * 获取数据库对象节点及其子节点
	 * @param objID 对象ID
	 * @param objName 对象名称
	 * @param formList 节点容器
	 * @return 获取数据库对象节点
	 */
	public ForceNodeForm level4(int objID,String objName,List<DefaultForm> formList,JdbcTemplate jdbcTemplate){//数据库对象
			
			String sql = "SELECT 5 as Level,A.DataBaseID as DbID,B.Name+'数据库' as DbName "
						+ " FROM T_DataBaseObj A"
						+ " LEFT JOIN T_DataBase B"
						+ " ON A.DataBaseID = B.ID"
						+ " WHERE A.ID = ?";
			
			String nodeFromID = "T_DataBaseObj"+objID;
			//node-data
			ForceNodeForm objForm = new ForceNodeForm();
			ForceNodeDataForm nodeData = new ForceNodeDataForm();
			nodeData.set$alpha(1).set$color(objColor).set$type(objType);
			objForm.setId(nodeFromID).setName(objName).setData(nodeData);
			//node-adjacencies-data
			ForceAdjDataForm adjDataForm = new ForceAdjDataForm();
			adjDataForm.set$alpha(1).set$color(color);
			
			ForceRowArgForm argForm = new ForceRowArgForm();
			argForm.setNodeID(objID)
					.setNodeFromID(nodeFromID)
					.setParamID("DbID")
					.setParamName("DbName")
					.setAdjData(adjDataForm)
					.setForceNodeDao(this)
					.setFormList(formList)
					.setTableName("T_DataBase").setJdbcTemplate(jdbcTemplate);
					
			List<ForceAdjForm> adjFormList = jdbcTemplate.query(sql,new Object[]{objID},
					new ForceRowMapper<ForceAdjForm>(argForm));
			objForm.setAdjacencies(adjFormList);
			return objForm;
		}
	/**
	 * 获取数据库节点
	 * @param dbID 数据库ID
	 * @param dbName 数据库名称
	 * @param formList 节点容器
	 * @return 数据库节点
	 */
	public ForceNodeForm level5(int dbID,String dbName,List<DefaultForm> formList,JdbcTemplate jdbcTemplate){//数据库
		String nodeFromID = "T_DataBase"+dbID;
		//node-data
		ForceNodeForm objForm = new ForceNodeForm();
		ForceNodeDataForm nodeData = new ForceNodeDataForm();
		nodeData.set$alpha(1).set$color(dbColor).set$type(dbType);
		objForm.setId(nodeFromID).setName(dbName).setData(nodeData);
		objForm.setAdjacencies(new ArrayList<ForceAdjForm>());
		return objForm;
	}
	/**
	 * 获取应用系统节点
	 * @param appID 应用系统ID
	 * @param appName 应用系统名称
	 * @param formList 节点容器
	 * @return 应用节点
	 */
	public ForceNodeForm level6(int appID,String appName,List<DefaultForm> formList,JdbcTemplate jdbcTemplate){//应用系统
		String nodeFromID = "T_Application"+appID;
		//node-data
		ForceNodeForm objForm = new ForceNodeForm();
		ForceNodeDataForm nodeData = new ForceNodeDataForm();
		nodeData.set$alpha(1).set$color(appColor).set$type(appType);
		objForm.setId(nodeFromID).setName(appName).setData(nodeData);
		objForm.setAdjacencies(new ArrayList<ForceAdjForm>());
		return objForm;
	}
	
	/**
	 * 获取数据库对象节点
	 * @param objID 数据库对象ID
	 * @param objName 数据库对象名称
	 * @param formList 节点容器
	 * @return 数据库对象节点
	 */
	public ForceNodeForm level8(int objID,String objName,List<DefaultForm> formList,JdbcTemplate jdbcTemplate){//数据库
		String sql = "SELECT 9 as Level,B.ID,(B.Name+C.DValue) as Name"
				+ " FROM T_IM_DBOC_Rel A"
				+ " LEFT JOIN T_InterfaceMethod B"
				+ " ON A.InterfaceMethodID = B.ID"
				+ " LEFT JOIN T_Dict C"
				+ " ON B.MethodTypeID = C.DKey"
				+ " WHERE A.DataBaseObjID = ?";
		String nodeFromID = "T_DataBaseObj"+objID;
		//node-data
		ForceNodeForm dbForm = new ForceNodeForm();
		ForceNodeDataForm nodeData = new ForceNodeDataForm();
		nodeData.set$alpha(1).set$color(objColor).set$type(objType);
		dbForm.setId(nodeFromID).setName(objName).setData(nodeData);
		dbForm.setAdjacencies(new ArrayList<ForceAdjForm>());
		
		//node-adjacencies-data
		ForceAdjDataForm adjDataForm = new ForceAdjDataForm();
		adjDataForm.set$alpha(1).set$color(color);
		
		ForceRowArgForm argForm = new ForceRowArgForm();
		argForm.setNodeID(objID)
				.setNodeFromID(nodeFromID)
				.setParamID("ID")
				.setParamName("Name")
				.setAdjData(adjDataForm)
				.setForceNodeDao(this)
				.setFormList(formList)
				.setTableName("T_InterfaceMethod").setJdbcTemplate(jdbcTemplate);
				
		List<ForceAdjForm> adjFormList = jdbcTemplate.query(sql,new Object[]{objID},
				new ForceRowMapper<ForceAdjForm>(argForm));
		dbForm.setAdjacencies(adjFormList);
		return dbForm;
	}
	
	/**
	 * 获取方法节点
	 * @param methodID 方法ID
	 * @param methodName 方法名称
	 * @param formList 节点容器
	 * @return 方法节点
	 */
	public ForceNodeForm level9(int methodID,String methodName,List<DefaultForm> formList,JdbcTemplate jdbcTemplate){//数据库
		String sql = "SELECT 10 as Level,B.ID,B.Name"
				+ " FROM T_InterfaceMethod A"
				+ " LEFT JOIN T_Interface B"
				+ " ON A.InterfaceID = B.ID"
				+ " WHERE A.ID = ?";
		String nodeFromID = "T_InterfaceMethod"+methodID;
		//node-data
		ForceNodeForm dbForm = new ForceNodeForm();
		ForceNodeDataForm nodeData = new ForceNodeDataForm();
		nodeData.set$alpha(1).set$color(methodColor).set$type(methodType);
		dbForm.setId(nodeFromID).setName(methodName).setData(nodeData);
		dbForm.setAdjacencies(new ArrayList<ForceAdjForm>());
		
		//node-adjacencies-data
		ForceAdjDataForm adjDataForm = new ForceAdjDataForm();
		adjDataForm.set$alpha(1).set$color(color);
		
		ForceRowArgForm argForm = new ForceRowArgForm();
		argForm.setNodeID(methodID)
				.setNodeFromID(nodeFromID)
				.setParamID("ID")
				.setParamName("Name")
				.setAdjData(adjDataForm)
				.setForceNodeDao(this)
				.setFormList(formList)
				.setTableName("T_Interface").setJdbcTemplate(jdbcTemplate);
				
		List<ForceAdjForm> adjFormList = jdbcTemplate.query(sql,new Object[]{methodID},
				new ForceRowMapper<ForceAdjForm>(argForm));
		dbForm.setAdjacencies(adjFormList);
		return dbForm;
	}
	
	/**
	 * 获取接口节点
	 * @param interID 接口ID
	 * @param interName 接口名称
	 * @param formList 节点容器
	 * @return 接口节点
	 */
	public ForceNodeForm level10(int interID,String interName,List<DefaultForm> formList,JdbcTemplate jdbcTemplate){//数据库
		String sql = "SELECT 6 as Level,B.ID,B.Name"
				+ " FROM T_Interface A"
				+ " LEFT JOIN T_Application B"
				+ " ON A.ApplicationID = B.ID"
				+ " WHERE A.ID = ?";
		String nodeFromID = "T_Interface"+interID;
		//node-data
		ForceNodeForm dbForm = new ForceNodeForm();
		ForceNodeDataForm nodeData = new ForceNodeDataForm();
		nodeData.set$alpha(1).set$color(interColor).set$type(interType);
		dbForm.setId(nodeFromID).setName(interName).setData(nodeData);
		dbForm.setAdjacencies(new ArrayList<ForceAdjForm>());
		
		//node-adjacencies-data
		ForceAdjDataForm adjDataForm = new ForceAdjDataForm();
		adjDataForm.set$alpha(1).set$color(color);
		
		ForceRowArgForm argForm = new ForceRowArgForm();
		argForm.setNodeID(interID)
				.setNodeFromID(nodeFromID)
				.setParamID("ID")
				.setParamName("Name")
				.setAdjData(adjDataForm)
				.setForceNodeDao(this)
				.setFormList(formList)
				.setTableName("T_Application").setJdbcTemplate(jdbcTemplate);
				
		List<ForceAdjForm> adjFormList = jdbcTemplate.query(sql,new Object[]{interID},
				new ForceRowMapper<ForceAdjForm>(argForm));
		dbForm.setAdjacencies(adjFormList);
		return dbForm;
	}
}
