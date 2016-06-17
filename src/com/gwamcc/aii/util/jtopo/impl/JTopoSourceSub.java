package com.gwamcc.aii.util.jtopo.impl;

import java.util.ArrayList;
import java.util.List;

import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.forms.TopoNode;
import com.gwamcc.aii.util.jtopo.JTopoSubNodeGetter;
import com.gwamcc.aii.util.jtopo.anno.JTopoSubType;
import com.gwamcc.aii.util.jtopo.types.NodeType;
import com.gwamcc.aii.util.sql2.ParamMap;
import com.gwamcc.aii.util.sql2.Result;
import com.gwamcc.aii.util.sql2.RowMap;

/**
 * 获取源码包下源码
 *
 * @author 范大德
 *
 */
@Deprecated
@JTopoSubType(NodeType.SOURCE_DIR)
public class JTopoSourceSub implements JTopoSubNodeGetter {

	@Override
	public TopoNode getSub(DefaultDao dao, int id, TopoNode pNode) throws Exception {
		TopoNode node = pNode;
		List<TopoNode> subList = new ArrayList<TopoNode>();

		//添加应用功能对应的源代码
		Result srcResult = dao.query("SELECT ID,Name,"
				+ "case when(select COUNT(*)from T_CodeStructure A where A.ParentID=T_CodeStructure.id)=0 THEN 1 ELSE 0 END isFile"
				+ " from T_CodeStructure where parentId=:id",
				new ParamMap().put("id", id));
		while (srcResult.next()) {
			RowMap row = srcResult.get();
			subList.add(new TopoNode().setId(row.getInt("id")).setName(row.getString("name")).setType(row.getBoolean("isFile")?NodeType.SOURCE_FILE:NodeType.SOURCE_DIR)
					.setGroup("源代码").setMethod(TopoNode.NORMAL));
		}

		node.setSub(subList);
		return node;
	}

}
