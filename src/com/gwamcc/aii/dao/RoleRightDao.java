package com.gwamcc.aii.dao;

import java.util.List;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.RoleRightForm;

public interface RoleRightDao {
	/**
	 * 获取数据库或应用系统列表,绑定到下拉框
	 * @param form
	 * @return
	 */
	List<DefaultForm> getName(int roleId,int menuId);
	/**
	 * 获取列表(用作下拉列表展示)
	 * @param form
	 * @return 名称列表
	 */
	List<DefaultForm> getList(RoleRightForm form);
	/**
	 * 分页获取列表
	 * @param role	查询条件
	 * @param page	分页信息
	 * @return	查询结果
	 */
	Object getList(RoleRightForm form, PageForm page);
	/**
	 * 编辑
	 * @param form
	 * @return
	 */
	Object edit(RoleRightForm form);
	/**
	 * 添加
	 * @param form
	 * @return
	 */
	Object append(RoleRightForm form);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Object remove(int id);
	
	String right(RoleRightForm form) throws Exception;
}
