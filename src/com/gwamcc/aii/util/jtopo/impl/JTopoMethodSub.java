package com.gwamcc.aii.util.jtopo.impl;

import java.util.ArrayList;
import java.util.List;

import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.forms.AppNameForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.InterNameForm;
import com.gwamcc.aii.forms.TopoNode;
import com.gwamcc.aii.forms.MethodNameForm;
import com.gwamcc.aii.util.jtopo.JTopoSubNodeGetter;
import com.gwamcc.aii.util.jtopo.anno.JTopoSubType;
import com.gwamcc.aii.util.jtopo.types.NodeType;
import com.gwamcc.aii.util.sql2.ConditionDef;
import com.gwamcc.aii.util.sql2.ParamMap;
/**
 * 获取应用接口方法的子级(调用方接口方法)
 * @author 范大德
 *
 */
@JTopoSubType(NodeType.METHOD)
public class JTopoMethodSub implements JTopoSubNodeGetter {

	@Override
	public TopoNode getSub(DefaultDao dao, int id,TopoNode pNode) throws Exception {
		TopoNode node = pNode;
		List<TopoNode> subList = new ArrayList<TopoNode>();
		List<DefaultForm> methodList = dao.queryList(MethodNameForm.class,new String[]{"name"},
				new ConditionDef(new Object[][] {
						{ "id in (select invokemethodid from T_ApplicationInterfaceInvoke where interfacemethodid=:id)" },
						{ "isValid=:isValid" } }),
				new ParamMap().put("id", id).put("isValid", 1));
		for (DefaultForm def : methodList) {
			if (def instanceof MethodNameForm) {
				MethodNameForm method = (MethodNameForm) def;
				List<TopoNode> parentList = new ArrayList<TopoNode>();
				//查询调用当前节点的方法是否存在其他方法的调用
				List<DefaultForm> methodList2 = dao.queryList(MethodNameForm.class,new String[]{"name"},
						new ConditionDef(new Object[][] { { "id in (select InterfaceMethodID from T_ApplicationInterfaceInvoke where invokemethodid=:method)" }
						,{" InterfaceID=(SELECT ID from T_Interfacemethod where ID=:method)"}
						,{"id <> :id"}
						, { "isValid=:isValid" } }),
						new ParamMap().put("id", id).put("method", method.getId()).put("isValid", 1));
				for (DefaultForm def2 : methodList2) {
					if (def2 instanceof MethodNameForm) {
						MethodNameForm method2 = (MethodNameForm) def2;
						parentList.add(new TopoNode().setId(method2.getId()).setName(method2.getName())
								.setType(NodeType.METHOD));
					}
				}
				//查询调用方方法所在的接口
				List<DefaultForm> interList = dao.queryList(InterNameForm.class,new String[]{"name"},
						new ConditionDef(new Object[][] { { "id in (select interfaceid from T_InterfaceMethod where id=:method)" }
						, { "isValid=:isValid" } }),
						new ParamMap().put("method", method.getId()).put("isValid", 1));
				for (DefaultForm def2 : interList) {
					if (def2 instanceof InterNameForm) {
						InterNameForm inter = (InterNameForm) def2;
						//查询调用方所在接口所属应用
						List<TopoNode> parentAppList = new ArrayList<TopoNode>();
						List<DefaultForm> appList = dao.queryList(AppNameForm.class,new String[]{"name"},
								new ConditionDef(new Object[][] { { "id in (select applicationID from T_Interface where id=:inter)" }
								, { "isValid=:isValid" } }),
								new ParamMap().put("inter", inter.getId()).put("isValid", 1));
						for (DefaultForm def3 : appList) {
							if (def3 instanceof AppNameForm) {
								AppNameForm app = (AppNameForm) def3;
								parentAppList.add(new TopoNode().setId(app.getId()).setName(app.getName())
										//.setTo(LinkType.LINK_APPLICATION_INTERFACE.create(NodeType.INTERFACE,inter.getId()))
										.setType(NodeType.APPLICATION));
							}
						}
					}
				}
				subList.add(new TopoNode().setId(method.getId()).setName(method.getName())
						//.setFrom(LinkType.LINK_INVOKE_METHOD_METHOD.create(NodeType.METHOD, id))
						.setType(NodeType.METHOD));
			}
		}
		node.setSub(subList);
		return node;
	}
	/*
	List<DefaultForm> appList = dao.queryList(AppNameForm.class,
			new ConditionDef(new Object[][] {
					{ "id in (select invokeapplicationid from T_ApplicationInterfaceInvoke where interfacemethodid=:id)" },
					{ "isValid=:isValid" } }),
			new ParamMap().put("id", id).put("isValid", 1));

	for (DefaultForm def : appList) {
		if (def instanceof AppNameForm) {
			AppNameForm app = (AppNameForm) def;
			List<DefaultForm> methodList = dao.queryList(MethodNameForm.class,
					new ConditionDef(new Object[][] { { "id in (select interfacemethodid from T_ApplicationInterfaceInvoke where invokeapplicationid=:app)" }
					,{"id <> :id and interfaceid=(select interfaceid from T_interfacemethod where id=:id)"}
					, { "isValid=:isValid" } }),
					new ParamMap().put("id", id).put("app", app.getId()).put("isValid", 1));
			System.out.println(app.getId());
			List<JTopoNode> parentList = new ArrayList<JTopoNode>();
			for (DefaultForm def2 : methodList) {
				if (def2 instanceof MethodNameForm) {
					MethodNameForm method = (MethodNameForm) def2;
					parentList.add(new JTopoNode().setId(method.getId()).setName(method.getName())
							.setLinkType(LinkType.LINK_INTERFACE_METHOD).setType(NodeType.METHOD));
				}
			}

			subList.add(new JTopoNode().setId(app.getId()).setName(app.getName())
					.setLinkType(LinkType.LINK_INVOKE_METHOD_APPLICATION).setType(NodeType.APPLICATION).setParent(parentList));
		}
	}
	*/

}
