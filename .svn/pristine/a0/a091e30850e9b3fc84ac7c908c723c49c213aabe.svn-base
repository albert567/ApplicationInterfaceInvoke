package com.gwamcc.aii.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.gwamcc.aii.dao.TreeDao;
import com.gwamcc.aii.forms.LoginUser;
import com.gwamcc.aii.forms.TreeForm;

import config.Res;

/**
 * 导航树数据操作实现类
 *
 * @author 范大德
 *
 */
@Repository
public class TreeDaoImpl implements TreeDao {

	@Override
	public List<TreeForm> getSubList(int id) {
		List<TreeForm> list = new ArrayList<TreeForm>();
		LoginUser user=(LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<Integer>menus=user.getMenus();

		switch ((id)) {
		case Res.MENU.ROOT:
			if(menus.contains(Res.MENU.APPLICATION)||menus.contains(Res.MENU.DATABASE))
				list.add(new TreeForm().setId(Res.MENU.MENU).setText("系统导航").setIconCls("icon-Extension-2012081511767")
					.setSelected(true));
			if(menus.contains(Res.MENU.DICT)||menus.contains(Res.MENU.RIGHT))
				list.add(new TreeForm().setId(Res.MENU.CONFIG).setText("系统管理").setIconCls("icon-Extension-1012333")
					.setSelected(true));
			break;
		case Res.MENU.MENU:
			if(menus.contains(Res.MENU.APPLICATION))
				list.add(new TreeForm().setId(Res.MENU.APPLICATION).setDataID(Res.MENU.APPLICATION).setType("Menu").setText("应用系统").setState(TreeForm.STATE_CLOSED).setChildren(new ArrayList<TreeForm>())
						.setIconCls("icon-Extension-application_double").setAttributes("Application.html").setState(TreeForm.STATE_CLOSED));
			if(menus.contains(Res.MENU.DATABASE))
				list.add(new TreeForm().setId(Res.MENU.DATABASE).setDataID(Res.MENU.DATABASE).setType("Menu").setText("数据库").setState(TreeForm.STATE_CLOSED).setIconCls("icon-Extension-databases")
						.setAttributes("DataBase.html"));
			break;
		case Res.MENU.CONFIG:
			if(menus.contains(Res.MENU.DICT))
				list.add(new TreeForm().setId(Res.MENU.DICT).setDataID(Res.MENU.DICT).setType("Menu").setText("数据字典").setIconCls("icon-Extension-book")
						.setAttributes("Dict.html"));
			if(menus.contains(Res.MENU.RIGHT))
				list.add(new TreeForm().setId(Res.MENU.RIGHT).setDataID(Res.MENU.RIGHT).setType("Menu").setText("角色权限").setIconCls("icon-Extension-group")
						.setAttributes("Role.html"));
			if(menus.contains(Res.MENU.LOG))
				list.add(new TreeForm().setId(Res.MENU.LOG).setDataID(Res.MENU.LOG).setType("Menu").setText("日志查询").setIconCls("icon-Extension-report_magnify")
						.setAttributes("Log.html"));
			break;
		default:
			break;
		}
		return list;
	}

}
