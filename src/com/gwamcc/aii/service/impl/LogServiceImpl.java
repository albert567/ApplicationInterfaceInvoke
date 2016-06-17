package com.gwamcc.aii.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.gwamcc.aii.dao.LogDao;
import com.gwamcc.aii.forms.LogQueryParamForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.LogService;
/**
 * 日志相关服务实现类
 * @author 范大德
 *
 */
@Service
@CacheConfig(cacheNames="T_Log")
public class LogServiceImpl implements LogService {
	@Autowired
    private LogDao dao;

	@Override
	@Cacheable(key="#type")
	public Object title(String type) throws Exception {
		return dao.title(type);
	}

	@Override
	@Cacheable(key="#table+#uuid+#page.toString()")
	public Object listByUuid(String table,String uuid, PageForm page) throws Exception {
		return dao.listByUuid(table,uuid,page);
	}

	@Override
	@Cacheable(key="#query.toString()+#page.toString()")
	public Object list(LogQueryParamForm query, PageForm page) throws Exception {
		return dao.list(query,page);
	}

	@Override
	@CacheEvict(allEntries=true)
	public Object rollback(String uuid) throws Exception {
		return dao.rollback(uuid);
	}

	@Override
	@Cacheable(key="#uuid")
	public Object tabs(String uuid) throws Exception {
		return dao.tabs(uuid);
	}

	@Override
	@Cacheable(key="#root.methodName")
	public Object tabs() throws Exception {
		return dao.tabs();
	}

	@Override
	@Cacheable(key="#datauuid")
	public Object history(String datauuid) throws Exception {
		return dao.history(datauuid);
	}

	@Override
	@Cacheable(key="#root.methodName")
	public Object operations() throws Exception {
		return dao.operations();
	}




}
