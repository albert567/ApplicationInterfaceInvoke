package com.gwamcc.aii.util.jtopo.impl;

import java.util.ArrayList;
import java.util.List;

import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.forms.AppNameForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.TopoNode;
import com.gwamcc.aii.util.jtopo.JTopoSubNodeGetter;
import com.gwamcc.aii.util.jtopo.anno.JTopoSubType;
import com.gwamcc.aii.util.jtopo.types.NodeType;
/**
 * 获取所有应用系统
 * @author 范大德
 *
 */
@JTopoSubType(NodeType.APPLICATIONS)
public class JTopoAppsSub implements JTopoSubNodeGetter {

	@Override
	public TopoNode getSub(DefaultDao dao, int id,TopoNode pNode) throws Exception {
		TopoNode node = pNode;
		node.setName("应用系统");
		node.setMethod(TopoNode.HIDDEN_SRC);
		List<TopoNode> subList = new ArrayList<TopoNode>();
		for (DefaultForm def : dao.queryList(AppNameForm.class,new String[]{"name"})) {
			if (def instanceof AppNameForm) {
				AppNameForm form = (AppNameForm) def;
				subList.add(new TopoNode().setId(form.getId()).setName(form.getName()).setType(NodeType.APPLICATION)
						.setGroup("应用程序")
						.setMethod(TopoNode.REDRAW)
						);
			}
		}
		node.setSub(subList);
		return node;
	}

}
