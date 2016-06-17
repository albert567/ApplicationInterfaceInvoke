package com.gwamcc.aii.util.jtopo.impl;

import java.util.ArrayList;
import java.util.List;

import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.forms.AppNameForm;
import com.gwamcc.aii.forms.DbNameForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.TopoNode;
import com.gwamcc.aii.util.jtopo.JTopoSubNodeGetter;
import com.gwamcc.aii.util.jtopo.anno.JTopoSubType;
import com.gwamcc.aii.util.jtopo.types.NodeType;
import com.gwamcc.aii.util.sql2.ConditionDef;
import com.gwamcc.aii.util.sql2.ParamMap;
import com.gwamcc.aii.util.sql2.Result;
import com.gwamcc.aii.util.sql2.RowMap;

/**
 * 获取应用类型的子级(功能,接口,数据库)
 *
 * @author 范大德
 *
 */
@JTopoSubType(NodeType.APPLICATION)
public class JTopoApplicationSub implements JTopoSubNodeGetter {

	@Override
	public TopoNode getSub(DefaultDao dao, int id, TopoNode pNode) throws Exception {
		// dao.setPrintSql(true);
		TopoNode node = pNode;
		// 查询应用信息
		AppNameForm appInfo = (AppNameForm) dao.queryEntity(AppNameForm.class,
				new ConditionDef(new Object[][] { { "id=:id" } }), new ParamMap().put("id", id));
		node.setId(appInfo.getId());
		node.setName(appInfo.getName());
		node.setType(NodeType.APPLICATION);
		List<TopoNode> subList = new ArrayList<TopoNode>();
		// 应用关联
		List<DefaultForm> invokeApps = dao.queryList(AppNameForm.class, new String[] { "name" },
				new ConditionDef(new Object[][] {
						{ "ID IN(select InvokeApplicationID from T_ApplicationInterfaceInvoke where InterfaceID "
								+ "IN(SELECT ID from T_Interface where ApplicationID=:appId))" } }),
				new ParamMap().put("appId", id));
		for (DefaultForm invokeApp : invokeApps) {
			if (invokeApp instanceof AppNameForm) {
				AppNameForm app = (AppNameForm) invokeApp;
				subList.add(new TopoNode().setId(app.getId()).setName(app.getName()).setType(NodeType.APPLICATION)
						.setGroup("关联应用").setMethod(TopoNode.NORMAL));
			}
		}
		List<DefaultForm> invokeDbApps = dao.queryList(AppNameForm.class, new String[] { "name" },
				new ConditionDef(new Object[][] {
						{ "ID IN(select distinct srcappid from V_ObjRelation where appid=:appId and srcappid<>:appId  and srcappid is not null)" } }),
				new ParamMap().put("appId", id));
		for (DefaultForm invokeApp : invokeDbApps) {
			if (invokeApp instanceof AppNameForm) {
				AppNameForm app = (AppNameForm) invokeApp;
				subList.add(new TopoNode().setId(app.getId()).setName(app.getName()).setType(NodeType.APPLICATION)
						.setGroup("关联应用").setMethod(TopoNode.NORMAL));
			}
		}

		invokeDbApps = dao.queryList(AppNameForm.class, new String[] { "name" },
				new ConditionDef(new Object[][] {
						{ "ID IN(select distinct appid from V_ObjRelation where srcappid=:appId and appid<>:appId  and srcappid is not null)" } }),
				new ParamMap().put("appId", id));
		for (DefaultForm invokeApp : invokeDbApps) {
			if (invokeApp instanceof AppNameForm) {
				AppNameForm app = (AppNameForm) invokeApp;
				subList.add(new TopoNode().setId(app.getId()).setName(app.getName()).setType(NodeType.APPLICATION)
						.setGroup("关联应用").setMethod(TopoNode.NORMAL));
			}
		}

		// 添加直接子级应用功能
		Result funcResult=dao.query(
				"SELECT id,name FROM T_ApplicationFunction where ApplicationID=:app and ParentID=-1",
				new ParamMap().put("app", id));
		while(funcResult.next()){
			RowMap row=funcResult.get();
			subList.add(new TopoNode().setId(row.getInt("id")).setName(row.getString("name")).setType(NodeType.FUNCTION)
					.setGroup("应用功能").setMethod(TopoNode.NORMAL));
		}
		// 数据库
		List<DefaultForm> dbs = dao.queryList(DbNameForm.class, new String[] { "name" },
				new ConditionDef(new Object[][] {
						{ "ID in (select DatabaseId FROM T_AppDataBase where applicationId=:appId)" } }),
				new ParamMap().put("appId", id));
		for (DefaultForm db : dbs) {
			if (db instanceof DbNameForm) {
				DbNameForm d = (DbNameForm) db;
				subList.add(new TopoNode().setId(d.getId()).setName(d.getName()).setType(NodeType.DATABASE)
						.setGroup("数据库").setMethod(TopoNode.NORMAL));
			}
		}

		// 应用子级
		// subList.add(new
		// TopoNode().setId(id).setName("功能").setType(NodeType.APP_FUNCTION).setMethod(TopoNode.HIDDEN));
		// subList.add(new
		// TopoNode().setId(id).setName("接口").setType(NodeType.APP_INTERFACE));
		// subList.add(new
		// TopoNode().setId(id).setName("数据库").setType(NodeType.APP_DATABASE));
		node.setSub(subList);
		return node;
	}

}
