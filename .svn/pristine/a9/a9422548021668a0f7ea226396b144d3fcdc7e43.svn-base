package com.gwamcc.aii.util.jtopo.impl;

import java.util.ArrayList;
import java.util.List;

import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.forms.DataBaseObjForm;
import com.gwamcc.aii.forms.DbNameForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.TopoNode;
import com.gwamcc.aii.util.jtopo.JTopoSubNodeGetter;
import com.gwamcc.aii.util.jtopo.anno.JTopoSubType;
import com.gwamcc.aii.util.jtopo.types.NodeType;
import com.gwamcc.aii.util.sql2.ConditionDef;
import com.gwamcc.aii.util.sql2.ParamMap;
/**
 * 获取数据库的子级(数据库对象)
 * @author 范大德
 *
 */
@JTopoSubType(NodeType.DATABASE)
public class JTopoDatabaseSub implements JTopoSubNodeGetter {

	@Override
	public TopoNode getSub(DefaultDao dao, int id,TopoNode pNode) throws Exception {
		TopoNode node = pNode;
		DbNameForm info = (DbNameForm) dao.queryEntity(DbNameForm.class,
				new ConditionDef(new Object[][] { { "id=:id" } }), new ParamMap().put("id", id));
		node.setName(info.getName());
		List<TopoNode> subList = new ArrayList<TopoNode>();
		List<DefaultForm> invokeDbs = dao.queryList(DbNameForm.class,new String[]{"name"},
				new ConditionDef(new Object[][] {
						{ "ID IN(select DISTINCT srcDbid FROM V_ObjRelation where dbid=:dbId and srcDbid<>:dbId  and srcDbid is not null)" } }),
				new ParamMap().put("dbId", id));
		for (DefaultForm invokeDb : invokeDbs) {
			if (invokeDb instanceof DbNameForm) {
				DbNameForm db = (DbNameForm) invokeDb;
				subList.add(new TopoNode().setId(db.getId()).setName(db.getName()).setType(NodeType.DATABASE)
						.setGroup("关联数据库")
						.setMethod(TopoNode.NORMAL));
			}
		}

		invokeDbs = dao.queryList(DbNameForm.class,new String[]{"name"},
				new ConditionDef(new Object[][] {
						{ "ID IN(select DISTINCT dbid FROM V_ObjRelation where  srcDbid=:dbId and dbid<>:dbId  and srcDbid is not null)" } }),
				new ParamMap().put("dbId", id));
		for (DefaultForm invokeDb : invokeDbs) {
			if (invokeDb instanceof DbNameForm) {
				DbNameForm db = (DbNameForm) invokeDb;
				subList.add(new TopoNode().setId(db.getId()).setName(db.getName()).setType(NodeType.DATABASE)
						.setGroup("关联数据库")
						.setMethod(TopoNode.NORMAL));
			}
		}

		List<DefaultForm> interList = dao.queryList(DataBaseObjForm.class,new String[]{"T_DataBaseObj.objname"},
				new ConditionDef(new Object[][] { { "T_DataBaseObj.databaseid=:id" }, { "T_DataBaseObj.isValid=:isValid" } }),
				new ParamMap().put("id", id).put("isValid", 1));
		for (DefaultForm def : interList) {
			if (def instanceof DataBaseObjForm) {
				DataBaseObjForm name = (DataBaseObjForm) def;
				subList.add(new TopoNode().setId(name.getObjID()).setName(name.getObjName()).setType(JTopoObjectSub.getObjType(name.getObjTypeID())).setGroup(name.getObjTypeName()).setMethod(TopoNode.HIDDEN));
			}
		}
		node.setSub(subList);
		return node;
	}

}
