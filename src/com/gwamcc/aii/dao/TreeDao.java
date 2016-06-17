package com.gwamcc.aii.dao;

import java.util.List;

import com.gwamcc.aii.forms.TreeForm;

/**
 * 导航树数据操作接口
 * @author 范大德
 *
 */
public interface TreeDao {
	/**
	 * 获取子菜单列表
	 * @param id	父ID
	 * @return
	 */
	List<TreeForm> getSubList(int id);
}
