package com.gwamcc.aii.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwamcc.aii.service.TemplateService;
import com.gwamcc.aii.dao.TemplateDao;
/**
 * 模板下载服务实现类
 * @author 范大德
 *
 */
@Service
//@CacheConfig(cacheNames="tree")
public class TemplateServiceImpl implements TemplateService {
	@Autowired
    private TemplateDao  dao;

	@Override
	public Object download(String[] name) throws Exception {
		return dao.download(name);
	}

	@Override
	public Object list() throws Exception {
		return dao.list();
	}

	@Override
	public Object download(String name) throws Exception {
		return dao.download(name);
	}

}
