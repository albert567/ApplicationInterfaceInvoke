package com.gwamcc.aii.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gwamcc.aii.dao.LeftMenuDao;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.forms.AppFuncForm;
import com.gwamcc.aii.forms.AppInterForm;
import com.gwamcc.aii.forms.ApplicationForm;
import com.gwamcc.aii.forms.DataBaseForm;
import com.gwamcc.aii.forms.DataBaseObjForm;
import com.gwamcc.aii.forms.DbObjColForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.DictForm;
import com.gwamcc.aii.forms.InterMethodForm;
import com.gwamcc.aii.forms.LeftNodeForm;
import com.gwamcc.aii.forms.MethodParamForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.ProcedureParamForm;
import com.gwamcc.aii.forms.RoleForm;
import com.gwamcc.aii.forms.TreeForm;
import com.gwamcc.aii.util.EscapeUnescape;
import com.gwamcc.aii.util.sql2.ConditionDefBuilder;
import com.gwamcc.aii.util.sql2.ParamMap;

/**
 * 菜单树数据操作实现类
 *
 * @author 张亚平
 *
 */
@Repository
public class LeftMenuDaoImpl extends DefaultDao implements LeftMenuDao {
	public final static String procedureType="0401";
	public final static String functionType="0404";
	public final static String dbInterType = "0202";//数据库接口
	public final static String methodInterType = "0201";//WebService接口
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static int uniqueId = 200;

	@Override
	public List<DefaultForm> getAppList() {
		String sql = "select A.ID,A.Name,A.Description,B.DValue,'App' as Type"
				+ " from T_Application A"
				+ " left join T_Dict B"
				+ " on A.AppType=B.DKey order by A.sn";
		List<DefaultForm> list = jdbcTemplate.query(sql, new RowMapper<DefaultForm>() {
			@Override
			public DefaultForm mapRow(ResultSet rs, int arg1) throws SQLException {
				LeftNodeForm lnode = new LeftNodeForm();
				lnode.setId(++uniqueId)
					 .setDataID(rs.getInt("ID"))
					 .setText(rs.getString("Name"))
					 .setState(TreeForm.STATE_CLOSED)
					 .setType(rs.getString("Type"))
					 .setIconCls("icon-Extension-application")
					 /*.setAttributes("AppInterface.html?appId="+rs.getInt("ID")+"&appName="
						+ EscapeUnescape.escape(rs.getString("Name"))
						+"&appTypeName="+EscapeUnescape.escape(rs.getString("DValue"))
						+"&description="+EscapeUnescape.escape(rs.getString("Description")))*/
					 .setAttributes("AppFunction.html?appId="+rs.getInt("ID")+"&appName="
						+EscapeUnescape.escape(rs.getString("Name"))
						+"&appTypeName="+EscapeUnescape.escape(rs.getString("DValue"))
						+"&parentID=-1"
						+"&description="+EscapeUnescape.escape(rs.getString("Description")))
					 .setChildren(new ArrayList<LeftNodeForm>());
				return lnode;
			}

		});
		return list;
	}
	@Override
	public List<DefaultForm> getAppMenu(int appID){
		List<DefaultForm> list = new ArrayList<DefaultForm>();
		
		ApplicationForm appForm = getAppInfo(appID);
		if(appForm==null){
			return null;
		}
		
		LeftNodeForm appFun = new LeftNodeForm();
		appFun.setId(++uniqueId).setDataID(appID)
			.setType("FuncMenu").setText("应用功能")
			.setIconCls("icon-Extension-wrench")
			.setAttributes("AppFunction.html?appId="+appID+"&appName="+ EscapeUnescape.escape(appForm.getName())
				+"&appTypeName="+EscapeUnescape.escape(appForm.getAppTypeName())
				+"&parentID=-1"
				+"&description="+EscapeUnescape.escape(appForm.getDescription()))
			.setChildren(new ArrayList<LeftNodeForm>());
		if(!getFuncList(appID).isEmpty()){
			appFun.setState(TreeForm.STATE_CLOSED);
		}
		list.add(appFun);
		LeftNodeForm appInter = new LeftNodeForm();
		appInter.setId(++uniqueId).setDataID(appID)
			.setType("InterMenu").setText("应用接口")
			.setIconCls("icon-Extension-link")
			.setState(TreeForm.STATE_CLOSED)
			.setAttributes("AppInterface.html?appId="+appID+"&appName="+ EscapeUnescape.escape(appForm.getName())
				+"&appTypeName="+EscapeUnescape.escape(appForm.getAppTypeName())
				+"&description="+EscapeUnescape.escape(appForm.getDescription()))
			.setChildren(new ArrayList<LeftNodeForm>());
		list.add(appInter);
		LeftNodeForm appDb = new LeftNodeForm();
		appDb.setId(++uniqueId).setDataID(appID)
			.setType("DbMenu").setText("数据库")
			.setIconCls("icon-Extension-databases")
			.setAttributes("AppDataBase.html?appId="+appID+"&appName="+ EscapeUnescape.escape(appForm.getName())
					+"&appTypeName="+EscapeUnescape.escape(appForm.getAppTypeName())
					+"&description="+EscapeUnescape.escape(appForm.getDescription()))
			.setChildren(new ArrayList<LeftNodeForm>());
		if(!getDbList(appID).isEmpty()){
			appDb.setState(TreeForm.STATE_CLOSED);
		}
		list.add(appDb);
		
		LeftNodeForm code = new LeftNodeForm();
		code.setId(++uniqueId).setDataID(appID)
			.setType("Code").setText("源代码")
			.setIconCls("icon-Extension-page_code")
			.setAttributes("CodeStructure.html?appId="+appID+"&appName="+ EscapeUnescape.escape(appForm.getName())
					+"&appTypeName="+EscapeUnescape.escape(appForm.getAppTypeName())
					+"&description="+EscapeUnescape.escape(appForm.getDescription()))
			.setChildren(new ArrayList<LeftNodeForm>());
		list.add(code);
		return list;
	}
	
	@Override
	public List<DefaultForm> getFuncList(int appID){
		String sql = "select B.ID AppID,B.Name AppName,A.ID FuncID,A.Name FuncName,A.description FuncDesc,'Func' as Type"
				+ " from T_ApplicationFunction A"
				+ " left join T_Application B on A.ApplicationID = B.ID"
				+ " where A.ApplicationID=? AND A.ParentID=-1 Order By A.Sn";
		List<DefaultForm> list = jdbcTemplate.query(sql,new Object[]{appID},new RowMapper<DefaultForm>() {
			@Override
			public DefaultForm mapRow(ResultSet rs, int arg1) throws SQLException {
				LeftNodeForm lnode = new LeftNodeForm();
				lnode.setId(++uniqueId)
					 .setDataID(rs.getInt("FuncID"))
					 .setText(rs.getString("FuncName"))
					 .setType(rs.getString("Type"))
					 .setIconCls("icon-Extension-wrench")
					 .setAttributes("AppFunction.html?appId="+rs.getInt("AppID")
					 	+"&appName="+ EscapeUnescape.escape(rs.getString("AppName"))
						+"&parentID="+rs.getInt("FuncID")
						+"&funcName="+EscapeUnescape.escape(rs.getString("FuncName"))
						+"&description="+EscapeUnescape.escape(rs.getString("FuncDesc")))
					 .setChildren(new ArrayList<LeftNodeForm>());
				if(!getFunc(rs.getInt("FuncID")).isEmpty()){
					lnode.setState(TreeForm.STATE_CLOSED);
				}
				return lnode;
			}

		});
		return list;
	}
	
	@Override
	public List<DefaultForm> getFunc(int funcID){
		String sql = "select B.ID AppID,B.Name AppName,A.ID FuncID,A.Name FuncName,A.description FuncDesc,'Func' as Type"
				+ " from T_ApplicationFunction A"
				+ " left join T_Application B on A.ApplicationID = B.ID"
				+ " where A.ParentID=? Order By A.Sn";
		List<DefaultForm> list = jdbcTemplate.query(sql,new Object[]{funcID},new RowMapper<DefaultForm>() {
			@Override
			public DefaultForm mapRow(ResultSet rs, int arg1) throws SQLException {
				LeftNodeForm lnode = new LeftNodeForm();
				lnode.setId(++uniqueId)
					 .setDataID(rs.getInt("FuncID"))
					 .setText(rs.getString("FuncName"))
					 .setType(rs.getString("Type"))
					 .setIconCls("icon-Extension-wrench")
					 
					 .setChildren(new ArrayList<LeftNodeForm>());
				if(!getFunc(rs.getInt("FuncID")).isEmpty()){
					lnode.setState(TreeForm.STATE_CLOSED)
					.setAttributes("AppFunction.html?appId="+rs.getInt("AppID")
				 	+"&appName="+ EscapeUnescape.escape(rs.getString("AppName"))
					+"&parentID="+rs.getInt("FuncID")
					+"&funcName="+EscapeUnescape.escape(rs.getString("FuncName"))
					+"&description="+EscapeUnescape.escape(rs.getString("FuncDesc")));
				}else{
					lnode.setState(TreeForm.STATE_OPEN)
					.setAttributes("AppFuncObj.html?appId="+rs.getInt("AppID")
				 	+"&appName="+ EscapeUnescape.escape(rs.getString("AppName"))
					+"&funcId="+rs.getInt("FuncID")
					+"&funcName="+EscapeUnescape.escape(rs.getString("FuncName"))
					+"&description="+EscapeUnescape.escape(rs.getString("FuncDesc")));
				}
				return lnode;
			}

		});
		return list;
	}

	@Override
	public List<DefaultForm> getInterType(final int appID){
		final ApplicationForm appForm = getAppInfo(appID);
		if(appForm==null){
			return null;
		}
		String sql = "select DKey as ID,DValue as Name,'InterType' as Type from T_Dict where DType='02'";
		List<DefaultForm> list = jdbcTemplate.query(sql,new RowMapper<DefaultForm>() {
			@Override
			public DefaultForm mapRow(ResultSet rs, int arg1) throws SQLException {
				LeftNodeForm lnode = new LeftNodeForm();
				lnode.setId(++uniqueId)
					 .setDataID(appID)
					 .setTypeID(rs.getString("ID"))
					 .setText(rs.getString("Name"))
					 .setType(rs.getString("Type"))
					 .setIconCls("icon-Extension-box_picture")
					 .setChildren(new ArrayList<LeftNodeForm>())
					 .setAttributes("AppInterface.html?appId="+appID
							 +"&appName="+EscapeUnescape.escape(appForm.getName())
							 +"&appTypeName="+EscapeUnescape.escape(appForm.getAppTypeName())
							 +"&description="+EscapeUnescape.escape(appForm.getDescription())
							 +"&interTypeID="+rs.getString("ID"));
				if(!getInterList(appID, rs.getString("ID")).isEmpty()){
					lnode.setState(TreeForm.STATE_CLOSED);
				}
				return lnode;
			}
		});
		return list;
	}
	
	@Override
	public List<DefaultForm> getInterList(final int appID,final String interTypeID) {
		String sql = "select A.ID as InterID,A.Name as InterName,A.InterfaceTypeID as InterTypeID,A.Description,"
				+ "	B.Name as AppName,C.DValue,'Inter' as Type"
				+ " from T_Interface A"
				+ " left join T_Application B"
				+ " on A.ApplicationID=B.ID"
				+ " left join T_Dict C"
				+ " on A.InterfaceTypeID=C.DKey"
				+ " where A.ApplicationID=? and A.InterfaceTypeID=? Order by A.Name asc";
		List<DefaultForm> list = jdbcTemplate.query(sql,new Object[]{appID,interTypeID},new RowMapper<DefaultForm>() {
			@Override
			public DefaultForm mapRow(ResultSet rs, int arg1) throws SQLException {
				LeftNodeForm lnode = new LeftNodeForm();
				lnode.setId(++uniqueId)
					 .setDataID(rs.getInt("InterID"))
					 .setText(rs.getString("InterName"))
					 .setTypeID(interTypeID)
					 .setType(rs.getString("Type"))
					 .setIconCls("icon-Extension-link")
					 .setChildren(new ArrayList<LeftNodeForm>());
				String interTypeID = rs.getString("InterTypeID");
				if(methodInterType.equals(interTypeID)){
					lnode.setAttributes("InterfaceMethod.html?appId="+appID
							 +"&interId="+rs.getInt("InterID")
							 +"&appName="+ EscapeUnescape.escape(rs.getString("AppName"))
							 +"&interName="+EscapeUnescape.escape(rs.getString("InterName"))
							 +"&interTypeName="+EscapeUnescape.escape(rs.getString("DValue"))
							 +"&description="+EscapeUnescape.escape(rs.getString("Description")));
					if(!getMethodList(rs.getInt("InterID")).isEmpty()){
						lnode.setState(TreeForm.STATE_CLOSED);
					}
				}else if(dbInterType.equals(interTypeID)){
					lnode.setAttributes("InterProcedure.html?appId="+appID
							 +"&interId="+rs.getInt("InterID")
							 +"&appName="+ EscapeUnescape.escape(rs.getString("AppName"))
							 +"&interName="+EscapeUnescape.escape(rs.getString("InterName"))
							 +"&interTypeName="+EscapeUnescape.escape(rs.getString("DValue"))
							 +"&description="+EscapeUnescape.escape(rs.getString("Description")));
					if(!getProcedureList(rs.getInt("InterID")).isEmpty()){
						lnode.setState(TreeForm.STATE_CLOSED);
					}
				}
				
				return lnode;
			}

		});
		return list;
	}
	
	@Override
	public List<DefaultForm> getDbList(int appID){
		String sql = "select B.ID,B.Name,B.NameEn,B.Description,'Db' as Type"
				+ " from T_AppDataBase A"
				+ " left join T_DataBase B"
				+ " on A.DataBaseID = B.ID"
				+ " where ApplicationID=? Order by B.Name asc";
		List<DefaultForm> list = jdbcTemplate.query(sql,new Object[]{appID},new RowMapper<DefaultForm>() {
			@Override
			public DefaultForm mapRow(ResultSet rs, int arg1) throws SQLException {
				LeftNodeForm lnode = new LeftNodeForm();
				lnode.setId(++uniqueId)
					 .setDataID(rs.getInt("ID"))
					 .setText(rs.getString("Name"))
					 .setState(TreeForm.STATE_CLOSED)
					 .setType(rs.getString("Type"))
					 .setIconCls("icon-Extension-database")
					 .setAttributes("DataBaseObj.html?dbID="+rs.getInt("ID")
					 +"&dbName="+EscapeUnescape.escape(rs.getString("Name"))
					 +"&dbEn="+ EscapeUnescape.escape(rs.getString("NameEn"))
					 +"&description="+EscapeUnescape.escape(rs.getString("Description")))
					 .setChildren(new ArrayList<LeftNodeForm>());
				return lnode;
			}

		});
		return list;
	}

	@Override
	public List<DefaultForm> getMethodList(final int interID) {
		String sql = "select A.ID as MethodID,A.Name as MethodName,"
				+ "	A.Description,B.Name as InterName,"
				+ " C.ID as AppID,C.Name as AppName,"
				+ " D.DValue,'Method' as Type"
				+ " from T_InterfaceMethod A"
				+ " left join T_Interface B"
				+ " on A.InterfaceID = B.ID"
				+ " left join T_Application C"
				+ " on B.ApplicationID = C.ID"
				+ " left join T_Dict D"
				+ " on A.MethodTypeID = D.DKey"
				+ " where A.InterfaceID=? Order by A.Name asc";
		List<DefaultForm> list = jdbcTemplate.query(sql,new Object[]{interID},new RowMapper<DefaultForm>() {
			@Override
			public DefaultForm mapRow(ResultSet rs, int arg1) throws SQLException {
				LeftNodeForm lnode = new LeftNodeForm();
				lnode.setId(++uniqueId)
					 .setDataID(rs.getInt("MethodID"))
					 .setText(rs.getString("MethodName"))
					 .setState(TreeForm.STATE_CLOSED)
					 .setType(rs.getString("Type"))
					 .setIconCls("icon-Extension-hammer")
					 .setAttributes("MethodParam.html?appId="+rs.getInt("AppID")
							 +"&interId="+interID+"&method="+rs.getInt("MethodID")
							 +"&appName="+ EscapeUnescape.escape(rs.getString("AppName"))
							 +"&interName="+EscapeUnescape.escape(rs.getString("InterName"))
							 +"&methodName="+EscapeUnescape.escape(rs.getString("MethodName"))
							 +"&methodTypeName="+EscapeUnescape.escape(rs.getString("DValue"))
							 +"&description="+EscapeUnescape.escape(rs.getString("Description")))
					 .setChildren(new ArrayList<LeftNodeForm>());
				return lnode;
			}
		});
		return list;
	}
	
	@Override
	public List<DefaultForm> getProcedureList(final int interID) {
		String sql = "SELECT A.ObjID as ObjID,B.ObjName, D.DValue,B.ObjDescription,"
				+ " B.DataBaseID as DbID,C.Name as DbName,'Obj' as Type"
				+ " FROM T_InterProcedure A "
				+ " LEFT JOIN T_DataBaseObj B ON A.ObjID=B.ID "
				+ " LEFT JOIN T_DataBase C ON B.DataBaseID = C.ID"
				+ " LEFT JOIN T_Dict D ON B.ObjType=D.DKey"
				+ " WHERE A.InterfaceID=? Order by B.ObjName asc";
		List<DefaultForm> list = jdbcTemplate.query(sql,new Object[]{interID},new RowMapper<DefaultForm>() {
			@Override
			public DefaultForm mapRow(ResultSet rs, int arg1) throws SQLException {
				LeftNodeForm lnode = new LeftNodeForm();
				lnode.setId(++uniqueId)
					 .setDataID(rs.getInt("ObjID"))
					 .setText(rs.getString("ObjName"))
					 .setType(rs.getString("Type"))
					 .setIconCls("icon-Extension-hammer")
					 .setAttributes("ProcedureParam.html?dbID="+rs.getInt("DbID")
							 +"&objID="+rs.getInt("ObjID")
							 +"&dbName="+ EscapeUnescape.escape(rs.getString("DbName"))
							 +"&objName="+EscapeUnescape.escape(rs.getString("ObjName"))
							 +"&objType="+EscapeUnescape.escape(rs.getString("DValue"))
							 +"&description="+EscapeUnescape.escape(rs.getString("ObjDescription")))
					 .setChildren(new ArrayList<LeftNodeForm>());
				return lnode;
			}
		});
		return list;
	}
	
	@Override
	public List<DefaultForm> getMethodMenu(int methodID){
		List<DefaultForm> list = new ArrayList<DefaultForm>();
		InterMethodForm methodForm = getMethodInfo(methodID);
		if(methodForm==null)
			return null;
		
		LeftNodeForm mdbMenu = new LeftNodeForm();
		mdbMenu.setId(++uniqueId).setDataID(methodID)
			.setType("MDbMenu").setText("该方法使用的对象")
			.setIconCls("icon-Extension-cmy")
			.setAttributes("IM_DBOC_Rel.html?appId="+methodForm.getAppID()
			 +"&interId="+methodForm.getInterID()+"&method="+methodID
			 +"&appName="+ EscapeUnescape.escape(methodForm.getAppName())
			 +"&interName="+EscapeUnescape.escape(methodForm.getInterName())
			 +"&methodName="+EscapeUnescape.escape(methodForm.getMethodName())
			 +"&methodTypeName="+EscapeUnescape.escape(methodForm.getMethodTypeName())
			 +"&description="+EscapeUnescape.escape(methodForm.getDescription()))
			.setChildren(new ArrayList<LeftNodeForm>());
		
		list.add(mdbMenu);
		LeftNodeForm minterMenu = new LeftNodeForm();
		minterMenu.setId(++uniqueId).setDataID(methodID)
			.setType("MInterMenu").setText("该方法调用的接口")
			.setIconCls("icon-Extension-arrow_right")
			.setAttributes("AppInterfaceInvoked.html?appId="+methodForm.getAppID()
			 +"&interId="+methodForm.getInterID()+"&method="+methodID
			 +"&appName="+ EscapeUnescape.escape(methodForm.getAppName())
			 +"&interName="+EscapeUnescape.escape(methodForm.getInterName())
			 +"&methodName="+EscapeUnescape.escape(methodForm.getMethodName())
			 +"&methodTypeName="+EscapeUnescape.escape(methodForm.getMethodTypeName())
			 +"&description="+EscapeUnescape.escape(methodForm.getDescription()))
			.setChildren(new ArrayList<LeftNodeForm>());
		list.add(minterMenu);
		LeftNodeForm minterdMenu = new LeftNodeForm();
		minterdMenu.setId(++uniqueId).setDataID(methodID)
			.setType("MInterdMenu").setText("调用该方法的接口")
			.setIconCls("icon-Extension-arrow_left")
			.setAttributes("ApplicationInterfaceInvoke.html?appId="+methodForm.getAppID()
			 +"&interId="+methodForm.getInterID()+"&method="+methodID
			 +"&appName="+ EscapeUnescape.escape(methodForm.getAppName())
			 +"&interName="+EscapeUnescape.escape(methodForm.getInterName())
			 +"&methodName="+EscapeUnescape.escape(methodForm.getMethodName())
			 +"&methodTypeName="+EscapeUnescape.escape(methodForm.getMethodTypeName())
			 +"&description="+EscapeUnescape.escape(methodForm.getDescription()))
			.setChildren(new ArrayList<LeftNodeForm>());
		list.add(minterdMenu);
		return list;
	}

	@Override
	public List<DefaultForm> getDbList() {
		String sql = "select ID,Name,NameEn,Description,'Db' as Type from T_DataBase Order by Name asc";
		List<DefaultForm> list = jdbcTemplate.query(sql,new RowMapper<DefaultForm>() {
			@Override
			public DefaultForm mapRow(ResultSet rs, int arg1) throws SQLException {
				LeftNodeForm lnode = new LeftNodeForm();
				lnode.setId(++uniqueId)
					 .setDataID(rs.getInt("ID"))
					 .setText(rs.getString("Name"))
					 .setState(TreeForm.STATE_CLOSED)
					 .setType(rs.getString("Type"))
					 .setIconCls("icon-Extension-database")
					 .setAttributes("DataBaseObj.html?dbID="+rs.getInt("ID")
							 +"&dbName="+EscapeUnescape.escape(rs.getString("Name"))
							 +"&dbEn="+ EscapeUnescape.escape(rs.getString("NameEn"))
							 +"&description="+EscapeUnescape.escape(rs.getString("Description")))
					 .setChildren(new ArrayList<LeftNodeForm>());
				return lnode;
			}
		});
		return list;
	}
	
	@Override
	public List<DefaultForm> getObjType(final int dbID){
		final DataBaseForm dbForm = getDbInfo(dbID);
		if(dbForm==null)
			return null;
		String sql = "select DKey as ID,DValue as Name,'ObjType' as Type from T_Dict where DType='04'";
		List<DefaultForm> list = jdbcTemplate.query(sql,new RowMapper<DefaultForm>() {
			@Override
			public DefaultForm mapRow(ResultSet rs, int arg1) throws SQLException {
				LeftNodeForm lnode = new LeftNodeForm();
				lnode.setId(++uniqueId)
					 .setDataID(dbID)
					 .setTypeID(rs.getString("ID"))
					 .setText(rs.getString("Name"))
					 .setType(rs.getString("Type"))
					 .setIconCls("icon-Extension-box_picture")
					 .setAttributes("DataBaseObj.html?dbID="+dbID
					 +"&dbName="+EscapeUnescape.escape(dbForm.getName())
					 +"&dbEn="+ EscapeUnescape.escape(dbForm.getNameEn())
					 +"&objTypeID="+EscapeUnescape.escape(rs.getString("ID"))
					 +"&description="+EscapeUnescape.escape(dbForm.getDescription()))
					 .setChildren(new ArrayList<LeftNodeForm>());
				if(!getObjList(dbID, rs.getString("ID")).isEmpty()){
					lnode.setState(TreeForm.STATE_CLOSED);
				}
				return lnode;
			}
		});
		return list;
	}

	@Override
	public List<DefaultForm> getObjList(int dbID,final String typeID) {
		String sql = "select A.ID as ObjID,A.ObjName,A.ObjDescription as Description,"
				+ "	B.ID as DbID,B.Name as DbName,"
				+ " C.DValue,'Obj' as Type"
				+ " from T_DataBaseObj A"
				+ " left join T_DataBase B"
				+ " on A.DataBaseID=B.ID"
				+ " left join T_Dict C"
				+ " on A.ObjType=C.DKey"
				+ " where A.DataBaseID=?"
				+ " and A.ObjType=? Order by A.ObjName asc";
		List<DefaultForm> list = jdbcTemplate.query(sql,new Object[]{dbID,typeID},new RowMapper<DefaultForm>() {
			@Override
			public DefaultForm mapRow(ResultSet rs, int arg1) throws SQLException {
				LeftNodeForm lnode = new LeftNodeForm();
				lnode.setId(++uniqueId)
					 .setDataID(rs.getInt("ObjID"))
					 .setText(rs.getString("ObjName"))
					 .setType(rs.getString("Type"))
					 .setIconCls("icon-Extension-cmy")
					 .setChildren(new ArrayList<LeftNodeForm>());
				if(typeID.equals(procedureType)||typeID.equals(functionType)){
					lnode.setAttributes("ProcedureParam.html?dbID="+rs.getInt("DbID")
					 +"&objID="+rs.getInt("ObjID")
					 +"&dbName="+EscapeUnescape.escape(rs.getString("DbName"))
					 +"&objName="+EscapeUnescape.escape(rs.getString("ObjName"))
					 +"&objTypeID="+typeID
					 +"&objType="+ EscapeUnescape.escape(rs.getString("DValue"))
					 +"&description="+EscapeUnescape.escape(rs.getString("Description")));
				}else{
					lnode.setAttributes("DataBaseObjCol.html?dbID="+rs.getInt("DbID")
					 +"&objID="+rs.getInt("ObjID")
					 +"&dbName="+EscapeUnescape.escape(rs.getString("DbName"))
					 +"&objName="+EscapeUnescape.escape(rs.getString("ObjName"))
					 +"&objTypeID="+typeID
					 +"&objType="+ EscapeUnescape.escape(rs.getString("DValue"))
					 +"&description="+EscapeUnescape.escape(rs.getString("Description")));
				}
				return lnode;
			}
		});
		return list;
	}
	@Override
	public ApplicationForm getAppInfo(int appID){
		List<DefaultForm> list = queryList(ApplicationForm.class, new ConditionDefBuilder().param("T_Application.ID=:appID").define(),
				new ParamMap().put("appID", appID));
		if(list.isEmpty()){
			return null;
		}else{
			return (ApplicationForm)list.get(0);
		}
	}
	@Override
	public AppInterForm getInterInfo(int interID){
		List<DefaultForm> list = queryList(AppInterForm.class, new ConditionDefBuilder().param("T_Interface.ID=:interID").define(),
				new ParamMap().put("interID", interID));
		if(list.isEmpty()){
			return null;
		}else{
			return (AppInterForm)list.get(0);
		}
	}
	@Override
	public InterMethodForm getMethodInfo(int methodID){
		List<DefaultForm> list = queryList(InterMethodForm.class, new ConditionDefBuilder().param("T_InterfaceMethod.ID=:methodID").define(),
				new ParamMap().put("methodID", methodID));
		if(list.isEmpty()){
			return null;
		}else{
			return (InterMethodForm)list.get(0);
		}
	}
	@Override
	public DataBaseForm getDbInfo(int dbID){
		List<DefaultForm> list = queryList(DataBaseForm.class, new ConditionDefBuilder().param("T_DataBase.ID=:dbID").define(),
				new ParamMap().put("dbID", dbID));
		if(list.isEmpty()){
			return null;
		}else{
			return (DataBaseForm)list.get(0);
		}
	}
	@Override
	public AppFuncForm getAppFuncInfo(int funcID) {
		List<DefaultForm> list = queryList(AppFuncForm.class, new ConditionDefBuilder().param("T_ApplicationFunction.id=:functionID").define(),
				new ParamMap().put("functionID", funcID));
		if(list.isEmpty()){
			return null;
		}else{
			return (AppFuncForm)list.get(0);
		}
	}
	@Override
	public MethodParamForm getMethodParamInfo(int paramID) {
		List<DefaultForm> list = queryList(MethodParamForm.class, new ConditionDefBuilder().param("T_InterfaceMethodParameter.ID=:paramID").define(),
				new ParamMap().put("paramID", paramID));
		if(list.isEmpty()){
			return null;
		}else{
			return (MethodParamForm)list.get(0);
		}
	}
	@Override
	public ProcedureParamForm getProParamInfo(int paramID) {
		List<DefaultForm> list = queryList(ProcedureParamForm.class, new ConditionDefBuilder().param("T_ObjParam.ID=:paramID").define(),
				new ParamMap().put("paramID", paramID));
		if(list.isEmpty()){
			return null;
		}else{
			return (ProcedureParamForm)list.get(0);
		}
	}
	@Override
	public DataBaseObjForm getDbObjInfo(int objID) {
		List<DefaultForm> list = queryList(DataBaseObjForm.class, new ConditionDefBuilder().param("T_DataBaseObj.ID=:objID").define(),
				new ParamMap().put("objID", objID));
		if(list.isEmpty()){
			return null;
		}else{
			return (DataBaseObjForm)list.get(0);
		}
	}
	@Override
	public DbObjColForm getObjColInfo(int colID) {
		List<DefaultForm> list = queryList(DbObjColForm.class, new ConditionDefBuilder().param("T_DataBaseObjCol.ID=:colID").define(),
				new ParamMap().put("colID", colID));
		if(list.isEmpty()){
			return null;
		}else{
			return (DbObjColForm)list.get(0);
		}
	}
	@Override
	public DictForm getDictInfo(int dictID) {
		List<DefaultForm> list = queryList(DictForm.class, new ConditionDefBuilder().param("T_Dict.ID=:dictID").define(),
				new ParamMap().put("dictID", dictID));
		if(list.isEmpty()){
			return null;
		}else{
			return (DictForm)list.get(0);
		}
	}
	@Override
	public RoleForm getRoleInfo(int roleID) {
		List<DefaultForm> list = queryList(RoleForm.class, new ConditionDefBuilder().param("T_Role.ID=:roleID").define(),
				new ParamMap().put("roleID", roleID));
		if(list.isEmpty()){
			return null;
		}else{
			return (RoleForm)list.get(0);
		}
	}
	
}
