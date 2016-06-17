package com.gwamcc.aii.service;

import java.util.List;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.RoleRightForm;

public interface RoleRightService {
	/**
	 * 获取数据库或应用系统列表,绑定到下拉框
	 * @param form
	 * @return
	 */
	List<DefaultForm> getName(int roleId,int menuId);
	
	List<DefaultForm> getList(RoleRightForm form);

	Object getList(RoleRightForm form, PageForm page);

	Object edit(RoleRightForm form);

	Object append(RoleRightForm form);

	Object remove(int id);

	String right(RoleRightForm form) throws Exception;

}
