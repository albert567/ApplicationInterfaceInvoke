package com.gwamcc.aii.service;


import com.gwamcc.aii.forms.DefaultForm;

/**
 * 数据展示对象操作服务类
 * @author 张亚平
 *
 */
public interface SpaceNodeService {
	public DefaultForm getForceData(int appID);
	public DefaultForm getDbData(int dbID);
}
