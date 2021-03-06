package com.gwamcc.aii.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.AppFuncObjDao;
import com.gwamcc.aii.forms.AppFuncObjForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.AppFuncObjService;
/**
 * 应用功能数据库对象关联服务实现类
 * @author 张亚平
 *
 */
@Service
@CacheConfig(cacheNames="T_AppFuncObj")
public class AppFuncObjServiceImpl implements AppFuncObjService {
	@Autowired
    private AppFuncObjDao dao;

	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm edit(AppFuncObjForm objFunc)  throws Exception {
		return dao.edit(objFunc);
	}


	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm append(AppFuncObjForm objFunc) throws Exception {
		return dao.append(objFunc);
	}


	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm remove(AppFuncObjForm objFunc)  throws Exception {
		return dao.remove(objFunc);
	}


	@Override
	@Cacheable(key="#form.toString()+#page.toString()")
	public PageDataForm getList(AppFuncObjForm form, PageForm page) throws Exception {
		return dao.getList(form,page);
	}

	@Override
	@CacheEvict(allEntries=true)
	public Object upload(MultipartFile file) throws Exception {
		return dao.upload(file);
	}

	@Override
	public Object download() throws Exception {
		return dao.download();
	}


}
