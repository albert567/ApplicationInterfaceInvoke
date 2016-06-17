package com.gwamcc.aii.util.jtopo.impl;

import java.util.ArrayList;
import java.util.List;

import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.forms.TopoNode;
import com.gwamcc.aii.util.jtopo.JTopoSubNodeGetter;
import com.gwamcc.aii.util.jtopo.anno.JTopoSubType;
import com.gwamcc.aii.util.jtopo.types.NodeType;
/**
 * 应用接口分类
 * @author 范大德
 *
 */
@JTopoSubType(NodeType.APP_INTERFACE)
public class JTopoAppInterfaceSub implements JTopoSubNodeGetter {

	@Override
	public TopoNode getSub(DefaultDao dao, int id,TopoNode pNode) throws Exception {
		TopoNode node = pNode;
		List<TopoNode> subList = new ArrayList<TopoNode>();
		subList.add(new TopoNode().setId(Integer.parseInt(id+"201")).setName("WebService").setGroup("接口类别").setType(NodeType.INTERFACES));
		subList.add(new TopoNode().setId(Integer.parseInt(id+"202")).setName("数据库访问").setGroup("接口类别").setType(NodeType.INTERFACES));
		return node.setSub(subList);
	}

}
