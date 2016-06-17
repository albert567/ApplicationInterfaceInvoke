package com.gwamcc.aii.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwamcc.aii.dao.SpaceNodeDao;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.service.SpaceNodeService;

/**
 * 接口方法服务实现类
 * @author 张亚平
 *
 */
@Service
public class SpaceNodeServiceImpl implements SpaceNodeService {
	@Autowired
    private SpaceNodeDao dao;

	@Override
	public DefaultForm getForceData(int appID) {
		// TODO Auto-generated method stub
		return dao.getForceData(appID);
	}

	@Override
	public DefaultForm getDbData(int dbID) {
		// TODO Auto-generated method stub
		return dao.getDbData(dbID);
	}
	
}
