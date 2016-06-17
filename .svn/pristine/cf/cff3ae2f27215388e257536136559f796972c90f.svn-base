package com.gwamcc.aii.service;

import java.util.List;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.RoleUserForm;

public interface RoleUserService {
	List<DefaultForm> getDepartList();
	/**
	 * 获取所有授权人员的部门列表(当前系统)
	 * @return
	 */
	List<DefaultForm> getCurrDepartList(int roleId);
	
	List<DefaultForm> getUserList(int roleId,String sectionId);
	
	List<DefaultForm> getList(RoleUserForm form);

	Object getList(RoleUserForm form, PageForm page);

	Object edit(RoleUserForm form);

	Object append(RoleUserForm form);

	Object remove(int id);

	List<DefaultForm> sync(String sectionId);
}
