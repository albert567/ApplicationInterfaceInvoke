package com.gwamcc.aii.dao.impl;

import java.util.Collection;
import java.util.List;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.gwamcc.aii.dao.SearchDao;
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
import com.gwamcc.aii.forms.LoginUser;
import com.gwamcc.aii.forms.MethodParamForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.ProcedureParamForm;
import com.gwamcc.aii.forms.RoleForm;
import com.gwamcc.aii.forms.SearchForm;
import com.gwamcc.aii.util.EscapeUnescape;
import com.gwamcc.aii.util.StrKit;
import com.gwamcc.aii.util.sql2.ConditionDefBuilder;
import com.gwamcc.aii.util.sql2.LikeParam;
import com.gwamcc.aii.util.sql2.LikeSpliceType;
import com.gwamcc.aii.util.sql2.LikeType;
import com.gwamcc.aii.util.sql2.ParamMap;

@Repository
public class SearchDaoImpl extends DefaultDao implements SearchDao{


	@Override
	public PageDataForm getList(SearchForm form, PageForm page){
		ConditionDefBuilder cond = new ConditionDefBuilder();
		ParamMap map = new ParamMap();
		LoginUser user=(LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<Integer>menus=user.getMenus();

		cond.param("V_Search.MenuID in (:menuID)");
		map.put("menuID", menus.toArray(new Integer[0]));

		if(!StrKit.isEmpty(form.getDescription())){
			String[] strs = form.getDescription().trim().split(" ");
			cond.param("V_Search.Description like :desc");
			map.put("desc", LikeParam.toArray(strs, LikeType.OR, LikeSpliceType.START_END));
		}

		try {
			PageDataForm pdfPage= queryPage(SearchForm.class, page, new String[] { "TableID" }, cond.define(), map);
			for(DefaultForm dform:pdfPage.getRows()){
				SearchForm sform = (SearchForm)dform;
				setUrl(sform);
			}
			return pdfPage;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private void setUrl(SearchForm form){
		String tableName = form.getTableName();
		int id = form.getId();
		String description = form.getDescription();
		String url = null;
		ApplicationForm appForm = null;
		AppFuncForm funcForm = null;
		AppInterForm interForm = null;
		InterMethodForm methodForm = null;
		MethodParamForm methodParamForm = null;
		ProcedureParamForm proParamForm = null;
		DataBaseForm dbForm = null;
		DataBaseObjForm objForm = null;
		DbObjColForm colForm = null;

		switch (tableName) {
		case "T_Application":
			appForm = getAppInfo(id);
			/*url = "AppInterface.html?appId="+id+"&appName="
					+ EscapeUnescape.escape(appForm.getName())
					+"&appTypeName="+EscapeUnescape.escape(appForm.getAppTypeName())
					+"&description="+EscapeUnescape.escape(appForm.getDescription());*/
			url = "AppFunction.html?appId="+id+"&appName="
					+ EscapeUnescape.escape(appForm.getName())
					+"&parentID=-1"
					+"&appTypeName="+EscapeUnescape.escape(appForm.getAppTypeName())
					+"&description="+EscapeUnescape.escape(appForm.getDescription());
			form.setType("应用系统");
			form.setSrc(form.getName());
			break;
		case "T_ApplicationFunction":
			funcForm = getAppFuncInfo(id);
			appForm = getAppInfo(funcForm.getAppID());
			url = "AppFunction.html?appId="+funcForm.getAppID()+"&appName="+ EscapeUnescape.escape(funcForm.getAppName())
			+"&appTypeName="+EscapeUnescape.escape(appForm.getAppTypeName())
			+"&description="+EscapeUnescape.escape(appForm.getDescription())
			+"&parentID="+funcForm.getId();
			form.setType("应用功能");
			form.setSrc(appForm.getName()+"/"+funcForm.getName());
			break;
		case "T_Attachment.function":
			funcForm = getAppFuncInfo(id);
			appForm = getAppInfo(funcForm.getAppID());
			url = "AppFunction.html?appId="+funcForm.getAppID()+"&appName="+ EscapeUnescape.escape(funcForm.getAppName())
			+"&appTypeName="+EscapeUnescape.escape(appForm.getAppTypeName())
			+"&description="+EscapeUnescape.escape(appForm.getDescription())
			+"&parentID="+id;
			form.setType(description.substring(0,description.indexOf('_')));
			form.setSrc(appForm.getName()+"/"+funcForm.getName());
			break;
		case "T_Interface":
			interForm = getInterInfo(id);
			if(LeftMenuDaoImpl.methodInterType.equals(interForm.getInterfaceTypeID())){
				url = "InterfaceMethod.html?appId="+interForm.getAppID()
						 +"&interId="+interForm.getInterID()
						 +"&appName="+ EscapeUnescape.escape(interForm.getAppName())
						 +"&interName="+EscapeUnescape.escape(interForm.getInterName())
						 +"&interTypeName="+EscapeUnescape.escape(interForm.getInterfaceType())
						 +"&description="+EscapeUnescape.escape(interForm.getDescription());
			}else if(LeftMenuDaoImpl.dbInterType.equals(interForm.getInterfaceTypeID())){
				url = "InterProcedure.html?appId="+interForm.getAppID()
						+"&interId="+interForm.getInterID()
						 +"&appName="+ EscapeUnescape.escape(interForm.getAppName())
						 +"&interName="+EscapeUnescape.escape(interForm.getInterName())
						 +"&interTypeName="+EscapeUnescape.escape(interForm.getInterfaceType())
						 +"&description="+EscapeUnescape.escape(interForm.getDescription());
			}
			form.setType("应用接口");
			form.setSrc(interForm.getAppName()+"/"+interForm.getInterName());
			break;
		case "T_InterfaceMethod":
			methodForm = getMethodInfo(id);
			url = "MethodParam.html?appId="+methodForm.getAppID()
			 +"&interId="+methodForm.getInterID()+"&method="+methodForm.getMethodID()
			 +"&appName="+ EscapeUnescape.escape(methodForm.getAppName())
			 +"&interName="+EscapeUnescape.escape(methodForm.getInterName())
			 +"&methodName="+EscapeUnescape.escape(methodForm.getMethodName())
			 +"&methodTypeName="+EscapeUnescape.escape(methodForm.getMethodTypeName())
			 +"&description="+EscapeUnescape.escape(methodForm.getDescription());
			form.setType("接口方法");
			form.setSrc(methodForm.getAppName()+"/"+methodForm.getInterName()+"/"+methodForm.getMethodName());
			break;
		case "T_InterfaceMethodParameter":
			methodParamForm = getMethodParamInfo(id);
			url = "MethodParam.html?appId="+methodParamForm.getAppID()
			 +"&interId="+methodParamForm.getInterID()+"&method="+methodParamForm.getMethodID()
			 +"&appName="+ EscapeUnescape.escape(methodParamForm.getAppName())
			 +"&interName="+EscapeUnescape.escape(methodParamForm.getInterName())
			 +"&methodName="+EscapeUnescape.escape(methodParamForm.getMethodName())
			 +"&methodTypeName="+EscapeUnescape.escape(methodParamForm.getMethodTypeName())
			 +"&description="+EscapeUnescape.escape(methodParamForm.getDescription())
			 +"&paramId="+methodParamForm.getParamID();
			form.setType("接口方法参数");
			form.setSrc(methodParamForm.getAppName()+"/"+methodParamForm.getInterName()
				+"/"+methodParamForm.getMethodName()+"/"+methodParamForm.getParamName());
			break;
		case "T_ObjParam":
			proParamForm = getProParamInfo(id);
			objForm = getDbObjInfo(proParamForm.getObjID());
			url = "ProcedureParam.html?dbID="+objForm.getDbID()
			 +"&objID="+objForm.getObjID()
			 +"&dbName="+ EscapeUnescape.escape(objForm.getDbName())
			 +"&objName="+EscapeUnescape.escape(objForm.getObjName())
			 +"&objType="+EscapeUnescape.escape(objForm.getObjTypeName())
			 +"&description="+EscapeUnescape.escape(objForm.getDescription())
			 +"&paramId="+proParamForm.getId();
			form.setType("存储过程参数");
			form.setSrc(objForm.getDbName()+"/"+objForm.getObjName()+"/"+proParamForm.getName());
			break;
		case "T_DataBase":
			dbForm = getDbInfo(id);
			url = "DataBaseObj.html?dbID="+dbForm.getId()
			 +"&dbName="+EscapeUnescape.escape(dbForm.getName())
			 +"&dbEn="+ EscapeUnescape.escape(dbForm.getNameEn())
			 +"&description="+EscapeUnescape.escape(dbForm.getDescription());
			form.setType("数据库");
			form.setSrc(dbForm.getName());
			break;
		case "T_DataBaseObj":
			objForm = getDbObjInfo(id);
			if(LeftMenuDaoImpl.procedureType.equals(objForm.getObjTypeID())){
				url = "ProcedureParam.html?dbID="+objForm.getDbID()
				 +"&objID="+objForm.getObjID()
				 +"&dbName="+EscapeUnescape.escape(objForm.getDbName())
				 +"&objName="+EscapeUnescape.escape(objForm.getObjName())
				 +"&objType="+ EscapeUnescape.escape(objForm.getObjTypeName())
				 +"&description="+EscapeUnescape.escape(objForm.getDescription());
			}else{
				url = "DataBaseObjCol.html?dbID="+objForm.getDbID()
				 +"&objID="+objForm.getObjID()
				 +"&dbName="+EscapeUnescape.escape(objForm.getDbName())
				 +"&objName="+EscapeUnescape.escape(objForm.getObjName())
				 +"&objTypeID="+objForm.getObjTypeID()
				 +"&objType="+ EscapeUnescape.escape(objForm.getObjTypeName())
				 +"&description="+EscapeUnescape.escape(objForm.getDescription());
			}
			form.setType("数据库对象");
			form.setSrc(objForm.getDbName()+"/"+objForm.getObjName());
			break;
		case "T_DataBaseObjCol":
				colForm = getObjColInfo(id);
				objForm = getDbObjInfo(colForm.getObjID());
				url = "DataBaseObjCol.html?dbID="+objForm.getDbID()
				 +"&objID="+objForm.getObjID()
				 +"&dbName="+EscapeUnescape.escape(objForm.getDbName())
				 +"&objName="+EscapeUnescape.escape(objForm.getObjName())
				 +"&objTypeID="+objForm.getObjTypeID()
				 +"&objType="+ EscapeUnescape.escape(objForm.getObjTypeName())
				 +"&description="+EscapeUnescape.escape(objForm.getDescription())
				 +"colId="+colForm.getColID();
				form.setType("数据库对象字段");
				form.setSrc(objForm.getDbName()+"/"+objForm.getObjName()+"/"+colForm.getColumnName());
			break;
		case "T_Dict":
			//dictForm = lmdao.getDictInfo(id);
			url = "Dict.html";
			form.setType("数据字典");
			form.setSrc("数据字典");
			break;
		case "T_Role":
			//roleForm = lmdao.getRoleInfo(id);
			url = "Role.html";
			form.setType("系统角色");
			form.setSrc("系统角色");
			break;
		default:
			url = "#";
			break;
		}
		form.setUrl(url);
	}

	public ApplicationForm getAppInfo(int appID){
		List<DefaultForm> list = queryList(ApplicationForm.class, new ConditionDefBuilder().param("T_Application.ID=:appID").define(),
				new ParamMap().put("appID", appID));
		if(list.isEmpty()){
			return null;
		}else{
			return (ApplicationForm)list.get(0);
		}
	}
	public AppInterForm getInterInfo(int interID){
		List<DefaultForm> list = queryList(AppInterForm.class, new ConditionDefBuilder().param("T_Interface.ID=:interID").define(),
				new ParamMap().put("interID", interID));
		if(list.isEmpty()){
			return null;
		}else{
			return (AppInterForm)list.get(0);
		}
	}
	public InterMethodForm getMethodInfo(int methodID){
		List<DefaultForm> list = queryList(InterMethodForm.class, new ConditionDefBuilder().param("T_InterfaceMethod.ID=:methodID").define(),
				new ParamMap().put("methodID", methodID));
		if(list.isEmpty()){
			return null;
		}else{
			return (InterMethodForm)list.get(0);
		}
	}
	public DataBaseForm getDbInfo(int dbID){
		List<DefaultForm> list = queryList(DataBaseForm.class, new ConditionDefBuilder().param("T_DataBase.ID=:dbID").define(),
				new ParamMap().put("dbID", dbID));
		if(list.isEmpty()){
			return null;
		}else{
			return (DataBaseForm)list.get(0);
		}
	}
	public AppFuncForm getAppFuncInfo(int funcID) {
		List<DefaultForm> list = queryList(AppFuncForm.class, new ConditionDefBuilder().param("T_ApplicationFunction.id=:functionID").define(),
				new ParamMap().put("functionID", funcID));
		if(list.isEmpty()){
			return null;
		}else{
			return (AppFuncForm)list.get(0);
		}
	}
	public MethodParamForm getMethodParamInfo(int paramID) {
		List<DefaultForm> list = queryList(MethodParamForm.class, new ConditionDefBuilder().param("T_InterfaceMethodParameter.ID=:paramID").define(),
				new ParamMap().put("paramID", paramID));
		if(list.isEmpty()){
			return null;
		}else{
			return (MethodParamForm)list.get(0);
		}
	}
	public ProcedureParamForm getProParamInfo(int paramID) {
		List<DefaultForm> list = queryList(ProcedureParamForm.class, new ConditionDefBuilder().param("T_ObjParam.ID=:paramID").define(),
				new ParamMap().put("paramID", paramID));
		if(list.isEmpty()){
			return null;
		}else{
			return (ProcedureParamForm)list.get(0);
		}
	}
	public DataBaseObjForm getDbObjInfo(int objID) {
		List<DefaultForm> list = queryList(DataBaseObjForm.class, new ConditionDefBuilder().param("T_DataBaseObj.ID=:objID").define(),
				new ParamMap().put("objID", objID));
		if(list.isEmpty()){
			return null;
		}else{
			return (DataBaseObjForm)list.get(0);
		}
	}
	public DbObjColForm getObjColInfo(int colID) {
		List<DefaultForm> list = queryList(DbObjColForm.class, new ConditionDefBuilder().param("T_DataBaseObjCol.ID=:colID").define(),
				new ParamMap().put("colID", colID));
		if(list.isEmpty()){
			return null;
		}else{
			return (DbObjColForm)list.get(0);
		}
	}
	public DictForm getDictInfo(int dictID) {
		List<DefaultForm> list = queryList(DictForm.class, new ConditionDefBuilder().param("T_Dict.ID=:dictID").define(),
				new ParamMap().put("dictID", dictID));
		if(list.isEmpty()){
			return null;
		}else{
			return (DictForm)list.get(0);
		}
	}
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
