package com.gwamcc.aii.dao;

import java.util.List;

import com.gwamcc.aii.forms.DefaultForm;

/**
 * 获取数据展示数据
 * @author 张亚平
 *
 */
public interface ForceNodeDao {
	/**
	 * 
	 * @param 
	 * @return 查询结果
	 */
	List<DefaultForm> getForceData();
}
