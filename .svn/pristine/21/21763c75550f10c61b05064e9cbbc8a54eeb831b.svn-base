package com.gwamcc.aii.util.jtopo.impl;

import java.util.ArrayList;
import java.util.List;

import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.forms.DataBaseObjForm;
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
 * 获取功能子功能
 *
 * @author 范大德
 *
 */
@JTopoSubType(NodeType.FUNCTION)
public class JTopoFunctionSub implements JTopoSubNodeGetter {

	@Override
	public TopoNode getSub(DefaultDao dao, int id, TopoNode pNode) throws Exception {
		TopoNode node = pNode;
		List<TopoNode> subList = new ArrayList<TopoNode>();
		// 添加直接子级应用功能
		Result funcResult = dao.query("SELECT id,name FROM T_ApplicationFunction where ParentID=:id",
				new ParamMap().put("id", id));
		while (funcResult.next()) {
			RowMap row = funcResult.get();
			subList.add(new TopoNode().setId(row.getInt("id")).setName(row.getString("name")).setType(NodeType.FUNCTION)
					.setGroup("子功能").setMethod(TopoNode.NORMAL));
		}
		/*
		//添加应用功能对应的源代码
		Result srcResult = dao.query("SELECT * FROM(SELECT ID,Name,"
				+ "case when(select COUNT(*)from T_CodeStructure A where A.ParentID=T_CodeStructure.id)=0 THEN 1 ELSE 0 END isFile"
				+ " from T_CodeStructure where Id IN(select codeid from T_appfuncCode where funcID=:id))A where isFile=1",
				new ParamMap().put("id", id));
		while (srcResult.next()) {
			RowMap row = srcResult.get();
			subList.add(new TopoNode().setId(row.getInt("id")).setName(row.getString("name")).setType(row.getBoolean("isFile")?NodeType.SOURCE_FILE:NodeType.SOURCE_DIR)
					.setGroup("源代码").setMethod(TopoNode.NORMAL));
		}*/
		// 添加应用功能对应的数据库对象
		List<DefaultForm> interList = dao.queryList(DataBaseObjForm.class, new String[] { "T_DataBaseObj.objname" },
				new ConditionDef(new Object[][] { { "T_DataBaseObj.ID IN(select ObjID FROM T_AppFuncObj where FuncID=:id)" } }),
				new ParamMap().put("id", id));
		for (DefaultForm def : interList) {
			if (def instanceof DataBaseObjForm) {
				DataBaseObjForm name = (DataBaseObjForm) def;
				subList.add(new TopoNode().setId(name.getObjID()).setName(name.getDbName()+"."+name.getObjName())
						.setType(JTopoObjectSub.getObjType(name.getObjTypeID())).setGroup(name.getObjTypeName())
						.setMethod(TopoNode.NORMAL));
			}
		}
		node.setSub(subList);
		return node;
	}

}
