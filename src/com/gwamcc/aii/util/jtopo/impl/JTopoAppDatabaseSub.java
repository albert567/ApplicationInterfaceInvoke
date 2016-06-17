package com.gwamcc.aii.util.jtopo.impl;

import java.util.ArrayList;
import java.util.List;

import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.forms.DbNameForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.TopoNode;
import com.gwamcc.aii.util.jtopo.JTopoSubNodeGetter;
import com.gwamcc.aii.util.jtopo.anno.JTopoSubType;
import com.gwamcc.aii.util.jtopo.types.NodeType;
import com.gwamcc.aii.util.sql2.ConditionDef;
import com.gwamcc.aii.util.sql2.ParamMap;
/**
 * 获取应用所属数据库
 * @author 范大德
 *
 */
@JTopoSubType(NodeType.APP_DATABASE)
public class JTopoAppDatabaseSub implements JTopoSubNodeGetter {

	@Override
	public TopoNode getSub(DefaultDao dao, int id,TopoNode pNode) throws Exception {
		TopoNode node = pNode;
		List<TopoNode> subList = new ArrayList<TopoNode>();
		List<DefaultForm> dbList = dao.queryList(DbNameForm.class,new String[]{"name"},
				new ConditionDef(new Object[][] { { "id=(select databaseid from T_Application where  id=:id)" }, { "isValid=:isValid" } }),
				new ParamMap().put("id", id).put("isValid", 1));
		for (DefaultForm def : dbList) {
			if (def instanceof DbNameForm) {
				DbNameForm func = (DbNameForm) def;
				subList.add(new TopoNode().setId(func.getId()).setName(func.getName()).setGroup("数据库").setType(NodeType.DATABASE));
			}
		}
		node.setSub(subList);
		return node;
	}

}
