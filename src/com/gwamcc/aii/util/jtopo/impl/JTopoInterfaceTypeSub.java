package com.gwamcc.aii.util.jtopo.impl;

import java.util.ArrayList;
import java.util.List;

import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.InterNameForm;
import com.gwamcc.aii.forms.TopoNode;
import com.gwamcc.aii.util.jtopo.JTopoSubNodeGetter;
import com.gwamcc.aii.util.jtopo.anno.JTopoSubType;
import com.gwamcc.aii.util.jtopo.types.NodeType;
import com.gwamcc.aii.util.sql2.ConditionDef;
import com.gwamcc.aii.util.sql2.ParamMap;
/**
 * 应用接口分类
 * @author 范大德
 *
 */
@JTopoSubType(NodeType.INTERFACES)
public class JTopoInterfaceTypeSub implements JTopoSubNodeGetter {

	@Override
	public TopoNode getSub(DefaultDao dao, int id,TopoNode pNode) throws Exception {
		String type=id+"";
		int interType= Integer.parseInt(type.substring(type.length()-3));
		id=Integer.parseInt(type.substring(0,type.length()-3));
		List<TopoNode>subList=new ArrayList<TopoNode>();
		// 查询应用下的接口信息
		List<DefaultForm> interList = dao.queryList(InterNameForm.class,new String[]{"name"},
				new ConditionDef(new Object[][] { { "applicationId=:id" }, { "isValid=:isValid" },{"InterfaceTypeID=:InterType"} }),
				new ParamMap().put("id", id).put("isValid", 1).put("InterType",interType));
		for (DefaultForm def : interList) {
			if (def instanceof InterNameForm) {
				InterNameForm inter = (InterNameForm) def;
				subList.add(new TopoNode().setId(inter.getId()).setName(inter.getName())
						.setGroup("应用接口")
						.setType(NodeType.INTERFACE));
			}
		}
		return pNode.setSub(subList);
	}
}
