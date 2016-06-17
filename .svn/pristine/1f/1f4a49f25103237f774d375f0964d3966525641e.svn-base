package com.gwamcc.aii.util.jtopo.impl;

import java.util.ArrayList;
import java.util.List;

import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.TopoNode;
import com.gwamcc.aii.forms.MethodNameForm;
import com.gwamcc.aii.util.jtopo.JTopoSubNodeGetter;
import com.gwamcc.aii.util.jtopo.anno.JTopoSubType;
import com.gwamcc.aii.util.jtopo.types.NodeType;
import com.gwamcc.aii.util.sql2.ConditionDef;
import com.gwamcc.aii.util.sql2.ParamMap;
/**
 * 获取接口的子级(接口方法)
 * @author 范大德
 *
 */
@JTopoSubType(NodeType.INTERFACE)
public class JTopoInterfaceSub implements JTopoSubNodeGetter {

	@Override
	public TopoNode getSub(DefaultDao dao, int id,TopoNode pNode) throws Exception {
		TopoNode node =pNode;
		List<DefaultForm> methodList = dao.queryList(MethodNameForm.class,new String[]{"name"},
				new ConditionDef(new Object[][] { { "interfaceID=:id" }, { "isValid=:isValid" } }),
				new ParamMap().put("id", id).put("isValid", 1));
		List<TopoNode> subList = new ArrayList<TopoNode>();
		for (DefaultForm def : methodList) {
			if (def instanceof MethodNameForm) {
				MethodNameForm method = (MethodNameForm) def;
				subList.add(new TopoNode().setId(method.getId()).setName(method.getName())
						.setGroup("接口方法")
						.setType(NodeType.METHOD));
			}
		}
		node.setSub(subList);
		return node;
	}

}
