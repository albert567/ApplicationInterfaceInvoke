package com.gwamcc.aii.dao;

import java.util.List;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.RoleUserForm;

public interface RoleUserDao {
	/**
	 * 获取所有授权人员的部门列表(统一授权)
	 * @return
	 */
	List<DefaultForm> getDepartList();
	/**
	 * 获取所有授权人员的部门列表(当前系统)
	 * @return
	 */
	List<DefaultForm> getCurrDepartList(int roleId);
	/**
	 * 根据roleId和sectionId筛选用户列表
	 * @param roleId
	 * @param sectionId
	 * @return
	 */
	List<DefaultForm> getUserList(int roleId,String sectionId);
	/**
	 * 获取角色人员列表
	 * @param form
	 * @return
	 */
	List<DefaultForm> getList(RoleUserForm form);
	/**
	 * 分页获取角色人员信息列表
	 * @param role	查询条件
	 * @param page	分页信息
	 * @return
	 */
	Object getList(RoleUserForm role,PageForm page);
	/**
	 * 编辑一条角色人员信息
	 * @param role
	 * @return
	 */
	Object edit(RoleUserForm role);
	/**
	 * 新增一条角色人员信息
	 * @param role
	 * @return
	 */
	Object append(RoleUserForm role);
	/**
	 * 删除一条角色人员信息
	 * @param id
	 * @return
	 */
	Object remove(int id);
	/**
	 * 根据部门获取人员列表
	 * @param sectionId
	 * @return
	 */
	List<DefaultForm> sync(String sectionId);
}
