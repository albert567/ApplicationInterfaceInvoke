package com.gwamcc.aii.util.jtopo.impl;

import java.util.ArrayList;
import java.util.List;

import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.forms.DataBaseObjForm;
import com.gwamcc.aii.forms.DbNameForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.TopoNode;
import com.gwamcc.aii.util.jtopo.JTopoSubNodeGetter;
import com.gwamcc.aii.util.jtopo.types.NodeType;
import com.gwamcc.aii.util.sql2.ConditionDef;
import com.gwamcc.aii.util.sql2.ParamMap;
/**
 * 获取应用接口方法的子级(调用方接口方法)
 * @author 范大德
 *
 */
public class JTopoObjectSub implements JTopoSubNodeGetter {

	@Override
	public TopoNode getSub(DefaultDao dao, int id,TopoNode pNode) throws Exception {
		TopoNode node = pNode;
		List<TopoNode> subList = new ArrayList<TopoNode>();
		//对象数据库信息
		DbNameForm info = (DbNameForm) dao.queryEntity(DbNameForm.class,
				new ConditionDef(new Object[][] { { "id=(select databaseId from T_DataBaseObj where ID=:id)" } }), new ParamMap().put("id", id));
		subList.add(new TopoNode().setId(info.getId()).setName(info.getName()).setGroup("所属数据库").setMethod(TopoNode.HIDDEN).setType(NodeType.DATABASE));
		List<DefaultForm> invokeObjs = dao.queryList(DataBaseObjForm.class,new String[]{"dbName","objName"},
				new ConditionDef(new Object[][] {
						{ "T_DataBaseObj.id IN(select DISTINCT srcObjid FROM V_ObjRelation where objid=:id and srcObjid<>:id and srcObjid is not null)" } }),
				new ParamMap().put("id", id));
		for (DefaultForm invokeObj : invokeObjs) {
			if (invokeObj instanceof DataBaseObjForm) {
				DataBaseObjForm obj = (DataBaseObjForm) invokeObj;
				subList.add(new TopoNode().setId(obj.getObjID()).setName(obj.getDbName()+"."+obj.getObjName()).setType(getObjType(obj.getObjTypeID()))
						.setGroup(obj.getObjTypeName())
						.setMethod(TopoNode.NORMAL));
			}
		}
		invokeObjs = dao.queryList(DataBaseObjForm.class,new String[]{"dbName","objName"},
				new ConditionDef(new Object[][] {
						{ "T_DataBaseObj.id IN(select DISTINCT objid FROM V_ObjRelation where srcObjid=:id and objid<>:id and srcObjid is not null)" } }),
				new ParamMap().put("id", id));
		for (DefaultForm invokeObj : invokeObjs) {
			if (invokeObj instanceof DataBaseObjForm) {
				DataBaseObjForm obj = (DataBaseObjForm) invokeObj;
				subList.add(new TopoNode().setId(obj.getObjID()).setName(obj.getDbName()+"."+obj.getObjName()).setType(getObjType(obj.getObjTypeID()))
						.setGroup(obj.getObjTypeName())
						.setMethod(TopoNode.NORMAL));
			}
		}
		node.setSub(subList);
		return node;
	}


	public static NodeType getObjType(String type){
		if(type.equals("0401")){
			return NodeType.EXEC;
		}else if(type.equals("0402")){
			return NodeType.TABLE;
		}else if(type.equals("0403")){
			return NodeType.VIEW;
		}else{
			return NodeType.TABLE;
		}
	}


}
