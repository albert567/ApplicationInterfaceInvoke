package com.gwamcc.aii.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gwamcc.aii.dao.SpaceNodeDao;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.dao.mapper.SpaceRowMapper;
import com.gwamcc.aii.forms.AppNameForm;
import com.gwamcc.aii.forms.DbNameForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.SpaceNodeDataForm;
import com.gwamcc.aii.forms.SpaceNodeForm;
import com.gwamcc.aii.util.sql2.ConditionDefBuilder;
import com.gwamcc.aii.util.sql2.ParamMap;

/**
 * 接口方法数据操作接口实现类
 * @author 张亚平
 *
 */
@Repository
public class SpaceNodeDaoImpl extends DefaultDao implements SpaceNodeDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static int uniqueID = 0;
	
	
	@Override
	public DefaultForm getForceData(int appID) {
		String appName = getAppName(appID);
		List<SpaceNodeForm> list = new ArrayList<SpaceNodeForm>();
		String nodeFromID = "App"+(++uniqueID);
		
		SpaceNodeForm appForm = new SpaceNodeForm();
		SpaceNodeDataForm dform = new SpaceNodeDataForm();
		dform.setNodeID(appID).setType("App");
		appForm.setId(nodeFromID).setName(appName).setData(dform);
		//应用功能
		String sqlFunc = "SELECT -1 as Level,ID,Name,Description"
						+ " FROM T_ApplicationFunction"
						+ " where ApplicationID = ?";

		List<SpaceNodeForm> funcList = jdbcTemplate.query(sqlFunc,new Object[]{appID},
					new SpaceRowMapper<SpaceNodeForm>("ID","Name","Func",this));
		
		//接口
		String sqlInter = "SELECT 1 as Level,ID,Name,Description"
				+ " FROM T_Interface"
				+ " where ApplicationID = ?";

		List<SpaceNodeForm> sFormList = jdbcTemplate.query(sqlInter,new Object[]{appID},
				new SpaceRowMapper<SpaceNodeForm>("ID","Name","Inter",this));
		
		//数据库
		String sqlData = "SELECT 11 as Level,B.ID,B.Name+'数据库' as DbName"
				+ " FROM T_AppDataBase A"
				+ " LEFT JOIN T_DataBase B"
				+ " ON A.DataBaseID=B.ID"
				+ " WHERE A.ApplicationID = ?";

		List<SpaceNodeForm> dataList = jdbcTemplate.query(sqlData,new Object[]{appID},
			new SpaceRowMapper<SpaceNodeForm>("ID","DbName","DataBase",this));
		
		//应用功能
		SpaceNodeForm funForm = new SpaceNodeForm();
		SpaceNodeDataForm funsForm = new SpaceNodeDataForm();
		funForm.setId("T_Func"+(++uniqueID)).setName("应用功能").setData(funsForm);
		funForm.setChildren(funcList);
		list.add(funForm);
		
		//应用接口
		SpaceNodeForm inForm = new SpaceNodeForm();
		SpaceNodeDataForm insForm = new SpaceNodeDataForm();
		inForm.setId("T_Inter"+(++uniqueID)).setName("应用接口").setData(insForm);
		inForm.setChildren(sFormList);
		list.add(inForm);
		
		//数据库
		SpaceNodeForm dbForm = new SpaceNodeForm();
		SpaceNodeDataForm dbsForm = new SpaceNodeDataForm();
		dbForm.setId("T_DB"+(++uniqueID)).setName("数据库").setData(dbsForm);
		dbForm.setChildren(dataList);
		list.add(dbForm);
		
		appForm.setChildren(list);
		return appForm;
	}
	
	private String getAppName(int id){
		List<DefaultForm> formList = queryList(AppNameForm.class, new ConditionDefBuilder().param("id=:ID").define(),
				new ParamMap().put("ID", id));
		if(!formList.isEmpty())
			return ((AppNameForm)(formList.get(0))).getName();
		return null;
	}
	
	public SpaceNodeForm level1(int interID,String interName){
		String sql = "SELECT 2 as Level,A.ID as MethodID,(A.Name+B.DValue) as MethodName"
				+ " FROM T_InterfaceMethod A"
				+ " LEFT JOIN T_Dict B"
				+ " ON A.MethodTypeID = B.DKey"
				+ " WHERE A.InterfaceID = ?";
		
		String nodeFromID = "Inter"+(++uniqueID);
		//node-data
		SpaceNodeForm methodForm = new SpaceNodeForm();
		SpaceNodeDataForm dform = new SpaceNodeDataForm();
		dform.setNodeID(interID).setType("Inter");
		methodForm.setId(nodeFromID).setName(interName).setData(dform);
				
		List<SpaceNodeForm> sFormList = jdbcTemplate.query(sql,new Object[]{interID},
				new SpaceRowMapper<SpaceNodeForm>("MethodID","MethodName","Method",this));
		methodForm.setChildren(sFormList);
		return methodForm;
	}
	
	public SpaceNodeForm level2(int methodID,String methodName){//接口方法
			List<SpaceNodeForm> list = new ArrayList<SpaceNodeForm>();
			String nodeFromID = "Method"+(++uniqueID);
			SpaceNodeForm methodForm = new SpaceNodeForm();
			SpaceNodeDataForm dform = new SpaceNodeDataForm();
			dform.setNodeID(methodID).setType("Method");
			methodForm.setId(nodeFromID).setName(methodName).setData(dform);
			
			String sql = "SELECT 3 as Level,A.DataBaseObjID as ObjID,A.DataBaseID as DbID,(B.ObjName+C.DValue) as ObjName"
						+ " FROM T_IM_DBOC_Rel A"
						+ " LEFT JOIN T_DataBaseObj B"
						+ " ON A.DataBaseObjID = B.ID"
						+ " LEFT JOIN T_Dict C"
						+ " ON B.ObjType = C.DKey"
						+ " WHERE A.DataBaseObjID!=-1 "
						+ " AND A.InterfaceMethodID = ?";
			List<SpaceNodeForm> sFormList = jdbcTemplate.query(sql,new Object[]{methodID},
					new SpaceRowMapper<SpaceNodeForm>("ObjID","ObjName","DbObj",this));
			
			//被该方法调用的方法
			String sql2 = "SELECT 6 as Level,B.ID as MethodID,(B.Name+C.DValue) as MethodName"
					+ " FROM T_ApplicationInterfaceInvoke A"
					+ " LEFT JOIN T_InterfaceMethod B"
					+ " ON A.InterfaceMethodID = B.ID"
					+ " LEFT JOIN T_Dict C"
					+ " ON B.MethodTypeID = C.DKey"
					+ " WHERE A.InvokeMethodID = ?";
			List<SpaceNodeForm> sFormList2 = jdbcTemplate.query(sql2,new Object[]{methodID},
					new SpaceRowMapper<SpaceNodeForm>("MethodID","MethodName","InvokeMethod",this));
			//调用该方法的方法
			String sql3 = "SELECT 6 as Level,B.ID as MethodID,(B.Name+C.DValue) as MethodName"
					+ " FROM T_ApplicationInterfaceInvoke A"
					+ " LEFT JOIN T_InterfaceMethod B"
					+ " ON A.InvokeMethodID = B.ID"
					+ " LEFT JOIN T_Dict C"
					+ " ON B.MethodTypeID = C.DKey"
					+ " WHERE A.InterfaceMethodID = ?";
			List<SpaceNodeForm> sFormList3 = jdbcTemplate.query(sql3,new Object[]{methodID},
					new SpaceRowMapper<SpaceNodeForm>("MethodID","MethodName","InvokedMethod",this));
			//方法使用对象
			SpaceNodeForm objForm = new SpaceNodeForm();
			SpaceNodeDataForm objDataForm = new SpaceNodeDataForm();
			objForm.setId("T_MeObj"+(++uniqueID)).setName("该方法使用的对象").setData(objDataForm);
			objForm.setChildren(sFormList);
			list.add(objForm);
			//被该方法调用的方法(被调用方)
			SpaceNodeForm invokeForm = new SpaceNodeForm();
			SpaceNodeDataForm invokeDataForm = new SpaceNodeDataForm();
			invokeForm.setId("T_InvokeMe"+(++uniqueID)).setName("该方法调用的接口").setData(invokeDataForm);
			invokeForm.setChildren(sFormList2);
			list.add(invokeForm);
			//调用该方法的方法(调用方)
			SpaceNodeForm invokedForm = new SpaceNodeForm();
			SpaceNodeDataForm invokedDataForm = new SpaceNodeDataForm();
			invokedForm.setId("T_InvokedMe"+(++uniqueID)).setName("调用该方法的接口").setData(invokedDataForm);
			invokedForm.setChildren(sFormList3);
			list.add(invokedForm);
			
			methodForm.setChildren(list);
			return methodForm;
	}
	
	public SpaceNodeForm level3(int objID,String objName){//数据库对象
		String sql = "SELECT 4 as Level,A.DataBaseID as DbID,B.Name+'数据库' as DbName "
					+ " FROM T_DataBaseObj A"
					+ " LEFT JOIN T_DataBase B"
					+ " ON A.DataBaseID = B.ID"
					+ " WHERE A.ID = ?";
		
		String nodeFromID = "DbObj"+(++uniqueID);
		//node-data
		SpaceNodeForm dbForm = new SpaceNodeForm();
		SpaceNodeDataForm dform = new SpaceNodeDataForm();
		dform.setNodeID(objID).setType("DbObj");
		dbForm.setId(nodeFromID).setName(objName).setData(dform);
				
		List<SpaceNodeForm> sFormList = jdbcTemplate.query(sql,new Object[]{objID},
				new SpaceRowMapper<SpaceNodeForm>("DbID","DbName","DbObj",this));
		dbForm.setChildren(sFormList);
		return dbForm;
	}
	
	public SpaceNodeForm level4(int dbID,String dbName){//数据库
		String nodeFromID = "DataBase"+(++uniqueID);
		//node-data
		SpaceNodeForm objForm = new SpaceNodeForm();
		SpaceNodeDataForm dform = new SpaceNodeDataForm();
		dform.setNodeID(dbID).setType("DataBase");
		objForm.setId(nodeFromID).setName(dbName).setData(dform);
		
		objForm.setChildren(new ArrayList<SpaceNodeForm>());
		return objForm;
	}
	
	public SpaceNodeForm level5(int appID,String appName){//应用系统
		String nodeFromID = "App"+(++uniqueID);
		//node-data
		SpaceNodeForm objForm = new SpaceNodeForm();
		SpaceNodeDataForm dform = new SpaceNodeDataForm();
		dform.setNodeID(appID).setType("App");
		objForm.setId(nodeFromID).setName(appName).setData(dform);
		objForm.setChildren(new ArrayList<SpaceNodeForm>());
		return objForm;
	}
	
	public SpaceNodeForm level6(int methodID,String methodName){//
		String sql = "SELECT 7 as Level,B.ID,B.Name"
				+ " FROM T_InterfaceMethod A"
				+ " LEFT JOIN T_Interface B"
				+ " ON A.InterfaceID = B.ID"
				+ " WHERE A.ID = ?";
		String nodeFromID = "Method"+(++uniqueID);
		//node-data
		SpaceNodeForm dbForm = new SpaceNodeForm();
		SpaceNodeDataForm nodeData = new SpaceNodeDataForm();
		nodeData.setNodeID(methodID).setType("Method");
		dbForm.setId(nodeFromID).setName(methodName).setData(nodeData);
		
		List<SpaceNodeForm> sFormList = jdbcTemplate.query(sql,new Object[]{methodID},
				new SpaceRowMapper<SpaceNodeForm>("ID","Name","Inter",this));
		dbForm.setChildren(sFormList);
		return dbForm;
	}
	
	public SpaceNodeForm level7(int interID,String interName){//
		String sql = "SELECT 5 as Level,B.ID,B.Name"
				+ " FROM T_Interface A"
				+ " LEFT JOIN T_Application B"
				+ " ON A.ApplicationID = B.ID"
				+ " WHERE A.ID = ?";
		String nodeFromID = "Inter"+(++uniqueID);
		//node-data
		SpaceNodeForm dbForm = new SpaceNodeForm();
		SpaceNodeDataForm nodeData = new SpaceNodeDataForm();
		nodeData.setNodeID(interID).setType("Inter");
		dbForm.setId(nodeFromID).setName(interName).setData(nodeData);
		
		List<SpaceNodeForm> sFormList = jdbcTemplate.query(sql,new Object[]{interID},
				new SpaceRowMapper<SpaceNodeForm>("ID","Name","App",this));
		dbForm.setChildren(sFormList);
		return dbForm;
	}

	@Override
	public DefaultForm getDbData(int dbID) {
		List<SpaceNodeForm> list = new ArrayList<SpaceNodeForm>();
		String sql = "SELECT 8 as Level,A.ID,(A.ObjName+B.DValue) as ObjName"
				+ " FROM T_DataBaseObj A"
				+ " LEFT JOIN T_Dict B"
				+ " ON A.ObjType = B.DKey"
				+ " WHERE DataBaseID=?";
		String nodeFromID = "DataBase"+(++uniqueID);
		String dbName = getDbName(dbID);
		dbName += "数据库";
		//node-data
		SpaceNodeForm dbForm = new SpaceNodeForm();
		SpaceNodeDataForm nodeData = new SpaceNodeDataForm();
		nodeData.setNodeID(dbID).setType("DataBase");
		dbForm.setId(nodeFromID).setName(dbName).setData(nodeData);
		//数据库对象
		List<SpaceNodeForm> sFormList = jdbcTemplate.query(sql,new Object[]{dbID},
				new SpaceRowMapper<SpaceNodeForm>("ID","ObjName","DbObj",this));
		
		//应用系统
		String appSql = "SELECT 12 as Level,B.ID,B.Name"
				+ " FROM T_AppDataBase A"
				+ " LEFT JOIN T_Application B"
				+ " ON A.ApplicationID=B.ID"
				+ " WHERE A.DataBaseID = ?";
		List<SpaceNodeForm> appFormList = jdbcTemplate.query(appSql,new Object[]{dbID},
				new SpaceRowMapper<SpaceNodeForm>("ID","Name","App",this));
		//数据库对象
		SpaceNodeForm objForm = new SpaceNodeForm();
		SpaceNodeDataForm objData = new SpaceNodeDataForm();
		objForm.setId("T_DbObj").setName("数据库对象").setData(objData);
		objForm.setChildren(sFormList);
		list.add(objForm);
		//应用系统
		SpaceNodeForm appForm = new SpaceNodeForm();
		SpaceNodeDataForm appData = new SpaceNodeDataForm();
		appForm.setId("T_App").setName("应用系统").setData(appData);
		appForm.setChildren(appFormList);
		list.add(appForm);
		
		dbForm.setChildren(list);
		return dbForm;
	}
	
	private String getDbName(int id){
		List<DefaultForm> formList = queryList(DbNameForm.class, new ConditionDefBuilder().param("id=:ID").define(),
				new ParamMap().put("ID", id));
		if(!formList.isEmpty())
			return ((DbNameForm)(formList.get(0))).getName();
		return null;
	}
	
	public SpaceNodeForm level8(int objID,String objName){//
		String sql = "SELECT 9 as Level,B.ID,(B.Name+C.DValue) as Name"
				+ " FROM T_IM_DBOC_Rel A"
				+ " LEFT JOIN T_InterfaceMethod B"
				+ " ON A.InterfaceMethodID = B.ID"
				+ " LEFT JOIN T_Dict C"
				+ " ON B.MethodTypeID = C.DKey"
				+ " WHERE A.DataBaseObjID = ?";
		String nodeFromID = "DbObj"+(++uniqueID);
		//node-data
		SpaceNodeForm dbForm = new SpaceNodeForm();
		SpaceNodeDataForm nodeData = new SpaceNodeDataForm();
		nodeData.setNodeID(objID).setType("DbObj");
		dbForm.setId(nodeFromID).setName(objName).setData(nodeData);
		
		List<SpaceNodeForm> sFormList = jdbcTemplate.query(sql,new Object[]{objID},
				new SpaceRowMapper<SpaceNodeForm>("ID","Name","Method",this));
		dbForm.setChildren(sFormList);
		return dbForm;
	}
	
	public SpaceNodeForm level9(int methodID,String methodName){
		String sql = "SELECT 10 as Level,B.ID,B.Name"
				+ " FROM T_InterfaceMethod A"
				+ " LEFT JOIN T_Interface B"
				+ " ON A.InterfaceID = B.ID"
				+ " WHERE A.ID = ?";
		String nodeFromID = "Method"+(++uniqueID);
		//node-data
		SpaceNodeForm dbForm = new SpaceNodeForm();
		SpaceNodeDataForm nodeData = new SpaceNodeDataForm();
		nodeData.setNodeID(methodID).setType("Method");
		dbForm.setId(nodeFromID).setName(methodName).setData(nodeData);
				
		List<SpaceNodeForm> sFormList = jdbcTemplate.query(sql,new Object[]{methodID},
				new SpaceRowMapper<SpaceNodeForm>("ID","Name","Inter",this));
		dbForm.setChildren(sFormList);
		return dbForm;
	}
	
	public SpaceNodeForm level10(int interID,String interName){//数据库
		String sql = "SELECT 5 as Level,B.ID,B.Name"
				+ " FROM T_Interface A"
				+ " LEFT JOIN T_Application B"
				+ " ON A.ApplicationID = B.ID"
				+ " WHERE A.ID = ?";
		String nodeFromID = "Inter"+(++uniqueID);
		//node-data
		SpaceNodeForm dbForm = new SpaceNodeForm();
		SpaceNodeDataForm nodeData = new SpaceNodeDataForm();
		nodeData.setNodeID(interID).setType("Inter");
		dbForm.setId(nodeFromID).setName(interName).setData(nodeData);
		
		List<SpaceNodeForm> sFormList = jdbcTemplate.query(sql,new Object[]{interID},
				new SpaceRowMapper<SpaceNodeForm>("ID","Name","App",this));
		dbForm.setChildren(sFormList);
		return dbForm;
	}
	
	public SpaceNodeForm level11(int dbID,String dbName){
		String sql = "SELECT -1 as Level,A.ID,(A.ObjName+B.DValue) as ObjName"
				+ " FROM T_DataBaseObj A"
				+ " LEFT JOIN T_Dict B"
				+ " ON A.ObjType = B.DKey"
				+ " WHERE DataBaseID=?";
		String nodeFromID = "DataBase"+(++uniqueID);
		//node-data
		SpaceNodeForm dbForm = new SpaceNodeForm();
		SpaceNodeDataForm nodeData = new SpaceNodeDataForm();
		nodeData.setNodeID(dbID).setType("DataBase");
		dbForm.setId(nodeFromID).setName(dbName).setData(nodeData);
		
		List<SpaceNodeForm> sFormList = jdbcTemplate.query(sql,new Object[]{dbID},
				new SpaceRowMapper<SpaceNodeForm>("ID","ObjName","DbObj",this));
		dbForm.setChildren(sFormList);
		return dbForm;
	}
	
	public SpaceNodeForm level12(int appID,String appName){
		List<SpaceNodeForm> list = new ArrayList<SpaceNodeForm>();
		String nodeFromID = "App"+(++uniqueID);
		
		SpaceNodeForm appForm = new SpaceNodeForm();
		SpaceNodeDataForm dform = new SpaceNodeDataForm();
		dform.setNodeID(appID).setType("App");
		appForm.setId(nodeFromID).setName(appName).setData(dform);
		//应用功能
		String sqlFunc = "SELECT -1 as Level,ID,Name,Description"
						+ " FROM T_ApplicationFunction"
						+ " where ApplicationID = ?";

		List<SpaceNodeForm> funcList = jdbcTemplate.query(sqlFunc,new Object[]{appID},
					new SpaceRowMapper<SpaceNodeForm>("ID","Name","Func",this));
		
		//接口
		String sqlInter = "SELECT 1 as Level,ID,Name,Description"
				+ " FROM T_Interface"
				+ " where ApplicationID = ?";

		List<SpaceNodeForm> sFormList = jdbcTemplate.query(sqlInter,new Object[]{appID},
				new SpaceRowMapper<SpaceNodeForm>("ID","Name","Inter",this));
		
		//应用功能
		SpaceNodeForm funForm = new SpaceNodeForm();
		SpaceNodeDataForm funsForm = new SpaceNodeDataForm();
		funForm.setId("T_Func"+(++uniqueID)).setName("应用功能").setData(funsForm);
		funForm.setChildren(funcList);
		list.add(funForm);
		
		//应用接口
		SpaceNodeForm inForm = new SpaceNodeForm();
		SpaceNodeDataForm insForm = new SpaceNodeDataForm();
		inForm.setId("T_Inter"+(++uniqueID)).setName("应用接口").setData(insForm);
		inForm.setChildren(sFormList);
		list.add(inForm);
		
		appForm.setChildren(list);
		return appForm;
	}

}
